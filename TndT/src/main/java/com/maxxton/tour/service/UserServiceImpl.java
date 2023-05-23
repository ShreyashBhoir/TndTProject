package com.maxxton.tour.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxxton.tour.entities.Booking;
import com.maxxton.tour.repository.BookingRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BookingRepo bookingrepo;
	
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

}
