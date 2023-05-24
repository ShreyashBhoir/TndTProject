package com.maxxton.tour.service;


import java.util.Optional;

import com.maxxton.tour.entities.Booking;
import com.maxxton.tour.entities.Review;

public interface UserService {
   public void addBooking(Booking booking);
   public Booking getBookingById(int id);
   public void deleteBookingById(int id);
   public void addReview(Review review);
}
