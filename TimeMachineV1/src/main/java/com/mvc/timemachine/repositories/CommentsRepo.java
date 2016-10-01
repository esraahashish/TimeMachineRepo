package com.mvc.timemachine.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mvc.timemachine.models.Comment;


@Repository
public interface CommentsRepo extends JpaRepository<Comment, Long> {

	@Query("SELECT c FROM Comment c LEFT JOIN FETCH c.post WHERE c.post.id = :postId ORDER BY c.date DESC")
	public List<Comment> getAllCommentsbyPostId(@Param("postId")Long postId);
}
