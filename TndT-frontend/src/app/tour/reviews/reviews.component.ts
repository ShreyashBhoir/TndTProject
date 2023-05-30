import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ReviewService } from 'src/app/services/tours-services/review.service';
import { Review } from 'src/app/interfaces/review';

@Component({
  selector: 'app-reviews',
  standalone: true,
  imports: [CommonModule],
  template: `
     <div class="container-fluid form-floating" id="textArea">
       <h5>Please Share us your feedback</h5>
     <textarea
        class="form-control"
        placeholder="Leave a comment here"
        id="floatingTextarea"
        style="height: 100px"
      #textMessage ></textarea>
       How Would You Rate us? (1- Poor - > 5 - Excellent)
      <input type="number" min="1" max="5" #rating>

    </div>
    <div class="container-fluid" id="Submit_Button">
    <button class="btn btn-primary" (click)="ReviewSubmit(textMessage.value,(rating.value))">
      Click
    </button>
    </div>
    <div class="container" *ngFor="let item of reviewList" >
    <p>
     {{item.review}}
    </p>
    </div>
  `,
  styleUrls: ['./reviews.component.css']
})
export class ReviewsComponent {
  token : String| null = ''
  route: ActivatedRoute = inject(ActivatedRoute);
  reviewId : number = -1 ;
  reviewService : ReviewService= inject(ReviewService)
  reviewList : Review[] = []
  ReviewSubmit(text: String, rating : String ) {
    let ratings = Number(rating)
    if (this.token == null){
      this.router.navigate(['/login'])
      return
      }
    console.log(text);

    localStorage.getItem('token');
  }
  constructor(private router : Router){

    let reviewId = parseInt(this.route.snapshot.params['id'], 10);

    this.reviewService.getAllReviewByTour(reviewId).then(reviewList=>{
      console.log(reviewList);

      this.reviewList = reviewList;

    })
    this.token = localStorage.getItem('token')
  }

}
