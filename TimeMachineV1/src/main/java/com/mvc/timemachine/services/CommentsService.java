package com.mvc.timemachine.services;

import java.util.List;

import com.mvc.timemachine.models.Comment;


public interface CommentsService {

	public List<Comment> getAllCommentsByPostId(Long post);
	public Comment saveComment(Comment comment);
	public void deleteCommentById(Long commentId);
	public Comment editComment(Comment comment);
	public Comment getCommentById(Long commentId);
	
}
