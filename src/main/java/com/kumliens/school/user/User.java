package com.kumliens.school.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Size(min=6, max=64)
	private String username;

	@NotNull
	private  String encryptedPwd;

	@NotNull
	private  String firstName;

	@NotNull
	private  String lastName;
	
	@Email
	private  String email;

	@CreatedDate
	private Date createdDate;
	
	@LastModifiedDate
	private Date modifiedDate;
	
	//Required according to JPA spec
	protected User(){}

	public User(String username, String encryptedPwd, String firstName,
			String lastName, String email) {
		this.username = username;
		this.encryptedPwd = encryptedPwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEncryptedPwd() {
		return encryptedPwd;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

}
