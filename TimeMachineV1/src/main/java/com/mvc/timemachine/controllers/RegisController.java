package com.mvc.timemachine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.social.connect.Connection;
//import org.springframework.social.connect.ConnectionKey;
//import org.springframework.social.connect.UserProfile;
//import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.mvc.timemachine.dto.RegistrationFormDTO;
import com.mvc.timemachine.models.SocialMediaService;
import com.mvc.timemachine.models.User;
import com.mvc.timemachine.services.DuplicateEmailException;
import com.mvc.timemachine.services.UserService;
//import com.mvc.timemachine.utils.SecurityUtil;

import javax.validation.Valid;

@Controller
@SessionAttributes("user")
public class RegisController {


	protected static final String ErrorEmailExist = "NotExist.user.email";
	protected static final String MODEL_NAME_REGISTRATION_DTO = "user";
	protected static final String VIEW_NAME_REGISTRATION_PAGE = "user/registrationForm";

	private UserService service;

	@Autowired
	public RegisController(UserService service) {
		this.service = service;
	}

	
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {

//		Connection<?> connection = ProviderSignInUtils.getConnection(request);

		RegistrationFormDTO registration = createRegistrationDTO();

		model.addAttribute(MODEL_NAME_REGISTRATION_DTO, registration);

		return VIEW_NAME_REGISTRATION_PAGE;
	}

	private RegistrationFormDTO createRegistrationDTO() {
		RegistrationFormDTO dto = new RegistrationFormDTO();

//		if (connection != null) {
//			UserProfile socialMediaProfile = connection.fetchUserProfile();
//			dto.setEmail(socialMediaProfile.getEmail());
//			dto.setUserName(socialMediaProfile.getFirstName());
//			
//
//			ConnectionKey providerKey = connection.getKey();
//			dto.setSignInProvider(SocialMediaService.valueOf(providerKey.getProviderId().toUpperCase()));
//		}

		return dto;
	}


	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public String registerUserAccount(@Valid @ModelAttribute("user") RegistrationFormDTO userAccountData,
			BindingResult result, WebRequest request) throws DuplicateEmailException {
//		if (result.hasErrors()) {
//			return VIEW_NAME_REGISTRATION_PAGE;
//		}

		User registered = createUserAccount(userAccountData, result);

		// If email address was already found from the database, render the form
		// view.
		if (registered == null) {
			return VIEW_NAME_REGISTRATION_PAGE;
		}


		// Logs the user in.
//		SecurityUtil.logInUser(registered);
		// If the user is signing in by using a social provider, this method
		// call stores
		// the connection to the UserConnection table. Otherwise, this method
		// does not
		// do anything.
//		ProviderSignInUtils.handlePostSignUp(registered.getEmail(), request);

		return "redirect:/";
	}

	private User createUserAccount(RegistrationFormDTO userAccountData, BindingResult result) {
		User registered = null;

		try {
			registered = service.registerNewUserAccount(userAccountData);
		} catch (DuplicateEmailException ex) {
			addFieldError(MODEL_NAME_REGISTRATION_DTO, RegistrationFormDTO.FIELD_NAME_EMAIL, userAccountData.getEmail(),
					ErrorEmailExist, result);
		}

		return registered;
	}

	private void addFieldError(String objectName, String fieldName, String fieldValue, String errorCode,
			BindingResult result) {
		FieldError error = new FieldError(objectName, fieldName, fieldValue, false, new String[] { errorCode },
				new Object[] {}, errorCode);

		result.addError(error);
	}
}
