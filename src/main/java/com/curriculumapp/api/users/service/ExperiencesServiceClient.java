package com.curriculumapp.api.users.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.curriculumapp.api.users.model.ExperiencesResponseModel;

@FeignClient(name="experiences-ws", fallback=ExperiencesFallBack.class)
public interface ExperiencesServiceClient {
	@GetMapping("/users/{id}/experiences")	
	public List<ExperiencesResponseModel> getExperiences(@PathVariable String id);
}

@Component
class ExperiencesFallBack implements ExperiencesServiceClient
{

	@Override
	public List<ExperiencesResponseModel> getExperiences(String id) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}
	
}