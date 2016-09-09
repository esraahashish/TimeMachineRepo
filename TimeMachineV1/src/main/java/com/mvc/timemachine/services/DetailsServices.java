//package com.mvc.timemachine.services;
//
//
//import org.springframework.dao.DataAccessException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.social.security.SocialUserDetails;
//import org.springframework.social.security.SocialUserDetailsService;
//
//
//public class DetailsServices implements SocialUserDetailsService {
//
//
//	private UserDetailsService detailsServices;
//
//	public DetailsServices(UserDetailsService userDetailsService) {
//		this.detailsServices = userDetailsService;
//	}
//
//	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
//
//		UserDetails userDetails = detailsServices.loadUserByUsername(userId);
//
//		return (SocialUserDetails) userDetails;
//	}
//}
