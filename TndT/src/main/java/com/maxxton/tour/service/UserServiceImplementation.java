package com.maxxton.tour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.maxxton.tour.entities.User;
import com.maxxton.tour.repository.UserRepo;

public class UserServiceImplementation implements UserDetailsService {
	
	
	
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String userame) throws UsernameNotFoundException {
		User user =  repo.findByEmail(userame);
		if (user == null)
			throw new UsernameNotFoundException("Invalid Credentials");
		return new UserDetailImplementation(user);
	}

}
