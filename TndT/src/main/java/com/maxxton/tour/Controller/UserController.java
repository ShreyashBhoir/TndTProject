package com.maxxton.tour.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxxton.tour.entities.Booking;
import com.maxxton.tour.entities.User;
import com.maxxton.tour.helper.JwtUtil;
import com.maxxton.tour.repository.UserRepo;
import com.maxxton.tour.service.UserService;

@RestController
@RequestMapping("/user/user/booking")
public class UserController {
  
	@Autowired
	private JwtUtil jwtutil;
	@Autowired
	private UserService userservice;
	
	@Autowired
	private UserRepo userRepo;
	
	    //CRUD for Booking by user
        //User adding new booking
		@PostMapping("/addBooking")
		public ResponseEntity<Booking> addTour(@RequestBody Booking booking,HttpServletRequest req) 
		{
			
			String token=req.getHeader("Bearer");
			System.out.println(req.getHeader("Bearer"));
			//System.out.println(jwt)
			String email=jwtutil.getUsernameFromToken(token);
			System.out.println(email);
			userservice.addBooking(booking);
			return new ResponseEntity<Booking>(booking,HttpStatus.ACCEPTED);
		}
		
		
		//user can get booking by id
		@GetMapping("/getBooking/{id}")
	    public ResponseEntity<Booking> getUser(HttpServletRequest req,@PathVariable("id") int id) {
			String token=req.getHeader("Bearer");
			System.out.println(req.getHeader("Bearer"));
			//System.out.println(jwt)
			String email=jwtutil.getUsernameFromToken(token);
			System.out.println("hi ");
	        Booking booking = userservice.getBookingById(id);
	        
	        if (booking != null) {
	            return new ResponseEntity<>(booking, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    } 
		
		//user can update
		//bookingdate
		//group size
		
		//bookingdate update
		 @PutMapping("/updateBooking/bookingdate/{id}")
		    public ResponseEntity<Booking> updateBookingDate(@PathVariable("id") int id, @RequestBody Booking updatedBooking) {
			 Booking booking  = userservice.getBookingById(id);
		        
		        if (booking != null) {
		            // Update the user with the new data
		         
		            booking.setBookingdate(updatedBooking.getBookingdate());
		            // ... other properties
		            
		            userservice.addBooking(booking);
		            
		            return new ResponseEntity<>(booking, HttpStatus.OK);
		        } else {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		    }
		
		 
			//group size update
		 @PutMapping("/updateBooking/groupsize/{id}")
		    public ResponseEntity<Booking> updateUser(@PathVariable("id") int id, @RequestBody Booking updatedBooking) {
			 Booking booking  = userservice.getBookingById(id);
		        
		        if (booking != null) {
		            // Update the user with the new data
		         
		            booking.setGroupsize(updatedBooking.getGroupsize());
		            // ... other properties
		            
		            userservice.addBooking(booking);
		            
		            return new ResponseEntity<>(booking, HttpStatus.OK);
		        } else {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		    }
		
		//user can delete booking by id
		@DeleteMapping("/deleteBooking/{id}")
		public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
			Booking booking = userservice.getBookingById(id);
			if(booking==null)
			{
				 System.out.println("Booking is null");
				 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else
			{
	         userservice.deleteBookingById(id);
	         return new ResponseEntity<>("USER DELETED WITH ID"+id,HttpStatus.NO_CONTENT);
			}
		}
		
		@GetMapping("/getbookinghistory/{id}")
		public ResponseEntity<List<Booking>> getAllBookings(@PathVariable("id") int id)
		{
			User user=userRepo.findById(id).orElse(null);
			
			
			if(user !=null) {
				List<Booking>bookinglist=user.getBooking();
				return new ResponseEntity<List<Booking>>(bookinglist,HttpStatus.NO_CONTENT);
			}
			return null;
			
		}
		
}
