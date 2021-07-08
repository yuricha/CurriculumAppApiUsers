package com.curriculumapp.api.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.curriculumapp.api.users.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	private Environment environment;
	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	public WebSecurity(Environment environment,UserService userService,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.environment=environment;
		this.userService=userService;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	}

	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").permitAll(). //hasIpAddress(environment.getProperty("gateway.io"));
		
		anyRequest().authenticated().and().
		// make sure we use stateless session; session won't be used to
		// store user's state.
		exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		/*
		.and()
		.addFilter(getAuthenticationFilter());
		http.headers().frameOptions().disable();
		/**/
	} 

	/*
	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(userService, environment,authenticationManager());
		//authenticationFilter.setAuthenticationManager(authenticationManager());
		System.out.print("login custom path "+environment.getProperty("login.url.path"));
		authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
		return authenticationFilter;
	}/**/
	
	/**
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}/**/
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	// configure AuthenticationManager so that it knows from where to load
	// user for matching credentials
	// Use BCryptPasswordEncoder
	auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
	}
}