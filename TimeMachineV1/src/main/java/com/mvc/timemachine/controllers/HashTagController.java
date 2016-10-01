package com.mvc.timemachine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mvc.timemachine.services.HashTagService;


@RestController
@EnableWebMvc
@RequestMapping(value="/hashtag/{hashtagName}", headers="Accept=*/*")
public class HashTagController {
	
	@Autowired
	HashTagService hserv;

	@RequestMapping(value = "/{eraId}" , method = RequestMethod.GET , headers = "Accept=application/json")
	public String searchHashtaginEra(@PathVariable("eraId") Long eraId , @PathVariable("hashtagName") String hashtagName){
		return hserv.searchHashtaginEra(eraId, hashtagName);
	}
	
	
	
}
