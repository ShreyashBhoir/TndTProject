package com.maxxton.tour.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.maxxton.tour.entities.Review;
import com.maxxton.tour.entities.Tour;
import com.maxxton.tour.repository.ReviewRepo;
import com.maxxton.tour.repository.TourRepo;

@Service
public class RatingService {

	@Autowired
	private static TourRepo tourRepo;

	@Autowired
	private static ReviewRepo reviewRepo;

	public static void avgRatingCalc(int tourId) {
		List<Review> reviewList = reviewRepo.findByTour(tourId);
		List<Integer> ratingList = new ArrayList<>();

		for (Review review : reviewList) {
			ratingList.add(review.getRating());
		}

		int sum = ratingList.stream().mapToInt((a) -> a).sum();
		float avg = sum / ratingList.size();

		Optional<Tour> tour = tourRepo.findById(tourId);
		if (tour.isEmpty())
			throw new UsernameNotFoundException("Tour with id:" + tourId + " not found! ");
		Tour tour1 = tour.get();
		tour1.setAvgRating(avg);
		tourRepo.save(tour1);
		

	}
	
	/*
	 * Tour tour1Tour = tour.get(); tour1Tour.setAvgRating(avg);
	 * tourRepo.save(tour1Tour);
	 */
}
