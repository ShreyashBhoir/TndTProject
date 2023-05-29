package com.maxxton.tour.DTO;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.maxxton.tour.entities.Booking;
import com.maxxton.tour.entities.Bstatus;
import com.maxxton.tour.entities.Tour;
import com.maxxton.tour.entities.User;

public class BookingDTO {

	//data fields
	private int bookingid;
	private Date bookingdate;
	private int groupsize;
	private double price;
	private String status;
	private String bookedBy;
	private String tourName;
	
	public BookingDTO(Booking booking) {
		super();
		this.bookingid = booking.getBookingid();
		this.bookingdate = booking.getBookingdate() ;
		this.groupsize = booking.getGroupsize() ;
		this.price = booking.getPrice() ;
		this.status = booking.getStatus().toString() ;
		this.bookedBy = booking.getUser().getUserName() ;
		this.tourName = booking.getTour().getName() ;
	}
	
	
	
	
	
	
}
