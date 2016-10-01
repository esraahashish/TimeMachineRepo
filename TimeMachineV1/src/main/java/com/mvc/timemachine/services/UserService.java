package com.mvc.timemachine.services;

import com.mvc.timemachine.dto.RegistrationFormDTO;
import com.mvc.timemachine.models.User;

public interface UserService {

	/**
	 * Creates a new user account to the service.
	 * 
	 * @param userAccountData
	 *            The information of the created user account.
	 * @return The information of the created user account.
	 * @throws DuplicateEmailException
	 *             Thrown when the email address is found from the database.
	 */
	public User registerNewUserAccount(RegistrationFormDTO userAccountData) throws DuplicateEmailException;

	public User loginUser(User user);
	
	public User getUserByid(Long userId);
}
