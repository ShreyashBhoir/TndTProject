package com.maxxton.tour.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.maxxton.tour.DTO.BookingDTO;
import com.maxxton.tour.DTO.TourDTO;
import com.maxxton.tour.entities.Booking;
import com.maxxton.tour.entities.Review;
import com.maxxton.tour.entities.Tour;
import com.maxxton.tour.entities.User;
import com.maxxton.tour.helper.JwtUtil;
import com.maxxton.tour.repository.TourRepo;
import com.maxxton.tour.repository.UserRepo;
import com.maxxton.tour.service.TourService;
import com.maxxton.tour.service.UserService;
import com.maxxton.tour.service.UserServiceImpl;

@RestController
@CrossOrigin(origins =  "*")
@RequestMapping("/user/admin")
public class AdminController {
	
	//Tourservice reference
	@Autowired
    private	TourService tourService;
	
	@Autowired
	private TourRepo tourrepo;
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private JwtUtil jwtutil;
	
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
	
	//get all tours
	@GetMapping("/getTours")
	public  ResponseEntity<List<Tour>> getAllTours()
	{
		List<Tour> tours=tourService.getTours();
		List<TourDTO> toursDtoList = new ArrayList<>();
		tours.forEach((e)->{toursDtoList.add(new TourDTO(e));});
		
		return  new ResponseEntity<List<Tour>>(tours, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getTopTours")
	public  ResponseEntity<List<Tour>> getTopTours()
	{
		List<Tour> tours=tourService.getTopTours();
		List<TourDTO> toursDtoList = new ArrayList<>();
		tours.forEach((e)->{toursDtoList.add(new TourDTO(e));});
		
		return  new ResponseEntity<List<Tour>>(tours, HttpStatus.ACCEPTED);
	}
	
	//get tour by id
	@GetMapping("/getTourById/{id}")
	public  ResponseEntity<Tour>getAllTours(@PathVariable int id)
	{
	
		return  new ResponseEntity<Tour>(tourService.getTourByid(id), HttpStatus.ACCEPTED);
	}
	
	//delete tour by id
	@DeleteMapping("/deleteTour/{id}")
	public  ResponseEntity<Tour>deleteTour(@PathVariable int id) 
	{
		
		return  new ResponseEntity<Tour>(tourService.deleteTour(id), HttpStatus.ACCEPTED);
	}
	
	//Admin block user
	@PutMapping("/blockuser/{id}")
	public  ResponseEntity<User>blockUser(@PathVariable int id)
	{
		User user=userrepo.findById(id).orElse(null);
		if(user !=null) {
			user.setRoles("Not Valid");
			return  new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
		}
	return null;
	}
	
	// Admin Reply to review
	@PostMapping("/reply/{reviewid}")
	public ResponseEntity<String>blockUser(@PathVariable int reviewid,@RequestBody String msg)
	{
	User user=userrepo.findById(1).orElse(null);
	if(user !=null) 
	{
		List<Review >review =user.getReview();
		for (Review review2 : review)
		{
			if(review2.getReviewid()==reviewid)
			{
				review2.setReview(msg);
			}
		}
		return  new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	}
	return null;
	}
	
	
	
	/*------BY SANKET-----*/
	
	@Autowired
	public UserServiceImpl userService;
	
	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> getUsers(){
		//List<User> userList = userService.getAllUsers();
		List<User> userList = userService.getAllUsers();
		//userList.remove(userList)
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	
	@GetMapping("/getAdminDetails")
	public ResponseEntity<User> getAdmin(HttpServletRequest req){
		String tokenwithBearer = req.getHeader("Authorization");

		// extracting the email from jwt token
		String email = jwtutil.getUsernameFromToken(tokenwithBearer.substring(7));
		System.out.println("hello   email from addbooking " + email);

		//userService.getAdminDetails(email);
		return new ResponseEntity<User>(userService.getAdminDetails(email),HttpStatus.OK);
	}

	//Admin can make user admin
	@PatchMapping("/makeadmin/{id}")
	public ResponseEntity<String> makeAdmin(@PathVariable int id){
		if(userService.makeAdmin(id)) {
			return new ResponseEntity<String>(new String("done"),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("operation failed",HttpStatus.BAD_REQUEST);
		}
	}
	//Admin can make user admin
	@PatchMapping("/makeuser/{id}")
		public ResponseEntity<String> makeUser(@PathVariable int id){
			if(userService.makeUser(id))
				return new ResponseEntity<String>(new String("done"),HttpStatus.OK);
			else
				return new ResponseEntity<String>("Operation failed",HttpStatus.BAD_REQUEST);
		}
	
	//enable user
	@PatchMapping("/enableUser/{id}")
	public ResponseEntity<String> enableUser(@PathVariable int id){
		if(userService.changeEnableStatus(id,true)) {
			return new ResponseEntity<String>(new String("done"),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("operation failed",HttpStatus.BAD_REQUEST);
		}
	}
	
	//disable user
		@PatchMapping("/disableUser/{id}")
		public ResponseEntity<String> disableUser(@PathVariable int id){
			if(userService.changeEnableStatus(id,false)) {
				return new ResponseEntity<String>(new String("done"),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("operation failed",HttpStatus.BAD_REQUEST);
			}
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
	public ResponseEntity<List<BookingDTO>> allStatsOfYear(@PathVariable int year){
		List<Booking> result = userService.getStatsForYear(year);
		List<BookingDTO> resultDTOList = new ArrayList<>();
		result.forEach((e)->resultDTOList.add(new BookingDTO(e)));
		
		if(result!=null)
			return new ResponseEntity<List<BookingDTO>>(resultDTOList,HttpStatus.OK);
		else
			return new ResponseEntity<List<BookingDTO>>(resultDTOList,HttpStatus.BAD_REQUEST);
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