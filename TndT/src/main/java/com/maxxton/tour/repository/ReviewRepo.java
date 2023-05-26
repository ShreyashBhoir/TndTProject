package com.maxxton.tour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.maxxton.tour.entities.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer> {
 List<Review> findByTour(int tourId);
}
