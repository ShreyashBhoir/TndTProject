package com.maxxton.tour.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxxton.tour.entities.Tour;
import com.maxxton.tour.service.TourService;

@RestController
@RequestMapping("/user/admin")
public class AdminController {
	
	//Tourservice reference
	@Autowired
    private	TourService tourService;
	
	//CRUD for Tour by admin
	//Admin adding new tour
	@PostMapping("/addTour")
	public ResponseEntity<Tour> addTour(@RequestBody Tour tour) 
	{
		tourService.addNewTour(tour);
		return new ResponseEntity<Tour>(tour,HttpStatus.ACCEPTED);
	}

}
