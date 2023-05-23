package com.maxxton.tour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.maxxton.tour.entities.User;
import com.maxxton.tour.repository.UserRepo;

@Component
public class UserServiceImplementation implements UserDetailsService {

	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		User user = repo.findByEmail(username);

		System.out.println(user);
		if (user == null)
			throw new UsernameNotFoundException("Invalid Credentials");

		return new UserDetailImplementation(user);
	}

}
