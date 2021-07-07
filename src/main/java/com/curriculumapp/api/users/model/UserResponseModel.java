package com.curriculumapp.api.users.model;
import java.util.List;

import lombok.Data;
@Data
public class UserResponseModel {
	private String firstName;	
	private String lastName;
	private String userId;
	private String contact;
	private List<ExperiencesResponseModel> experiencies;
	public UserResponseModel() {
		
	}
	
}
