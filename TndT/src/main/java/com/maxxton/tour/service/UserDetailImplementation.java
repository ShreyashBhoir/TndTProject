package com.maxxton.tour.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.maxxton.tour.entities.User;

public class UserDetailImplementation implements UserDetails {
	

	private String userName;
	private String password;
	private boolean isActive;
	private List<GrantedAuthority> authorities;

	public UserDetailImplementation(User user) {
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.isActive = user.getIsActive();
		
		this.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRoles()));
	}

	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {

		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
