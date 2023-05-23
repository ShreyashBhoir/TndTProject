package com.maxxton.tour.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxxton.tour.entities.Booking;
import com.maxxton.tour.entities.Tour;
import com.maxxton.tour.entities.User;
import com.maxxton.tour.repository.TourRepo;
import com.maxxton.tour.repository.UserRepo;
import com.maxxton.tour.service.TourService;
import com.maxxton.tour.service.UserService;
import com.maxxton.tour.service.UserServiceImpl;
import com.maxxton.tour.service.UserServiceImplementation;

@RestController
@RequestMapping("/user/admin")
public class AdminController {
	
	//Tourservice reference
	@Autowired
    private	TourService tourService;
	
	@Autowired
	private TourRepo tourrepo;
	
	//CRUD for Tour by admin
	//Admin adding new tour
	@PostMapping("/addTour")
	public ResponseEntity<Tour> addTour(@RequestBody Tour tour) 
	{
		tourService.addNewTour(tour);
		return new ResponseEntity<Tour>(tour,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Tour>UpdateTour(@RequestBody Tour tour)
	{
		tourService.updateTour(tour);
		return new ResponseEntity<Tour>(tour,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getTours")
	public  ResponseEntity<List<Tour>> getAllTours()
	{
		List tours=tourService.getTours();
		return  new ResponseEntity<List<Tour>>(tours, HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/getTourById/{id}")
	public  ResponseEntity<Tour>getAllTours(@PathVariable int id)
	{
	
		return  new ResponseEntity<Tour>(tourService.getTourByid(id), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteTour/{id}")
	public  ResponseEntity<Tour>deleteTour(@PathVariable int id) 
	{
		
		return  new ResponseEntity<Tour>(tourService.deleteTour(id), HttpStatus.ACCEPTED);
	}
	
	
	/*------BY SANKET-----*/
	
	@Autowired
	public UserServiceImpl userService;

	//Admin can make user admin
	@PatchMapping("/makeadmin/{id}")
	public ResponseEntity<String> makeAdmin(@PathVariable int id){
		if(userService.makeAdmin(id))
			return ResponseEntity.ok("done");
		else
			return ResponseEntity.badRequest().body("Operation failed");
	}
	
	//getting all bookings
	@GetMapping("/stats")
	public ResponseEntity<List<Booking>> allStats(){
		List<Booking> result = userService.getStatsForYear(-1);
		if(result!=null)
			return new ResponseEntity<List<Booking>>(result,HttpStatus.OK);
		else
			return new ResponseEntity<List<Booking>>(result,HttpStatus.BAD_REQUEST);
	}
	
	//get stats for this year
	@GetMapping("/stats/{year}")
	public ResponseEntity<List<Booking>> allStatsOfYear(@PathVariable int year){
		List<Booking> result = userService.getStatsForYear(year);
		if(result!=null)
			return new ResponseEntity<List<Booking>>(result,HttpStatus.OK);
		else
			return new ResponseEntity<List<Booking>>(result,HttpStatus.BAD_REQUEST);
	}

	
	//user1-150, user2-0
	@GetMapping("/stats/userbookings")
	public ResponseEntity<List<Object[]>> getUserBookings(){
		List<Object[]> result = userService.getUserAndBookings();
		if(result!=null)
			return new ResponseEntity<List<Object[]>>(result,HttpStatus.OK);
		else
			return new ResponseEntity<List<Object[]>>(result,HttpStatus.BAD_REQUEST);
	}

}