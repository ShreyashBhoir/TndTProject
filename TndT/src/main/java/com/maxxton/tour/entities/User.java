package com.maxxton.tour.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.boot.context.properties.bind.DefaultValue;

import net.bytebuddy.implementation.bind.annotation.Default;

import java.util.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(unique=true)
	private String email;
	
	private String password;
	private Boolean isActive;
	
	@Column(columnDefinition = "varchar(255) default 'USER'")
	private String roles;
	
	private GregorianCalendar changedPasswordDate;
	
	
	@OneToMany
	@JoinColumn(name="reviewid")
	private List<Review> review;



	@OneToMany(mappedBy = "user")
	private List<Booking> booking;
	
	public List<Booking> getBooking() {
		return booking;
	}


	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getIsActive() {
		return isActive;
	}


	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


	public String getRoles() {
		return roles;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	}


	public GregorianCalendar getChangedPasswordDate() {
		return changedPasswordDate;
	}


	public void setChangedPasswordDate(GregorianCalendar changedPasswordDate) {
		this.changedPasswordDate = changedPasswordDate;
	}


	public List<Review> getReview() {
		return review;
	}


	public void setReview(List<Review> review) {
		this.review = review;
	}


	
	
	

	// Generate Getters and Setters...
}