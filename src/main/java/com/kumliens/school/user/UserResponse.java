package com.kumliens.school.user;


public class UserResponse {
	
	public final Integer id;
	public String email;
	public String firstName;
	public String lastName;
	public String username;

	public UserResponse(SchoolUser user) {
		this.id = user.getId();
		email = user.getEmail();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		username = user.getUsername();
	}
	
	
}
