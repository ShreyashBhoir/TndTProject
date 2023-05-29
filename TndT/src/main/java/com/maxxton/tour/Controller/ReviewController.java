package com.maxxton.tour.Controller;

import java.util.ArrayList;
import java.util.Date
;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxxton.tour.DTO.ReviewDTO;
import com.maxxton.tour.entities.Booking;
import com.maxxton.tour.entities.Review;
import com.maxxton.tour.entities.Tour;
import com.maxxton.tour.entities.User;
import com.maxxton.tour.helper.JwtUtil;
import com.maxxton.tour.repository.ReviewRepo;
import com.maxxton.tour.repository.TourRepo;
import com.maxxton.tour.repository.UserRepo;
import com.maxxton.tour.service.RatingService;
import com.maxxton.tour.service.UserService;

/*
 * 
 * private String review;
	
	private int rating;
	
	private Date dateofcreation;
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private JwtUtil jwtutil;
	
	@Autowired
	private UserService userservice;
	
	
	@Autowired
	private UserRepo userrepo;	
	
	@Autowired
	private TourRepo tourRepo;
	
	
	@Autowired
	private ReviewRepo reviewRepo;	
	//CRUD for Review by user
    //User adding new review
	
	//add review using tour id from pathvariable and userId from token
	@PostMapping("/addReview/{tourid}")
	public ResponseEntity<Review> addReviewForTour(@RequestBody Review review,@PathVariable("tourid") int tourid,HttpServletRequest request){
		// EXTRACTING THE TOKEN
		String tokenwithBearer = request.getHeader("Authorization");

		// extracting the email from jwt token
		String email = jwtutil.getUsernameFromToken(tokenwithBearer.substring(7));
		
		// finding user from email
		User user = userservice.userFindByEmail(email);
		Tour tour = tourRepo.getById(tourid);
		review.setDateofcreation(new Date());
		review.setTour(tour);
		review.setUser(user);
		reviewRepo.save(review);
		//reviewRepo.
		//to update the avg Rating of the tour
		RatingService.avgRatingCalc(tourid);
		return new ResponseEntity<>(review,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<ReviewDTO>> getAllReview(){
		List<Review> reviewList =  reviewRepo.findAll();
		List<ReviewDTO> reviewDTOList = new ArrayList();
		reviewList.forEach(e->reviewDTOList.add(new ReviewDTO(e)));
		
		return new ResponseEntity<List<ReviewDTO>>(reviewDTOList,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/getreview/{Id}")
	public ResponseEntity<List<ReviewDTO>> getReviewForTour(@PathVariable("tId") int id){
//		int id = Integer.parseInt(tId);
	Optional<Tour>	tourbyId = tourRepo.findById(id);
	System.out.println(tourbyId.get());
	
	   List<Review> reviewList = reviewRepo.findByTour(tourbyId.get());
	   List<ReviewDTO> reviewDTOList = new ArrayList();
	  reviewList.forEach( (e)->reviewDTOList.add(new ReviewDTO(e)));
	   System.out.println(reviewList);
	 		
		
		
		return new ResponseEntity<List<ReviewDTO>> (reviewDTOList, HttpStatus.OK);
	}
	
	@GetMapping("/getreviewbyuser/{id}")
	public ResponseEntity<List<ReviewDTO>> getReviewByUser(@PathVariable("id") int id){
		 Optional<User> user = userrepo.findById(id);
		 if(user.isEmpty())
			 throw new UsernameNotFoundException("User Id is invalid");
		 User user1 = user.get();
		
		List<Review> reviewList = reviewRepo.findByUser(user1);
		List<ReviewDTO> reviewDTOList = new ArrayList();
		  reviewList.forEach( (e)->reviewDTOList.add(new ReviewDTO(e)));
		  
		  return new ResponseEntity<List<ReviewDTO>> (reviewDTOList, HttpStatus.OK);
		
	}
	
	@PutMapping("/updatereview/{tourId}/{reviewId}")
	public ResponseEntity<Review> updateReview(@RequestBody Review review ,@PathVariable("tourId") int tourId, @PathVariable("reviewId") int reviewId){
		Optional<Review> fetchedReview = reviewRepo.findById(reviewId);
		if (fetchedReview.isEmpty())
			throw new UsernameNotFoundException("invalid Review Id");
		Review rev1 = fetchedReview.get();
		rev1.setReview(review.getReview());
		rev1.setRating(review.getRating());
		reviewRepo.save(rev1);
		//to update the avgRating
		RatingService.avgRatingCalc(tourId);
		
		return new ResponseEntity<Review>(rev1,HttpStatus.OK);
			
	}
	
	
	
	
	/*
	 * //aading review using tour id
	 * 
	 * @PostMapping("/addReview/{id}") public ResponseEntity<User>
	 * addReview(@RequestBody Review review,@PathVariable("id") int id) { User
	 * user=userrepo.getById(id); List<Review>r=user.getReview(); r.add(review);
	 * userservice.addReview(review); userrepo.save(user); return new
	 * ResponseEntity<User>(user,HttpStatus.ACCEPTED); }
	 */
	
	//get review of a user
	
	/* @GetMapping("/getReview/{id}") */
 /*   public ResponseEntity<List<Review>> getUser(@PathVariable("id") int id) {
		System.out.println("hi "+id);
		User user=userrepo.getById(id);
        
        if (user != null) {
        	List<Review>r=user.getReview();
            return new ResponseEntity<>(r, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } 
	
	//delete review of a user
	@DeleteMapping("/deleteReview/{id}/{reviewid}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id,@PathVariable("reviewid") int reviewid) {
		
		//delete button
		//
		User user=userrepo.getById(id);
		if(user==null)
		{
			 System.out.println("No user is present");
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else
		{
		 reviewrepo.deleteById(reviewid);
		 //delete review from user
		 List<Review>r=user.getReview();
		 
		 for(int i=0;i<r.size();i++)
		 {
			 if(r.get(i).getReviewid()==reviewid)
			 { 
				 r.remove(i);
			 }
		 }
         return new ResponseEntity<>("USER DELETED WITH ID"+id,HttpStatus.NO_CONTENT);
		}
	}
	
	//update review of a user
	//1)REVIEW
	//2)RATING
	
	//update review
	 @PutMapping("/updateReview/review/{id}/{reviewid}")
	    public ResponseEntity<Review> updateReview(@PathVariable("id") int id, @PathVariable("reviewid") int reviewid,@RequestBody Review review) {
         		
		 return null;
	}*/
}