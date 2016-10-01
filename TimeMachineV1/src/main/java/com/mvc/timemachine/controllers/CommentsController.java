package com.mvc.timemachine.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mvc.timemachine.models.Comment;
import com.mvc.timemachine.services.CommentsService;
import com.mvc.timemachine.services.PostsService;
import com.mvc.timemachine.utils.JsonUtils;



@RestController
@EnableWebMvc
@RequestMapping(value="/posts/{postId}/comments", headers="Accept=*/*")
public class CommentsController {

	@Autowired
	CommentsService commentsService;
	@Autowired
	PostsService postsService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getCommentsOfPost(@PathVariable("postId") Long postId){
		if(postsService.getPostById(postId) == null){
			return "error";
		}
		List<Comment> comments = commentsService.getAllCommentsByPostId(postId);
		return "[" + comments.stream().map(this::toJsonLink).collect(Collectors.joining(", \n")) + "]";
	}
	private String toJsonLink(Comment comment) {
        return "{" + JsonUtils.toJsonField("id", comment.getId().toString()) + ", " + JsonUtils.toJsonField("text", comment.getCommentText()) + ", " + JsonUtils.toJsonField("date", comment.getDate().toString()) + "}";
    }
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String saveNewcomment(@Valid @ModelAttribute("comment") Comment comment, @PathVariable("postId") Long postId){
		if(comment == null || postsService.getPostById(postId) == null){
			return "error";
		}
		commentsService.saveComment(comment);
		return "Ok";
	}
	
	@RequestMapping(value="/{commentId}/edit", method=RequestMethod.POST)
	public Comment editcomment(@Valid @ModelAttribute("comment") Comment comment,@PathVariable("commentId") Long commentId, @PathVariable("postId") Long postId){
		Comment comment2 = null;
		try{
			comment2 = commentsService.editComment(comment);
		}catch(RuntimeException e){
			String status = "error";
		}
		return comment2;
	}

	@RequestMapping(value="/{commentId}/delete", method=RequestMethod.POST)
	public String deletecomment(@PathVariable("commentId") Long commentId, @PathVariable("postId") Long postId){
		try{
			commentsService.deleteCommentById(commentId);
		}catch(RuntimeException e){
			String status = "error";
		}
		return "OK";
	}
	
}
