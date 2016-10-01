package com.mvc.timemachine.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mvc.timemachine.models.Like;



@Repository
public interface LikesRepo extends JpaRepository <Like, Long>{

	@Query("SELECT l FROM Like l LEFT JOIN FETCH l.post WHERE l.post.id = :postId AND l.user.id = :userId")
	public List<Like> getAllLikesByPostIdAndUserId(@Param("postId")Long postId, @Param("userId")Long userId);
}
