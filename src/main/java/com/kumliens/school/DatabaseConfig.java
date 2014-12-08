package com.kumliens.school;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.collect.Sets;
import com.kumliens.school.user.Role;
import com.kumliens.school.user.SchoolUser;
import com.kumliens.school.user.UserRepo;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class DatabaseConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);

	@Autowired UserRepo userRepo;
	
	@Autowired PasswordEncoder encoder;
	
	@PostConstruct
	public void initDummyUser() {
//		SchoolUser user = new SchoolUser("svante", encoder.encode("vlhwhj"), "Svante", "Kumlien", "svante.kumlien@gmail.com", Sets.newHashSet(Role.ADMIN, Role.PARENT));
//		user = userRepo.save(user);
//		LOGGER.info("Svante created with id {}", user.getId());
	}
}
