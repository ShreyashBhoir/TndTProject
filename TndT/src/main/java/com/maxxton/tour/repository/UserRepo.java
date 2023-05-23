package com.maxxton.tour.repository;

import org.springframework.data.repository.CrudRepository;

import com.maxxton.tour.entities.User;

public interface UserRepo extends CrudRepository<User, Integer> {

}
