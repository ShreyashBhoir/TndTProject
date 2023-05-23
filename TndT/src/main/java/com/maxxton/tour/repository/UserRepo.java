package com.maxxton.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.maxxton.tour.entities.User;


public interface UserRepo extends JpaRepository<User, Integer> {
	User findByEmail(String Email);

public interface UserRepo extends CrudRepository<User, Integer> {


}
