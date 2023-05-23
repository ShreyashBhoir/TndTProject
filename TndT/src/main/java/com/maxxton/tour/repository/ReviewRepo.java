package com.maxxton.tour.repository;

import org.springframework.data.repository.CrudRepository;

import com.maxxton.tour.entities.Review;

public interface ReviewRepo extends CrudRepository<Review, Integer> {

}
