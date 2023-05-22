package com.maxxton.tour.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="Booking_T")
public class Booking {
	
	//data fields
	private Date bookingdate;
	private int groupsize;
	private double price;
	
	//tour and user mapping
	
	private Tour tour;
	private User user;
	
	//getters and setters
	public Date getBookingdate() {
		return bookingdate;
	}
	public void setBookingdate(Date bookingdate) {
		this.bookingdate = bookingdate;
	}
	public int getGroupsize() {
		return groupsize;
	}
	public void setGroupsize(int groupsize) {
		this.groupsize = groupsize;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Tour getTour() {
		return tour;
	}
	public void setTour(Tour tour) {
		this.tour = tour;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	//toString
	@Override
	public String toString() {
		return "Booking [bookingdate=" + bookingdate + ", groupsize=" + groupsize + ", price=" + price + ", tour="
				+ tour + ", user=" + user + ", getBookingdate()=" + getBookingdate() + ", getGroupsize()="
				+ getGroupsize() + ", getPrice()=" + getPrice() + ", getTour()=" + getTour() + ", getUser()="
				+ getUser() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	

}
