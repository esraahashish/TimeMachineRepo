package com.mvc.timemachine.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.timemachine.models.Era;
import com.mvc.timemachine.models.HashTag;
import com.mvc.timemachine.models.Information;
import com.mvc.timemachine.models.Post;
import com.mvc.timemachine.repositories.EraRepo;
import com.mvc.timemachine.repositories.HashTagRepo;
import com.mvc.timemachine.repositories.InformationRepo;
import com.mvc.timemachine.repositories.PostsRepo;
import com.mvc.timemachine.utils.*;


@Service
public class HashTagService {
	
	@Autowired
	HashTagRepo hrepo;
	
	@Autowired
	EraRepo erepo;
	
	@Autowired
	InformationRepo irepo;
	
	@Autowired
	PostsRepo prepo;

	public String searchHashtaginEra(Long eraId , String hashtagName){
		HashTag hashtag =  hrepo.getHashtagByName(hashtagName);
		if(hashtag == null){
			return "[{" + JsonUtils.toJsonField("status", "not found") + "}]";
		}
		String res = new String();
		res += "[{" + JsonUtils.toJsonField("status", "found") + "}],";
		res += "[" + hashTagtoJsonLink(hashtag) + "]";
		Era era = erepo.findOne(eraId);
		if(era.getEraHashTags().contains(hashtag)){
			List<Information> info = irepo.getEraHashtaginfo(eraId, hashtag);
			List<Post> posts = prepo.getEraHashtagPosts(eraId, hashtag);
			res += ",[";
			Iterator<Information> ite = info.iterator();
			int taken = 0;
			while(ite.hasNext() && taken < 20){
				if(taken != 0){
					res += ",";
				}
				res += infotoJsonLink(ite.next());
				taken ++;
			}
			res += "]";
			res += ",[";
			Iterator<Post> ite2 = posts.iterator();
			taken = 0;
			while(ite2.hasNext() && taken < 20){
				if(taken != 0){
					res += ",";
				}
				res += posttoJsonLink(ite2.next());
				taken ++;
			}
			res += "]";
		
		}
		return res;
	}
	
	private String infotoJsonLink(Information info){
		return "{" + JsonUtils.toJsonField("id", info.getId().toString()) + "," 
				+ JsonUtils.toJsonField("content", info.getContent()) + ","
				+ JsonUtils.toJsonField("date", info.getDate().toString()) + "}";
	}
	
	private String posttoJsonLink(Post post) {
		return "{" + JsonUtils.toJsonField("id", post.getId().toString()) + ", "
				+ JsonUtils.toJsonField("title", post.getTitle()) + ", " + JsonUtils.toJsonField("body", post.getBody())
				+ "}";
	}
	
	private String hashTagtoJsonLink(HashTag hashTag) {
		return "{" + JsonUtils.toJsonField("id", hashTag.getId().toString()) + ", "
				+ JsonUtils.toJsonField("name", hashTag.getName()) + ", " + JsonUtils.toJsonField("description", hashTag.getDescription())
				+ ", " + JsonUtils.toJsonField("frequency", hashTag.getFrequency().toString())
				+ "}";
	}
	
}
