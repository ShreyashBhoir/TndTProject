package com.maxxton.tour.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxxton.tour.entities.Booking;
import com.maxxton.tour.service.UserService;

@RestController
@RequestMapping("/user/user/booking")
public class UserController {
  
	@Autowired
	private UserService userservice;
	
	    //CRUD for Booking by user
        //User adding new booking
		@PostMapping("/addBooking")
		public ResponseEntity<Booking> addTour(@RequestBody Booking booking) 
		{
			userservice.addBooking(booking);
			return new ResponseEntity<Booking>(booking,HttpStatus.ACCEPTED);
		}
		
		@GetMapping("/getBooking/{id}")
	    public ResponseEntity<Booking> getUser(@PathVariable("id") int id) {
	        Booking booking = userservice.getBookingById(id);
	        
	        if (booking != null) {
	            return new ResponseEntity<>(booking, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    } 
	
 
}
