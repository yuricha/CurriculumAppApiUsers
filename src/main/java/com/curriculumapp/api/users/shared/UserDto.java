package com.curriculumapp.api.users.shared;


import java.util.List;

import com.curriculumapp.api.users.model.ExperiencesResponseModel;

import lombok.Data;

@Data
public class UserDto {

	private String firstName;	
	private String lastName;
	private String password;
	private String email;
	private String encryptedPassword;
	private String userId;
	private String contact;
	private List<ExperiencesResponseModel> experiences;
	
}
