package com.maxxton.tour.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxxton.tour.entities.Booking;
import com.maxxton.tour.entities.Review;
import com.maxxton.tour.entities.User;
import com.maxxton.tour.repository.BookingRepo;
import com.maxxton.tour.repository.ReviewRepo;
import com.maxxton.tour.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BookingRepo bookingrepo;
	
	@Autowired
	private ReviewRepo reviewrepo;
	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public void addBooking(Booking booking) {
		
		bookingrepo.save(booking);
	}

	@Override
	public Booking getBookingById(int id) {
		
		Optional<Booking>  booking = bookingrepo.findById(id);
		
		
		if(booking.get()==null)
		{
			return null;
		}
		
		return booking.get();
	}

	@Override
	public void deleteBookingById(int id) {
		// TODO Auto-generated method stub
		bookingrepo.deleteById(id);
	}

	public void addReview(Review review) {
		// TODO Auto-generated method stub
		reviewrepo.save(review);
		
	}
	
	/*----SANKET----*/
	//@Autowired
	//public UserRepo userRepo;
	
	@Autowired
	public BookingRepo bookingRepo;
	
	/*ADMIN ACTIONS*/
	public boolean makeAdmin(int userId) {
		//try to get ref to entity to update
		Optional<User> userToUpdateOptional = userRepo.findById(userId);
		/*if no one present with id, Give null*/
		if(!userToUpdateOptional.isPresent()) {
			return false;
		}
		//get actual object
		User userToUpdate = userToUpdateOptional.get();
		//update its role
		userToUpdate.setRoles("admin");
		//save updated entity to db
		userRepo.save(userToUpdate);
		return true;	/*Updated*/
	}
	
	/*STATS*/
	public List<Booking> getStatsForYear(int year){
		List<Booking> stats;
		
		if(year==-1) {			//get all 
			stats = bookingRepo.findAll();
		}else {					//get for particular year
			stats = bookingRepo.getBookingsByYear(year);
		}
		
		return stats;
	}
	
//	public ArrayList<Booking> getStatsForMonth(int m){
//		ArrayList<Booking> allStats = new ArrayList<>();
//		return allStats;
//	}
	
	public List<Object[]> getUserAndBookings(){
		List<Object[]> allStats = bookingRepo.getUsersAndBookings();
		return allStats;
	}

	//update user profile
	@Override
	public void profileupdate(User user) 
	{
	User user1=userRepo.findById(user.getUserId()).orElse(null);
	if(user1 !=null)
	{
	user1.setUserName(user.getUserName());
	user1.setEmail(user.getEmail());
	userRepo.save(user1);
	}
		
	}
	
//	public ArrayList<> getReviewStats(){
//		ArrayList<Booking> allStats = new ArrayList<>();
//		return allStats;
//	}




	

}
