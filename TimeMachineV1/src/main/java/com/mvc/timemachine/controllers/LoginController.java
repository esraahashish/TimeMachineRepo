package com.mvc.timemachine.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mvc.timemachine.models.Post;
import com.mvc.timemachine.models.User;
import com.mvc.timemachine.services.UserService;

@Controller
public class LoginController {


	@Autowired
	private UserService service;
	
	protected static final String VIEW_NAME_LOGIN_PAGE = "user/login";

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage() {
		return VIEW_NAME_LOGIN_PAGE;
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@Valid @ModelAttribute("user") User user) {
		System.out.println("login controller  ");
		System.out.println(user.getEmail());
		if(user.getId() == null){
			System.out.println("id = null");
			return VIEW_NAME_LOGIN_PAGE;
		}
		User u = service.loginUser(user);
		if(u == null)
			return VIEW_NAME_LOGIN_PAGE;
		return "redirect:/";
	}
	
}
