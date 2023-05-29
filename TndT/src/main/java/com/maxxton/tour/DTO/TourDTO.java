package com.maxxton.tour.DTO;

import java.sql.Date;
import java.util.List;

import com.maxxton.tour.entities.Booking;
import com.maxxton.tour.entities.Review;
import com.maxxton.tour.entities.Tour;

public class TourDTO {

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
		  private String begindate;
		  private String imageurl;
		  private int reviewcount;
		  private int bookingcount;
		  
		  public TourDTO(Tour tour) {
			  this.tourid = tour.getTourid();
			   this.name = tour.getName();
			    this.location = tour.getLocation();
			    this.duration = tour.getDuration();
			    this.availableseats = tour.getAvailableseats();
			    this.difficulty = tour.getDifficulty();
			    this.avgRating = tour.getAvgRating();
			    this.price = tour.getPrice();
			    this.description = tour.getDescription();
			    this.istouractive = tour.isIstouractive();
			    this.begindate = tour.getBegindate().toString();
			    this.imageurl = tour.getImageurl();
			    this.reviewcount = tour.getReview().size();
			    this.bookingcount = tour.getBooking().size();
		  }



	
	

}