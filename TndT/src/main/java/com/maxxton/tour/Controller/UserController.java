package com.maxxton.tour.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxxton.tour.DTO.UserDto;
import com.maxxton.tour.entities.Booking;
import com.maxxton.tour.entities.Bstatus;
import com.maxxton.tour.entities.EmailDetails;
import com.maxxton.tour.entities.Tour;
import com.maxxton.tour.entities.User;
import com.maxxton.tour.helper.JwtUtil;
import com.maxxton.tour.repository.BookingRepo;
import com.maxxton.tour.repository.TourRepo;
import com.maxxton.tour.repository.UserRepo;
import com.maxxton.tour.service.EmailService;
import com.maxxton.tour.service.UserService;

@RestController
@CrossOrigin(origins = "*")
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
	private BookingRepo bookingrepo;
	
	 @Autowired
	    private EmailService emailService;

	// CRUD for Booking by user
	// User adding new booking
	@PostMapping("/addBooking/{tourid}")
	public ResponseEntity<Booking> addTour(@PathVariable("tourid") int tourid, @RequestBody Booking booking,
			HttpServletRequest req) {
		// EXTRACTING THE TOKEN
		String tokenwithBearer = req.getHeader("Authorization");

		// extracting the email from jwt token
		String email = jwtutil.getUsernameFromToken(tokenwithBearer.substring(7));
		System.out.println("hello   email from addbooking " + email);

		// finding user from email
		User user = userservice.userFindByEmail(email);

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
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {

			tour.setAvailableseats(tourgroupsize - bookinggroupsize);
			double price = tour.getPrice();
			if (bookinggroupsize >= 5) {
//				double price=tour.getPrice();
				price = price * booking.getGroupsize() * 0.8;
				booking.setPrice(price);

			} else {
//				double price=booking.getPrice();
				price = price * booking.getGroupsize() * 0.9;
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
/*
			System.out.println("hiii " + booking);
			String status
            = emailservice.sendSimpleMail(details);
        return status;
        */
			
			EmailDetails details=new EmailDetails();
	        details.setRecipient(email);
	        details.setMsgBody("Hey \n \n This is Booking Confiramation email \n\n Thank You For doing Booking with us!!!");
	        details.setSubject("Booking Confirmation Email");
	        String status= emailService.sendSimpleMail(details);
			
			System.out.println("hello  at end ");
			return new ResponseEntity<>(booking, HttpStatus.OK);
		}
	}

	public ResponseEntity<Booking> updateBookingDate(@PathVariable("id") int id, @RequestBody Booking updatedBooking) {
		Booking booking = userservice.getBookingById(id);

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

	@GetMapping("/getAllBooking")
	public ResponseEntity<List<Booking>> getAllBooking(HttpServletRequest req) {
		// EXTRACTING THE TOKEN
		String tokenwithBearer = req.getHeader("Authorization");

		// extracting the email from jwt token
		String email = jwtutil.getUsernameFromToken(tokenwithBearer.substring(7));
		System.out.println(email);

		// finding user from email
		User user = userservice.userFindByEmail(email);

		// getBookingList from user list
		List<Booking> bookinglist = user.getBooking();
		System.out.println(bookinglist);
		

		List<Booking> bookinglists = new ArrayList();
		for (int i = 0; i < bookinglist.size(); i++) {
			bookinglists.add(bookinglist.get(i));
			
		}
		System.out.println(bookinglists);
		//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if (bookinglists != null) {

			return new ResponseEntity<List<Booking>>(bookinglist, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/getBookingByBookingId/{bookingid}")
	public ResponseEntity<Booking> getBookingByBookingId(@PathVariable("bookingid") int bookingid,
			HttpServletRequest req) {
		// EXTRACTING THE TOKEN
		String tokenwithBearer = req.getHeader("Authorization");

		// extracting the email from jwt token
		String email = jwtutil.getUsernameFromToken(tokenwithBearer.substring(7));
		System.out.println(email);

		// finding user from email
		User user = userservice.userFindByEmail(email);

		// getBooking from user Booking Table
		Optional<Booking> booking = bookingrepo.findById(bookingid);

		if (booking != null) {

			return new ResponseEntity<>(booking.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/*
	// group size update
	@PutMapping("/updateBooking/groupsize/{id}")
	public ResponseEntity<Booking> updateUser(@PathVariable("id") int id, @RequestBody Booking updatedBooking) {
		Booking booking = userservice.getBookingById(id);

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
*/
	// user can delete booking by id
	@DeleteMapping("/deleteBooking/{bid1}")
	public ResponseEntity<String> deleteUser(@PathVariable("bid1") String bid1) {
		System.out.println("I am in deleter+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		int bid=Integer.parseInt(bid1);
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
         //userservice.deleteBookingById(bid);
        booking.setStatus(Bstatus.CANCELLED);
      
		tour.setAvailableseats(tour.getAvailableseats()+booking.getGroupsize());
		bookingrepo.delete(booking);
         
		System.out.println("end of delete");
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
			else {
				 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
			return new ResponseEntity<List<Booking>>(bookinglist,HttpStatus.ACCEPTED);
		}
		return null;
		
	}
	

	@GetMapping("/getbookinghistory/{id}")
	public ResponseEntity<List<Booking>> getAllBookings(@PathVariable("id") int id) {
		User user = userRepo.findById(id).orElse(null);

		if (user != null) {
			List<Booking> bookinglist = user.getBooking();
			return new ResponseEntity<List<Booking>>(bookinglist, HttpStatus.NO_CONTENT);
		}
		return null;

	}

	// update profile
	@PutMapping("/updateprofile")
	public ResponseEntity<UserDto> updateProfile(@RequestBody UserDto user)
	{
	System.out.println("I am in userprofile controller");
	System.out.println(user.getEmail());
		userservice.profileupdate(user);
		return new ResponseEntity<UserDto>(user,HttpStatus.ACCEPTED);
	}
	// getallusers
	@GetMapping("/getallusers")
	public ResponseEntity<List<User>> getallusers() {
		System.out.println("ok");
		List<User> users = userRepo.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.ACCEPTED);

	}

	@GetMapping("/getallusers/{id}")
	public ResponseEntity<User> getallusers(@PathVariable int id) {
		System.out.println("ok");
		User user = userRepo.findById(id).orElse(null);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
		}
		return null;

	}
	
	
	@GetMapping("/getallusersbylogin")
	public ResponseEntity<User> getallusers1( HttpServletRequest req)
	{
		
		String tokenwithBearer = req.getHeader("Authorization");

		// extracting the email from jwt token
		String email = jwtutil.getUsernameFromToken(tokenwithBearer.substring(7));
		System.out.println(email);

		// finding user from email
		User user = userservice.userFindByEmail(email);
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
		
	}
	
}
