package com.mvc.timemachine.services;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.timemachine.dto.RegistrationFormDTO;
import com.mvc.timemachine.models.User;
import com.mvc.timemachine.repositories.UserRepository;
import com.mvc.timemachine.utils.JsonUtils;


@Service
public class RepositoryUserService implements UserService {


	private UserRepository repository;

	@Autowired
	public RepositoryUserService(UserRepository repository) {
		this.repository = repository;
	}

	public User registerNewUserAccount(RegistrationFormDTO userAccountData) throws DuplicateEmailException {

		if (emailExist(userAccountData.getEmail())) {
			throw new DuplicateEmailException(
					"The email address: " + userAccountData.getEmail() + " is already in use.");
		}


		String encodedPassword = encodePassword(userAccountData);

		User user = new User();
		user.setEmail(userAccountData.getEmail());
		user.setUsername(userAccountData.getUsername());
		user.setPassword(userAccountData.getPassword());

		if (userAccountData.isSocialSignIn()) {
			user.setSignInProvider(userAccountData.getSignInProvider());
		}
		return repository.saveAndFlush(user);
	}

	private boolean emailExist(String email) {

		User user = repository.findByEmail(email);

		if (user != null) {
			return true;
		}
		return false;
	}

	private String encodePassword(RegistrationFormDTO dto) {
		String encodedPassword = null;

		if (dto.isNormalRegistration()) {
//			encodedPassword = passEncoder.encode(dto.getPassword());
		}

		return encodedPassword;
	}

	@Override
	public User loginUser(User user) {
		// TODO Auto-generated method stub
		return repository.findOne(user.getId());
	}

	@Override
	public User getUserByid(Long userId) {
		// TODO Auto-generated method stub
		return repository.findOne(userId);
	}
	
	public String getUserbyEmail(String email){
		User user = repository.findByEmail(email);
		return "[" + usertoJsonLink(user) + "]";
	}
	
	public String getUsersbyUserName(String username){
		List<User> users = repository.findByUsername(username);
		String res = "[";
		Iterator<User> ite = users.iterator();
		int taken = 0;
		while(ite.hasNext() && taken < 20){
			if(taken != 0){
				res += ",";
			}
			res += usertoJsonLink(ite.next());
			taken ++;
		}
		res += "]";
		return res;
	}
	
	public String getUserbyId(Long id){
		User user = repository.findOne(id);
		return "[" + usertoJsonLink(user) + "]";
	}
	
	private String usertoJsonLink(User user) {
		return "{" + JsonUtils.toJsonField("id", user.getId().toString()) + ", "
				+ JsonUtils.toJsonField("userName", user.getUsername()) + ", " + JsonUtils.toJsonField("email", user.getEmail())
				+ "}";
	}
}
