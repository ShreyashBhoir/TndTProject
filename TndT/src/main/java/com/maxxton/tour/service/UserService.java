package com.maxxton.tour.service;


import java.util.Optional;

import com.maxxton.tour.entities.Booking;

public interface UserService {
   public void addBooking(Booking booking);
   public Booking getBookingById(int id);
}
