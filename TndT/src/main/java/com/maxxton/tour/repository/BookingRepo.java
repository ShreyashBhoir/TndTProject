package com.maxxton.tour.repository;

import org.springframework.data.repository.CrudRepository;

import com.maxxton.tour.entities.Booking;

public interface BookingRepo extends CrudRepository<Booking, Integer>{

}
