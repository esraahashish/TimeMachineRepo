package com.mvc.timemachine.services;

import java.util.List;

import com.mvc.timemachine.models.Like;
import com.mvc.timemachine.models.Post;
import com.mvc.timemachine.models.User;


public interface PostsService {

	public List<Post> getAllPosts();
	public Post savePost(Post post);
	public void deletePostById(Long postId);
	public String likePost(Post post, User user);
	public String dislikePost(Like like);
	public Post editPost(Post post);
	public Post getPostById(Long id);
	public List<Post> getAllPostsbyEraId(Long eraId);
	public List<Post> filterPosts(Long eraId, int start, int end, int size);
}
