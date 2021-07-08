package com.curriculumapp.api.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import feign.Logger;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CurriculumAppApiUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurriculumAppApiUsersApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	/*
	 * missing  config BCrypPassword bCryptPasswordEncoder	
	 * RestTemplate conf
	 */
	@Bean
	Logger.Level feignLoggerLevel()
	{
		return Logger.Level.FULL;
	}

}
