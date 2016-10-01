package com.mvc.timemachine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mvc.timemachine.services.EraService;





@RestController
@EnableWebMvc
@RequestMapping(value="/era", headers="Accept=*/*")
public class EraController {
	
	@Autowired
	EraService eraService;
	
	@RequestMapping(value = "/{eraId}/newsfeed" , method = RequestMethod.GET , headers = "Accept=application/json")
	public String getEraInformation(@PathVariable("eraId") Long eraId){
		return eraService.getInformation(eraId);
	
	}
	@RequestMapping(value = "/eraselection" , method = RequestMethod.GET , headers = "Accept=application/json")
	public String getErasWithNoBeginning(){
		return eraService.getErasWithNoBeginning();
	}
	
	@RequestMapping(value = "/eraselection/{beginningEra}" , method = RequestMethod.GET , headers = "Accept=application/json")
	public String getErasWithBeginning(@PathVariable("beginningEra") Integer beginningEra){
		return eraService.getErasWithBeginning(beginningEra);
	}
	@RequestMapping(value = "/{eraId}/tophashtags" , method = RequestMethod.GET , headers = "Accept=application/json")
	public String getTopHashTags(){
		return eraService.getTopHashTags();
	}
	
	@RequestMapping(value = "/selectusers/{eraId}" , method = RequestMethod.GET , headers = "Accept=application/json")
	public String getEraUsers(@PathVariable("eraId") Long eraId){
		return eraService.getEraUsers(eraId);
	}
	
	@RequestMapping(value = "/selecteras/{userId}" , method = RequestMethod.GET , headers = "Accept=application/json")
	public String getUserEras(@PathVariable("userId") Long userId){
		return eraService.getUserEras(userId);
	}
	
	@RequestMapping(value = "/selecthashtags/{postId}" , method = RequestMethod.GET , headers = "Accept=application/json")
	public String getPostHashTags(@PathVariable("postId") Long postId){
		return eraService.getPostHashTags(postId);
	}

	
}
