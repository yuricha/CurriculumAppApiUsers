package com.curriculumapp.api.users.model;

import lombok.Data;

@Data
public class LoginRequestModel {
	private String email;
	private String password;
	
	public LoginRequestModel(){}
}
