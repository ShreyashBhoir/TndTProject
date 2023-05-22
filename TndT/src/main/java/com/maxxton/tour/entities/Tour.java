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
	private int groupSize;
	private String difficulty;
	private int rating;
	private int price;
	private String description;
	private Date begindate;
	private String imageurl;
	
	//review entity mapping
	@OneToMany(mappedBy = "reviewid" ,cascade = CascadeType.REMOVE)
	private List<Review> review;

	@OneToOne(mappedBy = "tour")
	private Booking booking;


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

	public int getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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
				+ ", groupSize=" + groupSize + ", difficulty=" + difficulty + ", rating=" + rating + ", price=" + price
				+ ", description=" + description + ", begindate=" + begindate + ", imageurl=" + imageurl + ", review="
				+ review + "]";
	}

	
	

}
