package com.maxxton.tour.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.maxxton.tour.entities.User;
import com.maxxton.tour.repository.UserRepo;

import java.util.ArrayList;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	System.out.println(userName);
        User user = this.userRepository.findByEmail(userName);
        System.out.println("User"+user);

        if (user == null) {
            throw new UsernameNotFoundException("User not found !!");
        } else {
            return new UserDetailsImpl(user);
        }
    }
}