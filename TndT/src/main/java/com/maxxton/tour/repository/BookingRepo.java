package com.maxxton.tour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.maxxton.tour.entities.Booking;


public interface BookingRepo extends JpaRepository<Booking, Integer>{

	/*--------s------*/
	@Query("SELECT b FROM Booking b WHERE YEAR(b.bookingdate) = ?1")
	public List<Booking> getBookingsByYear(int year);
	
	//@Query("SELECT count(b.bookingid),(b.user.userId) FROM Booking b group by b.tour.tourid,")
	@Query("SELECT count(b.bookingid),(b.user.userId) FROM Booking b group by b.user.userId")
	public List<Object[]> getUsersAndBookings();
	
	
	@Query("SELECT b from Booking b WHERE b.user.userId =:uid ")
	public List<Booking> getBookinghistory( @Param("uid") int uid);
    
}
