package com.mvc.timemachine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mvc.timemachine.repositories.UserRepository;
import com.mvc.timemachine.services.RepositoryUserService;

@RestController
@EnableWebMvc
@RequestMapping(value="/user", headers="Accept=*/*")
public class UserController {
	
	@Autowired
	RepositoryUserService userv;
	
	//need testing
	@RequestMapping(value = "/searchByEmail" , method = RequestMethod.GET , headers = "Accept=application/json")
	public String getUserbyEmail(@RequestParam(value = "email")String email){
		return userv.getUserbyEmail(email);
	}

	//need testing
	@RequestMapping(value = "/searchByUserName" , method = RequestMethod.GET , headers = "Accept=application/json")
	public String getUsersbyUserName(@RequestParam(value = "username")String username){
		return userv.getUsersbyUserName(username);
	}
	
	@RequestMapping(value = "/{userId}" , method = RequestMethod.GET , headers = "Accept=application/json")
	public String getUsersbyId(@PathVariable(value = "userId")Long userId){
		return userv.getUserbyId(userId);
	}
	
}
