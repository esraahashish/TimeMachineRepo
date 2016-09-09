//package com.mvc.timemachine.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.mvc.timemachine.dto.UserDetailsDTO;
//import com.mvc.timemachine.models.User;
//import com.mvc.timemachine.repositories.UserRepository;
//
//
//
//public class RepositoryUserDetailsService implements UserDetailsService {
//
//
//	private UserRepository repository;
//
//	@Autowired
//	public RepositoryUserDetailsService(UserRepository repository) {
//		this.repository = repository;
//	}
//
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		User user = repository.findByEmail(username);
//
//		if (user == null) {
//			throw new UsernameNotFoundException("this user doesn't exist username: " + username);
//		}
//
//		UserDetailsDTO principal = UserDetailsDTO.getBuilder().firstName(user.getUserName()).id(user.getId())
//				.password(user.getPassword())
//				.socialSignInProvider(user.getSignInProvider()).username(user.getEmail()).build();
//
//
//		return principal;
//	}
//}
