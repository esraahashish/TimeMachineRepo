//package com.mvc.timemachine.utils;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import com.mvc.timemachine.dto.UserDetailsDTO;
//import com.mvc.timemachine.models.User;
//
//
//public class SecurityUtil {
//
//
//	public static void logInUser(User user) {
//
//		UserDetailsDTO userDetails = UserDetailsDTO.getBuilder().firstName(user.getUserName()).id(user.getId())
//				.password(user.getPassword())
//				.socialSignInProvider(user.getSignInProvider()).username(user.getEmail()).build();
//
//		Authentication authen = new UsernamePasswordAuthenticationToken(userDetails, null,
//				userDetails.getAuthorities());
//		SecurityContextHolder.getContext().setAuthentication(authen);
//
//	}
//}
