package com.maxxton.tour.entities;

import java.util.Date;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Review {

	//REVIEW DATA
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int reviewid;
	
	
	private String review;
	
	private int rating;
	
	private Date dateofcreation;
	
	@ManyToOne
	@JoinColumn(name="tourid")
	private Tour tour;

	public int getReviewid() {
		return reviewid;
	}

	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getDateofcreation() {
		return dateofcreation;
	}

	public void setDateofcreation(Date dateofcreation) {
		this.dateofcreation = dateofcreation;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}
	
	

}
