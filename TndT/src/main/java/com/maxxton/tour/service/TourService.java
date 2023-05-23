package com.maxxton.tour.service;

import java.util.List;

import com.maxxton.tour.entities.Tour;

public interface TourService {

	//Adding new tour
	public void addNewTour(Tour tour);
	
	public void updateTour(Tour tour);

	public  List<Tour> getTours();

	public Tour getTourByid(int id);
	
	public Tour deleteTour(int id);

	
}