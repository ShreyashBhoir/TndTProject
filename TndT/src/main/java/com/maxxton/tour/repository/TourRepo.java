package com.maxxton.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.maxxton.tour.entities.Tour;

public interface TourRepo extends JpaRepository<Tour, Integer> {

}
