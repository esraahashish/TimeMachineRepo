package com.mvc.timemachine.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.mvc.timemachine.models.Era;
import com.mvc.timemachine.models.HashTag;
import com.mvc.timemachine.models.Like;
import com.mvc.timemachine.models.Post;
import com.mvc.timemachine.models.User;
import com.mvc.timemachine.repositories.EraRepo;
import com.mvc.timemachine.repositories.HashTagRepo;
import com.mvc.timemachine.repositories.LikesRepo;
import com.mvc.timemachine.repositories.PostsRepo;
import com.mvc.timemachine.utils.JsonUtils;


@Service("postsService")
public class PostsServiceImpl implements PostsService{

	@Autowired
	private PostsRepo postrepo;
	@Autowired 
	private LikesRepo likesRepo;
	@Autowired 
	private HashTagRepo hRepo;
	@Autowired 
	private EraRepo eraRepo;
	
	
	public List<Post> getAllPosts() {
		// TODO Auto-generated method stub
		return postrepo.findAll();
	}

	public Post savePost(Post post) {
		// TODO Auto-generated method stub
		return postrepo.saveAndFlush(post);
	}

	public void deletePostById(Long postId) {
		// TODO Auto-generated method stub
		postrepo.delete(postId);
	}

	public Post editPost(Post newPost) {
		// TODO Auto-generated method stub
		Post old = getPostById(newPost.getId());
		old.setTitle(newPost.getTitle());
		old.setBody(newPost.getBody());
		old.setDate(newPost.getDate());
		
		return postrepo.saveAndFlush(old);
	}

	public Post getPostById(Long id) {
		// TODO Auto-generated method stub
		return postrepo.findOne(id);
	}

	public List<Post> getAllPostsbyEraId(Long eraId) {
		// TODO Auto-generated method stub
		return postrepo.getsAllPostsByEraId(eraId);
	}

	public List<Post> getTopEraPosts(Long eraId, int postsRequired) {
		// TODO Auto-generated method stub
		List<Post> posts = getAllPostsbyEraId(eraId);
		List<Post> returnPosts = new ArrayList<Post>();
		int n = posts.size()-postsRequired;
		for(int i= n>0 ?n:0; i<posts.size(); i++){
			returnPosts.add(posts.get(i));
		}
		return returnPosts;
	}
	public List<Post> getNextEraPosts(Long eraId, int postsRequired, int beginingIndex) {
		// TODO Auto-generated method stub
		List<Post> posts = getAllPostsbyEraId(eraId);
		List<Post> returnPosts = new ArrayList<Post>();
		int n = beginingIndex+postsRequired+1;
		for(int i= beginingIndex > 0 ? beginingIndex: 0; i<(n<posts.size()?n:posts.size()); i++){
			returnPosts.add(posts.get(i));
		}
		return returnPosts;
	}
	
	public List<Post> getTopEraUserPosts(Long eraId, Long userId , int postsRequired) {
		// TODO Auto-generated method stub
		List<Post> posts = postrepo.getEraUserPosts(eraId, userId);
		List<Post> returnPosts = new ArrayList<Post>();
		int n = posts.size()-postsRequired;
		for(int i= n>0 ?n:0; i<posts.size(); i++){
			returnPosts.add(posts.get(i));
		}
		return returnPosts;
	}
	public List<Post> getNextEraUserPosts(Long eraId, Long userId , int postsRequired, int beginingIndex) {
		// TODO Auto-generated method stub
		List<Post> posts = postrepo.getEraUserPosts(eraId, userId);
		List<Post> returnPosts = new ArrayList<Post>();
		int n = beginingIndex+postsRequired+1;
		for(int i= beginingIndex > 0 ? beginingIndex: 0; i<(n<posts.size()?n:posts.size()); i++){
			returnPosts.add(posts.get(i));
		}
		return returnPosts;
	}

	public String likePost(Post post, User user) {
		// TODO Auto-generated method stub
		List<Like> likes = likesRepo.getAllLikesByPostIdAndUserId(post.getId(), user.getId());
		if(likes.size() == 1){
			return dislikePost(likes.get(0));
		}
		Like like = new Like();
		like.setPost(post);
		like.setUser(user);
		likesRepo.saveAndFlush(like);
		return "Like";
	}

	public String dislikePost(Like like) {
		// TODO Auto-generated method stub
		
		likesRepo.delete(like);
		return "Dislike";
	}
	public List<Post> getEraUserPosts(Long eraId , Long userId){
		List<Post> posts = postrepo.getEraUserPosts(eraId, userId);
		return posts;
		/*
		String res = "[";
		Iterator<Post> ite = posts.iterator();
		int taken = 0;
		while(ite.hasNext() && taken < 20){
			if(taken != 0){
				res += ",";
			}
			res += posttoJsonLink(ite.next());
			taken ++;
		}
		res += "]";
		return res;
		*/
	}

	
	private String posttoJsonLink(Post post) {
		return "{" + JsonUtils.toJsonField("id", post.getId().toString()) + ", "
				+ JsonUtils.toJsonField("title", post.getTitle()) + ", " + JsonUtils.toJsonField("body", post.getBody())
				+ "}";
	}
	//*****************************************extracting hashtags from a post***************************************************************
	public void extractPostHashTags(Long postId){
		Post post = postrepo.findOne(postId);
		Era era = post.getEra();
		char[] body = post.getBody().toCharArray();
		for (int i=0;i<body.length;i++){
			if(body[i] == '#'){
				String hashtagName = "";
				while(i<body.length && body[i] != ' ' && body[i] !='\n'){
					i++;
					hashtagName+=body[i];
				}
				HashTag hash = hRepo.getHashtagByName(hashtagName);
				if(hash == null){
					hash= new HashTag();
					hash.setFrequency(0L);
					hash.setName(hashtagName);
				}
				hash.setFrequency(hash.getFrequency()+1L);
				post.getPostHashTags().add(hash);
				hash.getHashTagposts().add(post);
				
				
				HashTag hash2 = hRepo.getHashtagByNameandEra(hashtagName, era);
				if(hash2 == null){
					hash2 =hash;
					era.getEraHashTags().add(hash2);
					hash2.getHashTagEras().add(era);
				}
				
				
				///test that
				hRepo.saveAndFlush(hash);
			}	
			
		}	
	}
}
