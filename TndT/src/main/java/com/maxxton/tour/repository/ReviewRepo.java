package com.maxxton.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.maxxton.tour.entities.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer> {

}
