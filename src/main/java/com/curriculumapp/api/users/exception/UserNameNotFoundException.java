package com.curriculumapp.api.users.exception;

public class UserNameNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNameNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
