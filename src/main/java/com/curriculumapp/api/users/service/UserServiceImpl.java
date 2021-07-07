package com.curriculumapp.api.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.curriculumapp.api.users.base.ResponseDto;
import com.curriculumapp.api.users.common.Values;
import com.curriculumapp.api.users.data.UserEntity;
import com.curriculumapp.api.users.data.UserRepository;
import com.curriculumapp.api.users.exception.UserNameNotFoundException;
import com.curriculumapp.api.users.model.ExperiencesResponseModel;
import com.curriculumapp.api.users.model.UserResponseModel;
import com.curriculumapp.api.users.shared.UserDto;

import feign.FeignException;

@Service
public class UserServiceImpl implements UserService {
	UserRepository userRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	ExperiencesServiceClient expServicesClient;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	public UserServiceImpl (UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder,ExperiencesServiceClient expServicesClient) {
		this.userRepository=userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.expServicesClient = expServicesClient;
	}
	
	@Override
	public ResponseDto saveUser(UserDto userDto) {
		
		userDto.setUserId(UUID.randomUUID().toString());
		logger.info("request name ........"+userDto.getFirstName());
		userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));		
		logger.info("request password encrypted..... {}",userDto.getEncryptedPassword());
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		
		userRepository.save(userEntity);
		/*
		 * object capa media
		 */
		UserDto userDtoReturn = modelMapper.map(userEntity, UserDto.class);			

		/*
		 * object capa externa
		 */
		UserResponseModel userResponseReturn = modelMapper.map(userDtoReturn, UserResponseModel.class); //

		return new ResponseDto(Values.APP_CODE_OK, userResponseReturn);

	}

	@Override
	public UserDto getUserByUserId(String userId) throws UserNameNotFoundException  {
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity==null)throw new UserNameNotFoundException("User not found");
				

		UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
		
		List<ExperiencesResponseModel>listExperiencesResponseModels=null;
		try {
			logger.info("Before calling albums Microservices ");
			listExperiencesResponseModels=expServicesClient.getExperiences(userId);// call microservice album with feign mode
			logger.info("After calling albums Microservices");
		}catch (FeignException e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		userDto.setExperiences(listExperiencesResponseModels);
		return userDto;
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);

		if(userEntity==null) throw new UsernameNotFoundException(email); // this case the userName its email
		
		logger.info("userentity login {}", userEntity.getEmail());
		
		
		//return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),true,true,true,true, new ArrayList<>());
		return new ModelMapper().map(userEntity, UserDto.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(username);
		
		
		if(userEntity==null) throw new UsernameNotFoundException(username); // this case the userName its email
		
		logger.info("userentity login {}", userEntity.getEmail());
		
		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),true,true,true,true, new ArrayList<>());
	}

}
