package com.kumliens.school.web.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CreateUserRequest {
	
	public final String username;
	
	public final String password1;
	
	public final String password2;
	
	public final String firstName;
	
	public final String lastName;

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
