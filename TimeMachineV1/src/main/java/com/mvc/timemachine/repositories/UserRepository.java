package com.mvc.timemachine.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mvc.timemachine.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// @Query("select u from User u where u.email = :email")
	public User findByEmail(/* @Param("email") */String email);
	
	public List<User> findByUsername(String username);
}
