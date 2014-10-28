package com.kumliens.school.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CreateUserRequest {
	
	@NotNull
	public final String username;
	
	@NotNull
	@Size(min=6, message="password must be at lest six chars long")
	public final String password1;
	
	@NotNull
	@Size(min=6, message="password must be at least six chars long")
	public final String password2;
	
	@NotNull
	@Size(min=2, max=64, message="first name must be between two and 64 chars long")
	public final String firstName;
	
	@NotNull
	@Size(min=2, max=64, message="last name must be between two and 64 chars long")
	public final String lastName;

	@Email
	public final String email;
	
	@JsonCreator
	public CreateUserRequest(@JsonProperty("username") String username, 
			@JsonProperty("password1") String password1,
			@JsonProperty("password2")String password2, 
			@JsonProperty("firstname")String firstName, 
			@JsonProperty("lastname")String lastName,
			@JsonProperty("email")String email) {
		this.username = username;
		this.password1 = password1;
		this.password2 = password2;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	@Override
	public String toString() {
		return "CreateUserRequest [username=" + username + ", password1="
				+ password1 + ", password2=" + password2 + ", firstName="
				+ firstName + ", lastName=" + lastName + "]";
	}
	
	


}
