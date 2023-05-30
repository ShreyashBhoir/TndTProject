package com.maxxton.tour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.maxxton.tour.entities.Tour;

public interface TourRepo extends JpaRepository<Tour, Integer> {

	@Query("Select t from Tour t order by t.avgRating desc")
	public List<Tour> getTopTours();
}
