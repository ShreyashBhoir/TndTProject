import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ReviewService } from 'src/app/services/tours-services/review.service';
import { Review } from 'src/app/interfaces/review';

@Component({
  selector: 'app-reviews',
  standalone: true,
  imports: [CommonModule],
  template: `
     <div class="container-fluid form-floating" id="textArea">
      <textarea
        class="form-control"
        placeholder="Leave a comment here"
        id="floatingTextarea"
        style="height: 100px"
      #textMessage ></textarea>

    </div>
    <div class="container-fluid" id="Submit_Button">
    <button class="btn btn-primary" (click)="ReviewSubmit(textMessage.value)">
      Click
    </button>
    </div>
    <div class="container" *ngFor="let item of reviewList" >
    <p>
      Lorem ipsum dolor sit amet consectetur adipisicing elit. Libero deleniti, deserunt quasi numquam velit soluta quidem magni! Officiis accusamus qui optio numquam omnis pariatur dicta quibusdam nemo, maxime, quas provident!
    </p>
    </div>
  `,
  styleUrls: ['./reviews.component.css']
})
export class ReviewsComponent {
  route: ActivatedRoute = inject(ActivatedRoute);
  reviewId : number = -1 ;
  reviewService : ReviewService= inject(ReviewService)
  reviewList : Review[] = []
  ReviewSubmit(text: String) {
    console.log(text);

    localStorage.getItem('token');
  }
  constructor(){

    let reviewId = parseInt(this.route.snapshot.params['id'], 10);

    this.reviewService.getAllReviewByTour(reviewId).then(reviewList=>{
      this.reviewList = reviewList;
    })
  }

}
