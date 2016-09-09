package com.mvc.timemachine.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsNotEmptyValidator implements ConstraintValidator<PasswordsNotEmpty, Object> {

	private String fieldName;
	private String passwordFieldName;
	private String passwordVerificationFieldName;

	public void initialize(PasswordsNotEmpty constraintAnnotation) {
		fieldName = constraintAnnotation.triggerFieldName();
		passwordFieldName = constraintAnnotation.passwordFieldName();
		passwordVerificationFieldName = constraintAnnotation.passwordVerificationFieldName();
	}


	private boolean passwordFieldsAreValid(Object value, ConstraintValidatorContext context)
			throws NoSuchFieldException, IllegalAccessException {
		boolean passwordWordFieldsAreValid = true;

		String password = (String) ValidatorUtil.getFieldValue(value, passwordFieldName);
		if (isNullOrEmpty(password)) {
			ValidatorUtil.addValidationError(passwordFieldName, context);
			passwordWordFieldsAreValid = false;
		}

		String passwordVerification = (String) ValidatorUtil.getFieldValue(value, passwordVerificationFieldName);
		if (isNullOrEmpty(passwordVerification)) {
			ValidatorUtil.addValidationError(passwordVerificationFieldName, context);
			passwordWordFieldsAreValid = false;
		}

		return passwordWordFieldsAreValid;
	}

	private boolean isNullOrEmpty(String field) {
		return field == null || field.trim().isEmpty();
	}
	
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		try {
			Object validationTrigger = ValidatorUtil.getFieldValue(value, fieldName);
			if (validationTrigger == null) {
				return passwordFieldsAreValid(value, context);
			}
		} catch (Exception ex) {
			throw new RuntimeException("Exception occurred during validation", ex);
		}

		return true;
	}

}
