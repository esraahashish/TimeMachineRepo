package com.mvc.timemachine.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.timemachine.models.Like;
import com.mvc.timemachine.models.Post;
import com.mvc.timemachine.models.User;
import com.mvc.timemachine.repositories.LikesRepo;
import com.mvc.timemachine.repositories.PostsRepo;


@Service("postsService")
public class PostsServiceImpl implements PostsService{

	@Autowired
	private PostsRepo postrepo;
	@Autowired 
	private LikesRepo likesRepo;
	
	
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

	public List<Post> filterPosts(Long eraId, int start, int end, int size) {
		// TODO Auto-generated method stub
		List<Post> posts = getAllPostsbyEraId(eraId);
		List<Post> returnPosts = new ArrayList<Post>();
		for(int i=end; (i<size && i<start); i++){
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

	

}
