package com.curriculumapp.api.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.curriculumapp.api.users.base.ResponseDto;
import com.curriculumapp.api.users.exception.UserNameNotFoundException;
import com.curriculumapp.api.users.shared.UserDto;

public interface UserService extends UserDetailsService{
	ResponseDto saveUser(UserDto userDto);
	UserDto getUserByUserId(String userId)throws UserNameNotFoundException;
	UserDto getUserDetailsByEmail(String email);	
}
