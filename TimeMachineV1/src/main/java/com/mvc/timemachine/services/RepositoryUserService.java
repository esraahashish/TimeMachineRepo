package com.mvc.timemachine.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.timemachine.dto.RegistrationFormDTO;
import com.mvc.timemachine.models.User;
import com.mvc.timemachine.repositories.UserRepository;


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
}
