package com.maxxton.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.maxxton.tour.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String email);
	
}
