package com.maxxton.tour.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxxton.tour.DTO.UserDto;
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
	
	public List<User> getAllUsers(){
		List<User> userList = userRepo.findAll();
		return userList;
	}
	
	/*ADMIN ACTIONS*/
	public User getAdminDetails(String email) {
		User user = userRepo.findByEmail(email);
		
		/*if no one present with id, Give null*/
		if(user==null) {
			throw new RuntimeException("User not found");
		}
		
		return user;
	}
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
		userToUpdate.setRoles("ROLE_ADMIN");
		//save updated entity to db
		userRepo.save(userToUpdate);
		return true;	/*Updated*/
	}
	
	public boolean makeUser(int userId) {
		//try to get ref to entity to update
		Optional<User> userToUpdateOptional = userRepo.findById(userId);
		/*if no one present with id, Give null*/
		if(!userToUpdateOptional.isPresent()) {
			return false;
		}
		//get actual object
		User userToUpdate = userToUpdateOptional.get();
		//update its role
		userToUpdate.setRoles("ROLE_USER");
		//save updated entity to db
		userRepo.save(userToUpdate);
		return true;	/*Updated*/
	}
	
	//
	public boolean changeEnableStatus(int userId,boolean activeStatus) {
		//try to get ref to entity to update
		Optional<User> userToUpdateOptional = userRepo.findById(userId);
		/*if no one present with id, Give null*/
		if(!userToUpdateOptional.isPresent()) {
			return false;
		}
		//get actual object
		User userToUpdate = userToUpdateOptional.get();
		//update its activeStatus
		if(userToUpdate.getRoles()=="ROLE_ADMIN") {
			return false;
		}
		
		userToUpdate.setIsActive(activeStatus);;
		//save updated entity to db
		System.out.println(userToUpdate.getIsActive());
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
		public void profileupdate(UserDto user) 
		{
		User user1=userRepo.findByEmail(user.getEmail());
		
		System.out.println(user1);
		if(user1 !=null)
		{
			System.out.println("hi bro");
		System.out.println(user.getFirstname());
			user1.setFirstname(user.getFirstname());
			user1.setLastname(user.getLastname());
			user1.setMobileno(user.getMobileno());
			user1.setEmail(user1.getEmail());
			user1.setBooking(user1.getBooking());
			user1.setGender(user1.getGender());
			user1.setIsActive(user1.getIsActive());
			user1.setPassword(user1.getPassword());
			user1.setRoles(user1.getRoles());
			user1.setReview(user1.getReview());
			user1.setUserId(user1.getUserId());
			user1.setUserName(user1.getUserName());
		
		userRepo.save(user1);
		}
		else
		{
			System.out.println("I am in else");
		}
			
		}
	
	@Override
	public User userFindByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	
//	public ArrayList<> getReviewStats(){
//		ArrayList<Booking> allStats = new ArrayList<>();
//		return allStats;
//	}




	

}
