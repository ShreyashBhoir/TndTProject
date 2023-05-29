package com.maxxton.tour.DTO;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.maxxton.tour.entities.Review;
import com.maxxton.tour.entities.Tour;
import com.maxxton.tour.entities.User;

public class ReviewDTO {
private int reviewid;
	
	
	private String review;
	
	private int rating;
	
	private Date dateofcreation;
	
//	@ManyToOne
//	@JoinColumn(name="tourid")
	private int  tourid;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="userId")
	 */
	private int userid ;

	public ReviewDTO(Review review) {
		super();
		this.reviewid = review.getReviewid();
		this.review = review.getReview();
		this.rating = review.getRating();
		this.dateofcreation = review.getDateofcreation();
		this.tourid = review.getTour().getTourid();
		this.userid = review.getUser().getUserId();
	}

	
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

	public int getTourid() {
		return tourid;
	}

	public void setTourid(int tourid) {
		this.tourid = tourid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}


	@Override
	public String toString() {
		return "ReviewDTO [reviewid=" + reviewid + ", review=" + review + ", rating=" + rating + ", dateofcreation="
				+ dateofcreation + ", tourid=" + tourid + ", userid=" + userid + "]";
	}
	
	
	

}
