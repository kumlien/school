package com.kumliens.school.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kumliens.school.entities.User;
import com.kumliens.school.repos.UserRepo;
import com.kumliens.school.web.requests.CreateUserRequest;
import com.kumliens.school.web.responses.UserResponse;


@RestController
@RequestMapping("users")
public class UserController {
	
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private final UserRepo userRepo;
	
	@Autowired
	public UserController (UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Iterable<User> findAll() {
		return userRepo.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
		logger.info("About to create new user from request {}", request);
		
		//TODO Validate pwd, encrypt
		User user = new User(request.username, request.firstName, request.lastName, request.password1, request.email);
		user = userRepo.save(user);
		UserResponse response = new UserResponse(user.getId());
		return new ResponseEntity<UserResponse>(response, HttpStatus.CREATED);
	}
}
