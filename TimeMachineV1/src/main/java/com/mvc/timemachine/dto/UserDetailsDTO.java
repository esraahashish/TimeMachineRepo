package com.mvc.timemachine.dto;

//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.social.security.SocialUser;

import com.mvc.timemachine.models.SocialMediaService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsDTO {//extends SocialUser {

	private Long id;

	private String username;


	private SocialMediaService socialSignInProvider;

//	public UserDetailsDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//		super(username, password, authorities);
//	}

	public static Builder getBuilder() {
		return new Builder();
	}

	public Long getId() {
		return id;
	}

	public String getUserName() {
		return username;
	}

	public SocialMediaService getSocialSignInProvider() {
		return socialSignInProvider;
	}

	@Override
	public String toString() {
		return "";
//		return new ToStringBuilder(this).append("id", id).append("username", getUsername())
//				.append("userName", username).append("socialSignInProvider", socialSignInProvider).toString();
	}

	public static class Builder {

		private Long id;

		private String username;

		private String userName;

		

		private String password;

		

		private SocialMediaService socialSignInProvider;

//		private Set<GrantedAuthority> authorities;

		public Builder() {
//			this.authorities = new HashSet();
		}

		public Builder firstName(String firstName) {
			this.userName = firstName;
			return this;
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

	

		public Builder password(String password) {
			if (password == null) {
				password = "SocialUser";
			}

			this.password = password;
			return this;
		}

	
		public Builder socialSignInProvider(SocialMediaService socialSignInProvider) {
			this.socialSignInProvider = socialSignInProvider;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public UserDetailsDTO build() {
			return null;
//			UserDetailsDTO user = new UserDetailsDTO(username, password, authorities);
//
//			user.id = id;
//			user.username = userName;
//			
//			user.socialSignInProvider = socialSignInProvider;
//
//			return user;
		}
	}
}
