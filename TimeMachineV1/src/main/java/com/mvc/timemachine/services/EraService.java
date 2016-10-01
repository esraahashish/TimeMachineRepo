package com.mvc.timemachine.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.timemachine.models.Era;
import com.mvc.timemachine.models.HashTag;
import com.mvc.timemachine.models.Information;
import com.mvc.timemachine.models.User;
import com.mvc.timemachine.repositories.EraRepo;
import com.mvc.timemachine.repositories.HashTagRepo;
import com.mvc.timemachine.repositories.InformationRepo;
import com.mvc.timemachine.repositories.PostsRepo;
import com.mvc.timemachine.repositories.UserRepository;
import com.mvc.timemachine.utils.*;


@Service("eraService")
public class EraService {
	@Autowired
	EraRepo eraRepo;
	
	@Autowired
	InformationRepo infoRepo;
	
	@Autowired
	HashTagRepo hashTagRepo;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostsRepo postsRepo;
	
	public String getInformation(Long eraId){
		String res = "[";
		Era era = eraRepo.findOne(eraId);
		List<Information> info = era.getEraInfo();
		int begin = era.getStartInfoInd();
		int shouldTake = era.getInfoIncreaseRate();
		int taken = 0;
		while(taken < shouldTake && begin < info.size()){
			if(taken != 0){
				res += ",";
			}
			res += infotoJsonLink(info.get(begin));
			taken ++;
			begin ++;
		}
		res += "]";
		return res;
	}
	
	public String getErasWithNoBeginning(){
		List<Era> eras = (List<Era>) eraRepo.findAll();
		String res = "[";
		for(int i = eras.size()-1; i >=0 && i >=eras.size()-5; i --){
			if(i != eras.size() - 1){
				res += ",";
			}
			res += eratoJsonLink(eras.get(i));
		}
		res += "]";
		return res;
	}
	
	public String getErasWithBeginning(Integer beginningEra){
		String res = "[";
		List<Era> eras = (List<Era>) eraRepo.findAll();
		int cnt = 0;
		for(int i = beginningEra-1; i >=0 && cnt < 5; i --){
			//res += JsonUtils.toJsonField(eras.get(i).getId().toString() , eras.get(i).getName());
			if(i != beginningEra-1){
				res += ",";
			}
			res+=eratoJsonLink(eras.get(i));
			cnt++;
		}
		res += "]";
		return res;
	}
	
	public String getTopHashTags(){
		String res = "[";
		List<HashTag> hashTags  = hashTagRepo.getHashTags();
		for(int i = hashTags.size()-1; i >=0 && i>=hashTags.size()-10 ; i --){
			if(i != hashTags.size()-1){
				res += ",";
			}
			res += hashTagtoJsonLink(hashTags.get(i));
		}
		res += "]";
		return res;
		
	}
	public String getEraUsers(Long eraId){
		List<User> users  = eraRepo.findOne(eraId).getEraUsers();
		String res = "[";
		for(int i = 0; i < users.size(); i ++){
			if(i != 0){
				res += ",";
			}
			res += usertoJsonLink(users.get(i));
		}
		res += "]";
		
		return res;
		
	}

	public String getUserEras(Long userId) {
		List<Era> eras  = userRepository.findOne(userId).getUserEras();
		String res = "[";
		for(int i = 0; i < eras.size(); i ++){
			if(i != 0){
				res += ",";
			}
			res += eratoJsonLink(eras.get(i));
		}
		res += "]";
		
		return res;
	}   
	private String eratoJsonLink(Era era) {
		return "{" + JsonUtils.toJsonField("id", era.getId().toString()) + ", "
				+ JsonUtils.toJsonField("name", era.getName()) + ", " + JsonUtils.toJsonField("description", era.getDescription())
				+ "}";
	}
	private String usertoJsonLink(User user) {
		return "{" + JsonUtils.toJsonField("id", user.getId().toString()) + ", "
				+ JsonUtils.toJsonField("userName", user.getUsername()) + ", " + JsonUtils.toJsonField("email", user.getEmail())
				+ ", " + JsonUtils.toJsonField("password", user.getPassword())
				+ "}";
	}
	
	private String hashTagtoJsonLink(HashTag hashTag) {
		return "{" + JsonUtils.toJsonField("id", hashTag.getId().toString()) + ", "
				+ JsonUtils.toJsonField("name", hashTag.getName()) + ", " + JsonUtils.toJsonField("description", hashTag.getDescription())
				+ ", " + JsonUtils.toJsonField("frequency", hashTag.getFrequency().toString())
				+ "}";
	}

	public String getPostHashTags(Long postId) {
		String res = "[";
		List<HashTag> hashTags  = postsRepo.findOne(postId).getPostHashTags();
		for(int i =0; i <hashTags.size() ; i++){
			res += hashTagtoJsonLink(hashTags.get(i));
		}
		res += "]";
		return res;
	}
	
	private String infotoJsonLink(Information info){
		return "{" + JsonUtils.toJsonField("id", info.getId().toString()) + "," 
				+ JsonUtils.toJsonField("content", info.getContent()) + ","
				+ JsonUtils.toJsonField("date", info.getDate().toString()) + "}";
	}
	
}
