package com.kumliens.school.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;

	private  String encryptedPwd;

	private  String firstName;

	private  String lastName;
	
	private  String email;

	@CreatedDate
	private Date createdDate;
	
	@LastModifiedDate
	private Date modifiedDate;
	
	//Needed for Hibernate.
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
