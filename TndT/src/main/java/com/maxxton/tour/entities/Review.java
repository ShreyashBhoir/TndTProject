package com.maxxton.tour.entities;

import java.util.Date;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	Tour tour;

}
