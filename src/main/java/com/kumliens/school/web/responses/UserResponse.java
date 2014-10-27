package com.kumliens.school.web.responses;

import com.kumliens.school.entities.User;

public class UserResponse {
	
	public final Integer id;
	public String email;
	public String firstName;
	public String lastName;
	public String username;

	public UserResponse(User user) {
		this.id = user.getId();
		email = user.getEmail();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		username = user.getUsername();
	}
	
	
}
