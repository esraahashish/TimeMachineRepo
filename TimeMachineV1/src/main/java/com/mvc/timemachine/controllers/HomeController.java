package com.mvc.timemachine.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {


	protected static final String VIEW_NAME_HOMEPAGE = "index";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHomePage() {
		return VIEW_NAME_HOMEPAGE;
	}
}
