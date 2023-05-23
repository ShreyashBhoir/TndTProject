package com.maxxton.tour.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.maxxton.tour.entities.Tour;
import com.maxxton.tour.repository.TourRepo;
import com.maxxton.tour.service.TourService;

public class TourServiceImpl implements TourService {

	//TourRepo reference
	@Autowired
    private	TourRepo tourRepo;
	
	
	@Override
	public void addNewTour(Tour tour) 
	{
	tourRepo.save(tour);
		
	}

}