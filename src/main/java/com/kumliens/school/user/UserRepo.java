package com.kumliens.school.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository <SchoolUser, Integer> {
	
	SchoolUser findByUsername(@Param("username") String username);

}
