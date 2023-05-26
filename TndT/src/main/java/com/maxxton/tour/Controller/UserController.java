package com.maxxton.tour.Controller;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

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
import com.maxxton.tour.entities.Bstatus;
import com.maxxton.tour.entities.Tour;
import com.maxxton.tour.entities.User;
import com.maxxton.tour.helper.JwtUtil;
import com.maxxton.tour.repository.BookingRepo;
import com.maxxton.tour.repository.TourRepo;
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
	
	@Autowired
	private TourRepo tourrepo;
	@Autowired
	BookingRepo bookingrepo;
	
	    //CRUD for Booking by user
        //User adding new booking
	@PostMapping("/addBooking/{tourid}")
	public ResponseEntity<Booking> addTour(@PathVariable("tourid") int tourid, @RequestBody Booking booking,
			HttpServletRequest req) {
		// EXTRACTING THE TOKEN
		String tokenwithBearer = req.getHeader("Authorization");

		// extracting the email from jwt token
		String email = jwtutil.getUsernameFromToken(tokenwithBearer.substring(7));
		System.out.println("hello   email from addbooking " + email);

		// finding user from email
		User user = userservice.userFindByEmail(email)
;

		System.out.println("hello   user from addbooking " + user);
		// finding tour from tourid
		Tour tour = tourrepo.getById(tourid);

		System.out.println("hello   tour from addbooking " + user);
		// GETTING GROUP SIZE OF TOUR
		int tourgroupsize = tour.getAvailableseats();
		System.out.println("hello   value from tourGroupSize " + tourgroupsize);
		// GETTING GROUP SIZE OF BOOKING
		int bookinggroupsize = booking.getGroupsize();

		int value = tourgroupsize - bookinggroupsize;

		System.out.println("hello   value from addbooking " + value);

		if (value <= 0) {
			// error throw krna hoga
			tour.setIstouractive(false);
			tourrepo.save(tour);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {

			tour.setAvailableseats((tourgroupsize - bookinggroupsize));
			
			double price = tour.getPrice();
			if (bookinggroupsize >= 5) {
//				double price=tour.getPrice();
				price = price*booking.getGroupsize()*0.8;
				booking.setPrice(price);

			} else {
//				double price=booking.getPrice();
				price = price*booking.getGroupsize()*0.9;
				booking.setPrice(price);
			}
			// setting tour in booking side and user in booking side
			booking.setTour(tour);

			booking.setUser(user);

			// getting and setting list of booking in tour side
			List<Booking> booklisttour = tour.getBooking();
			booklisttour.add(booking);
			tour.setBooking(booklisttour);

			// getting and setting list of booking in user side
			List<Booking> bookinglistuser = user.getBooking();
			bookinglistuser.add(booking);
			user.setBooking(bookinglistuser);

			// saving all the details
			userRepo.save(user);
			tourrepo.save(tour);
			bookingrepo.save(booking);

			System.out.println("hiii " + booking);
			return new ResponseEntity<>(booking, HttpStatus.OK);
		}
	}
		//user can  see booking history
		@GetMapping("/getBooking")
	    public ResponseEntity<List<Booking>> getUser(HttpServletRequest req)
		{
			String token=req.getHeader("Bearer");
			System.out.println(req.getHeader("Bearer"));
			System.out.println("token "+token);
			String email=jwtutil.getUsernameFromToken(token);
			User user=userRepo.findByEmail(email);
			List<Booking> bookings=bookingrepo.getBookinghistory(user.getUserId());
	       return new ResponseEntity<List<Booking>>(bookings,HttpStatus.ACCEPTED);
		}
		
		//getAllBooking
		@GetMapping("/getAllBooking")
		public ResponseEntity<List<Booking>> getAllBooking(HttpServletRequest req) {
			// EXTRACTING THE TOKEN
			String tokenwithBearer = req.getHeader("Authorization");

			// extracting the email from jwt token
			String email = jwtutil.getUsernameFromToken(tokenwithBearer.substring(7));
			System.out.println(email)
	;

			// finding user from email
			User user = userservice.userFindByEmail(email)
	;

			// getBookingList from user list
			List<Booking> bookinglist = user.getBooking();
			System.out.println(bookinglist);
			

			
			//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			if (bookinglist != null) {

				return new ResponseEntity<List<Booking>>(bookinglist, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		}

		//getAllBooking by id
		@GetMapping("/getBookingByBookingId/{bookingid}")
		public ResponseEntity<Booking> getBookingByBookingId(@PathVariable("bookingid") int bookingid,
				HttpServletRequest req) {
			// EXTRACTING THE TOKEN
			String tokenwithBearer = req.getHeader("Authorization");

			// extracting the email from jwt token
			String email = jwtutil.getUsernameFromToken(tokenwithBearer.substring(7));
			System.out.println(email)
	;

			// finding user from email
			User user = userservice.userFindByEmail(email)
	;

			// getBooking from user Booking Table
			Optional<Booking> booking = bookingrepo.findById(bookingid);

			if (booking != null) {

				return new ResponseEntity<>(booking.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	        
	        
	      
		
		//user can update
		//bookingdate
		//group size
		
		 
//			//group size update
//		 @PutMapping("/updateBooking/groupsize/{id}")
//		    public ResponseEntity<Booking> updateUser(@PathVariable("id") int id, @RequestBody Booking updatedBooking) {
//			 Booking booking  = userservice.getBookingById(id);
//		        
//		        if (booking != null) {
//		            // Update the user with the new data
//		         
//		            booking.setGroupsize(updatedBooking.getGroupsize());
//		            // ... other properties
//		            
//		            userservice.addBooking(booking);
//		            
//		            return new ResponseEntity<>(booking, HttpStatus.OK);
//		        } else {
//		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		        }
//		    }
		
		//user can delete booking by id
		@DeleteMapping("/deleteBooking/{bid}")
		public ResponseEntity<String> deleteUser(@PathVariable("bid") int bid) {
			Booking booking = userservice.getBookingById(bid);
			if(booking==null)
			{
				 System.out.println("Booking is null");
				 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else
			{
				 Tour tour=booking.getTour();
				long milliseconds=tour.getBegindate().getTime()-booking.getBookingdate().getTime();
				long days = milliseconds / (1000*60*60 * 24);
				if(days>=15) 
				{
	         userservice.deleteBookingById(bid);
	        booking.setStatus(Bstatus.CANCELLED);
	      
			tour.setAvailableseats(tour.getAvailableseats()+booking.getGroupsize());
	         
	         return new ResponseEntity<>("USER DELETED WITH ID"+bid,HttpStatus.NO_CONTENT);
			}
				else {
					 return new ResponseEntity<>(HttpStatus.ACCEPTED);
				}
			}
		}
		
		@GetMapping("/getbookinghistory")
		public ResponseEntity<List<Booking>> getAllBookings(HttpServletRequest req)
		{
			String tokenwithBearer = req.getHeader("Authorization");

			// extracting the email from jwt token
			String email = jwtutil.getUsernameFromToken(tokenwithBearer.substring(7));
			System.out.println(email)
	;

			// finding user from email
			User user = userservice.userFindByEmail(email)
	;
			
			if(user !=null) {
				List<Booking>bookinglist=user.getBooking();
				return new ResponseEntity<List<Booking>>(bookinglist,HttpStatus.NO_CONTENT);
			}
			return null;
			
		}
		
		//update profile
		@PutMapping("/updateprofile")
		public ResponseEntity<User> updateProfile(@RequestBody User user)
		{
			userservice.profileupdate(user);
			return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
		}
		
		//getallusers
		@GetMapping("/getallusers")
		public ResponseEntity<List<User>> getallusers()
		{
			System.out.println("ok");
			List<User>users=userRepo.findAll();
			return new ResponseEntity<List<User>>(users, HttpStatus.ACCEPTED);
			
		}
		//get all users by id
		@GetMapping("/getallusers/{id}")
		public ResponseEntity<User> getallusers(@PathVariable int id)
		{
			System.out.println("ok");
			User user=userRepo.findById(id).orElse(null);
			if(user !=null) {
				return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);	
			}
			return null;
		
			
		}
		
		
}
