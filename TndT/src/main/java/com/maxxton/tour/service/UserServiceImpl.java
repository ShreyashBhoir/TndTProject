package com.maxxton.tour.service;

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

	

}
