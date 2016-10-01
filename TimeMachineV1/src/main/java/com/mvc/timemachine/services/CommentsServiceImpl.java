package com.mvc.timemachine.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.timemachine.models.Comment;
import com.mvc.timemachine.repositories.CommentsRepo;


@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentsRepo commentsRepo;
	
	public List<Comment> getAllCommentsByPostId(Long postId) {
		// TODO Auto-generated method stub
		return commentsRepo.getAllCommentsbyPostId(postId);
	}
	
	public Comment saveComment(Comment comment) {
		// TODO Auto-generated method stub
		
		return commentsRepo.saveAndFlush(comment);
	}

	public void deleteCommentById(Long commentId) {
		// TODO Auto-generated method stub
		commentsRepo.delete(commentId);
	}

	public Comment editComment(Comment newComment) {
		// TODO Auto-generated method stub
		Comment old = getCommentById(newComment.getId());
		old.setCommentText(newComment.getCommentText());
		old.setDate(newComment.getDate());
		return commentsRepo.saveAndFlush(old);
	}

	public Comment getCommentById(Long commentId) {
		// TODO Auto-generated method stub
		return commentsRepo.findOne(commentId);
	}

	
}
