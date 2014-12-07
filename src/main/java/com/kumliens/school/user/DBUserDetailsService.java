package com.kumliens.school.user;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

@Service
public class DBUserDetailsService implements UserDetailsService {

	private final UserRepo userRepo;

	@Autowired
	public DBUserDetailsService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		SchoolUser user = userRepo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
		User u = new User(username, user.getEncryptedPwd(), getGrantedAuthorities(user));
		return u;
	}

	
	//TODO lambdas
	private Collection<? extends GrantedAuthority> getGrantedAuthorities(SchoolUser user) {
		Set<SimpleGrantedAuthority> authorities = Sets.newHashSet();
		user.getRoles().forEach(r -> {
			authorities.add(new SimpleGrantedAuthority(r.name()));
		});
		
		return authorities;
	}

}
