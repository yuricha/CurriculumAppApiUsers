package com.curriculumapp.api.users.model;

import lombok.Data;

@Data
public class ExperiencesResponseModel {
	private String experienceId;
	private String userId;
	private String name;
	private String description;
	
	
	public ExperiencesResponseModel() {
		
	}

}
