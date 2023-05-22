package com.maxxton.tour.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Bookings")
public class Booking {
	
	//data fields
	@Id
	@GeneratedValue
	private int bookingid;
	private Date bookingdate;
	private int groupsize;
	private double price;
	
	//tour and user mapping
	@OneToOne
	@JoinColumn(name="tourid")
	private Tour tour;
	
	@OneToOne
	@JoinColumn(name="userId")
	private User user;
	
	//getters and setters
	public Date getBookingdate() {
		return bookingdate;
	}
	public int getBookingid() {
		return bookingid;
	}
	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
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
