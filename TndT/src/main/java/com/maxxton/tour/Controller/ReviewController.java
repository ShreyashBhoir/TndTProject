package com.maxxton.tour.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxxton.tour.entities.Booking;
import com.maxxton.tour.entities.Review;
import com.maxxton.tour.entities.User;
import com.maxxton.tour.repository.ReviewRepo;
import com.maxxton.tour.repository.UserRepo;
import com.maxxton.tour.service.UserService;

/*
 * 
 * private String review;
	
	private int rating;
	
	private Date dateofcreation;
 */

@RestController
@RequestMapping("/user/user/review")
public class ReviewController {
	@Autowired
	private UserService userservice;	
	
	@Autowired
	private UserRepo userrepo;	
	
	
	@Autowired
	private ReviewRepo reviewrepo;	
	//CRUD for Review by user
    //User adding new review
	
	@PostMapping("/addReview/{id}")
	public ResponseEntity<User> addReview(@RequestBody Review review,@PathVariable("id") int id) 
	{
		User user=userrepo.getById(id);
		List<Review>r=user.getReview();
		r.add(review);
		userservice.addReview(review);
		userrepo.save(user);
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
	}
	
	//get review of a user
	
	@GetMapping("/getReview/{id}")
    public ResponseEntity<List<Review>> getUser(@PathVariable("id") int id) {
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
	}
}