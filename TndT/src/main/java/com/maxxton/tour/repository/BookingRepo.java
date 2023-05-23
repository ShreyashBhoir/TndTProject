package com.maxxton.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.maxxton.tour.entities.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer>{

}
