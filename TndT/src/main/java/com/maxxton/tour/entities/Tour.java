package com.maxxton.tour.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

public class Tour {
	
	//data fields
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
	private Review review;
	

}
