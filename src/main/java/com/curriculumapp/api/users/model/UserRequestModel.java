package com.curriculumapp.api.users.model;

import java.io.Serializable;
import lombok.Data;
@Data
public class UserRequestModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String firstName;	
	private String lastName;
	private String password;
	private String email;
	
	public UserRequestModel() {
		
	}


}
