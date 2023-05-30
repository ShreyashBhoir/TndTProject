package com.maxxton.tour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxxton.tour.entities.Tour;
import com.maxxton.tour.repository.TourRepo;
import com.maxxton.tour.service.TourService;

@Service
public class TourServiceImpl implements TourService {

	//TourRepo reference
	@Autowired
    private	TourRepo tourRepo;
	
	
	@Override
	public void addNewTour(Tour tour) 
	{
	tourRepo.save(tour);
		
	}


	@Override
	public void updateTour(Tour tour) {
	
		Tour tour1=tourRepo.findById(tour.getTourid()).orElse(null);
		
		if(tour1!=null) {
			System.out.println("I am in");
		tour1.setTourid(tour.getTourid());
		tour1.setName(tour.getName());
		tour1.setLocation(tour.getLocation());
		tour1.setAvailableseats(tour.getAvailableseats());
		tour1.setDifficulty(tour.getDifficulty());
		tour1.setRating(tour.getRating());
		tour1.setPrice(tour.getPrice());
		tour1.setDescription((tour.getDescription()));
		tour1.setBegindate((tour.getBegindate()));
		tour1.setImageurl(tour.getImageurl());
		tourRepo.save(tour1);
		
		}
		
	}


	@Override
	public List<Tour> getTours() {
		
		return tourRepo.findAll();
	}
	
	@Override
	public List<Tour> getTopTours() {
		
		return tourRepo.getTopTours();
	}


	@Override
	public Tour getTourByid(int id) {
		Tour tour=tourRepo.findById(id).orElse(null);
		if(tour!=null) {
		
			return tour;
		}
		return null;
	}


	@Override
	public Tour deleteTour(int id) {
		Tour tour=tourRepo.findById(id).orElse(null);
		if(tour!=null) {
		tour.setIstouractive(false);
	   tourRepo.delete(tour);
		}
		return null;
	}

	
		
		
		
	
	

}