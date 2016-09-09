package com.mvc.timemachine.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mvc.timemachine.models.Post;
import com.mvc.timemachine.services.PostsService;
import com.mvc.timemachine.utils.JsonUtils;


@RestController
@EnableWebMvc
@RequestMapping(value = "/posts", headers = "Accept=*/*")
public class PostsController {

	@Autowired
	PostsService postsService;
	private Integer postsSize = 0;
	private Integer start = 0, end = 0; // start must be greater than end =>
										// start is last record

	@RequestMapping(value = "/top/{postsRequired}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String setTopPosts(@RequestParam(value = "eraId") Long eraId,
			@PathVariable("postsRequired") Integer postsRequired) {
		List<Post> posts = postsService.getAllPostsbyEraId(eraId);
		postsSize = posts.size();
		start = postsSize;
		end = (start - postsRequired > 0) ? (start - postsRequired) : 0;
		List<Post> topPosts = postsService.filterPosts(eraId, start, end, postsSize);
		return null ;// "[" + topPosts.stream().map(this::toJsonLink).collect(Collectors.joining(", \n")) + "]";
	}

	@RequestMapping(value = "/next/{postsRequired}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String getSomePosts(@RequestParam(value = "eraId") Long eraId,
			@PathVariable("postsRequired") Integer postsRequired) {
		start = end;
		end = (start - postsRequired > 0) ? (start - postsRequired) : 0;
		List<Post> posts = postsService.filterPosts(eraId, start, end, postsSize);
		return null ;//"[" + posts.stream().map(this::toJsonLink).collect(Collectors.joining(", \n")) + "]";
	}

	private String toJsonLink(Post post) {
		return "{" + JsonUtils.toJsonField("id", post.getId().toString()) + ", "
				+ JsonUtils.toJsonField("title", post.getTitle()) + ", " + JsonUtils.toJsonField("body", post.getBody())
				+ "}";
	}

	@RequestMapping(value = "/{postId}", method = RequestMethod.GET)
	public String showPost(@PathVariable("postId") Long postId) {
		Post post = postsService.getPostById(postId);

		if (post == null) {
			return "error" + postId;
		}

		return "[" + toJsonLink(post) + "]";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public Post showNewPost() {
		return new Post();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String saveNewPost(@Valid @ModelAttribute("post") Post post) {
		if (post == null) {
			return "error";
		}
		postsService.savePost(post);
		return "Ok";
	}

	@RequestMapping(value = "/{postId}/edit", method = RequestMethod.GET)
	public Post showEditPost(@PathVariable("postId") Long postId) {
		Post post = null;
		try {
			post = postsService.getPostById(postId);
		} catch (RuntimeException e) {
			String status = "error";
		}
		return post;

	}

	@RequestMapping(value = "/{postId}/edit", method = RequestMethod.POST)
	public Post editPost(@Valid @ModelAttribute("post") Post post, @PathVariable("postId") Long postId) {
		Post post2 = null;
		try {
			post.setId(postId);
			post2 = postsService.editPost(post);
		} catch (RuntimeException e) {
			String status = "error";
		}
		return post2;
	}

	@RequestMapping(value = "/{postId}/delete", method = RequestMethod.POST)
	public String deletePost(@PathVariable("postId") Long postId) {
		try {
			postsService.deletePostById(postId);
		} catch (RuntimeException e) {
			String status = "error";
		}
		return "OK";
	}

	@RequestMapping(value = "/{postId}/like", method = RequestMethod.POST)
	public String likePost(@PathVariable("postId") Long postId, @RequestParam(value = "userId") Long userId) {

		return null;// postsService.likePost(postsService.getPostById(postId), usersService.getUserByid(userId));

	}
}
