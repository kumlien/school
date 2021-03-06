package com.kumliens.school.user;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Sets;


@RestController
@RequestMapping("users")
public class UserController {
	
	private static final int MIN_PWD_LENGTH = 5;

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private final UserRepo userRepo;
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserController (final UserRepo userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	@RequestMapping(value="{id}")
	@PostAuthorize("(returnObject.body != null and returnObject.body.username == principal.username) or hasRole('ADMIN')")
	public ResponseEntity<UserResponse> getUserById(@PathVariable final Integer id) {
		final SchoolUser user = this.userRepo.findOne(id);
		if(user == null) {
			return new ResponseEntity<UserResponse>(HttpStatus.NOT_FOUND);
		}
		ResponseEntity<UserResponse> responseEntity = new ResponseEntity<UserResponse>(new UserResponse(user), HttpStatus.OK);
		return responseEntity;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public Iterable<SchoolUser> findAll() {
		return this.userRepo.findAll();
	}
	
	/**
	 * Create a new user.
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public ResponseEntity<?> createUser(@RequestBody @Valid final CreateUserRequest request) {
		logger.info("About to create new user from request {}", request);

		if(!passwordsOk(request.password1, request.password2)) {
			return new ResponseEntity<String>("Bad passwords", HttpStatus.BAD_REQUEST);
		}
		
		final String encryptedPwd = encryptPwd(request.password1);
		
		if(this.userRepo.findByUsername(request.username) != null) {
			return new ResponseEntity<String>("That username is already taken", HttpStatus.BAD_REQUEST);
		}
		
		SchoolUser user = new SchoolUser(request.username, encryptedPwd, request.firstName, request.lastName, request.email, Sets.newHashSet(request.role));
		user = this.userRepo.save(user);
		final UserResponse response = new UserResponse(user);
		logger.info("User created: {}", user);
		return new ResponseEntity<UserResponse>(response, HttpStatus.CREATED);
	}

	
	private final String encryptPwd(final String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
	

	private static final boolean passwordsOk(final String password1, final String password2) {
		if(!StringUtils.hasText(password1)) return false;
		if(!StringUtils.hasText(password2)) return false;
		if(!password1.equals(password2)) return false;
		if(!(password1.length() > MIN_PWD_LENGTH)) return false;
		return true;
	}
}
