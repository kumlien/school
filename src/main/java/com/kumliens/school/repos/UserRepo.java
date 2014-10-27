package com.kumliens.school.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kumliens.school.entities.User;

@Repository
public interface UserRepo extends JpaRepository <User, Integer> {
	
	User findByUsername(String username);

}
