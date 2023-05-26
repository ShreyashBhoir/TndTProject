package com.maxxton.tour.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Tours")
public class Tour {
	
	//data fields
	
	@Id
	@GeneratedValue
	private int tourid;
	private String name;
	private String location;
	private int duration;
	private int availableseats;
	private String difficulty;
	private float avgRating;
	private int price;
	private String description;
	private boolean istouractive;
	
	
	public boolean isIstouractive() {
		return istouractive;
	}

	public void setIstouractive(boolean istouractive) {
		this.istouractive = istouractive;
	}

	private Date begindate;
	
	private String imageurl;
	
	//review entity mapping
	@JsonIgnore
	@OneToMany(mappedBy = "tour" ,cascade = CascadeType.REMOVE)
	private List<Review> review;

	
	@JsonIgnore
	@OneToMany(mappedBy = "tour")
	private List<Booking> booking;


	//getters and setters
	public int getTourid() {
		return tourid;
	}

	public void setTourid(int tourid) {
		this.tourid = tourid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	
	public int getAvailableseats() {
		return availableseats;
	}

	public void setAvailableseats(int availableseats) {
		this.availableseats = availableseats;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public float getRating() {
		return avgRating;
	}

	public void setRating(float f) {
		this.avgRating = f;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Date getBegindate() {
		return begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	

	public float getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}

	public List<Booking> getBooking() {
		return booking;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}



	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	//ToString

	
	@Override
	public String toString() {
		return "Tour [tourid=" + tourid + ", name=" + name + ", location=" + location + ", duration=" + duration
				+ ", groupSize=" + availableseats + ", difficulty=" + difficulty + ", rating=" + avgRating + ", price=" + price
				+ ", description=" + description + ", begindate=" + begindate + ", imageurl=" + imageurl + ", review="
				+ review + "]";
	}

	
	

}