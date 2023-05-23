package com.maxxton.tour.repository;

import org.springframework.data.repository.CrudRepository;

import com.maxxton.tour.entities.Tour;

public interface TourRepo extends CrudRepository<Tour, Integer> {

}
