package com.curriculumapp.api.users.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curriculumapp.api.users.base.ResponseDto;
import com.curriculumapp.api.users.common.HttpStatusUtil;
import com.curriculumapp.api.users.common.Values;
import com.curriculumapp.api.users.exception.UserNameNotFoundException;
import com.curriculumapp.api.users.model.UserRequestModel;
import com.curriculumapp.api.users.service.UserService;
import com.curriculumapp.api.users.shared.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired	
	private Environment env;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/status/check")
	public String status() {		
		return "working on port "+ env.getProperty("local.server.port");
	}
	
    
    @GetMapping(value = "/{userId}")
    public ResponseEntity<String> getUser(@PathVariable("userId")  String userId)throws UserNameNotFoundException {
    	
		UserDto userDto = userService.getUserByUserId(userId);
		
        return new HttpStatusUtil().getHttpStatusByResponse(new ResponseDto(Values.APP_CODE_OK, userDto));       
    }
    
	
    @PostMapping(value = "/create", 
    		consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
    		produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> saveUser( @RequestBody UserRequestModel userRequestModel){
    	
    	ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto userDto = modelMapper.map(userRequestModel, UserDto.class);
		
        return new HttpStatusUtil().getHttpStatusByResponse(userService.saveUser(userDto));       
    }
}
