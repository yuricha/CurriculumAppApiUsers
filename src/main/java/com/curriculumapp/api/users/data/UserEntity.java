package com.curriculumapp.api.users.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="users")
public class UserEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false, length=50)
	private String firstName;	
	
	@Column(nullable=false, length=50)
	private String lastName;
	
	//private String password;
	
	@Column(nullable=false, length=50,unique=true)
	private String email;
	private String encryptedPassword;
	private String userId;
	private String contact;

}
