import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { inject } from '@angular/core';
import { TourService } from 'src/app/services/tour-services/tour.service';
import { TourLocation } from 'src/app/interfaces/tour-location';

import { FormControl, FormGroup,ReactiveFormsModule } from '@angular/forms';
import { ReviewsComponent } from "../reviews/reviews.component";

@Component({
    selector: 'app-tourdetails',
    standalone: true,
    template: `
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-12">
          <div class="row">
            <div class="col-md-6">
              <article class="body">
                <img
                  class="img-fluid "
                  id="tourimg"
                  [src]="tourLocation.imageurl"
                  alt="Exterior photo of {{ tourLocation.name }}"
                />
              </article>
            </div>
            <div class="col-md-6">
              <section class="listing-description">
                <h2 class="listing-heading">{{ tourLocation.name }}</h2>
                <img src="./assets/location-pin.svg" />
                <p class="listing-location">{{ tourLocation.location }}, IN</p>
              </section>
              <section class="listing-features">
                <h2 class="section-heading">About this tour location</h2>
                <ul>
                  <li><h4>Description : {{ tourLocation.description }}</h4></li>
                  <li>Seats Available: {{ tourLocation.availableseats }}</li>
                  <li>Duration: {{ tourLocation.duration }}</li>
                  <li>
                    When does the tour start: {{ tourLocation.begindate }}
                  </li>
                  <li>Price: {{ tourLocation.price }} PP</li>
                  <li>Difficulty : {{ tourLocation.difficulty }}</li>


                  <br />
                  <form [formGroup]="toursDetails">
                    <label for="first-name">No of People</label>

                    <input
                      id="first-name"
                      type="number"
                      formControlName="groupSize"
                    />
                    <br />
                    <button
                      type="submit"
                      class="primary"
                       *ngIf="display()" (click)="submitBooking()"
                    >
                      Book now
                    </button>
                    <h2 style="color: red;" *ngIf="error == true">
                      Unable to book
                    </h2>
                  </form>
                </ul>
              </section>
            </div>
          </div>
        </div>
      </div>
    </div>
    <app-reviews></app-reviews>
  `,
    styleUrls: ['./tourdetails.component.css'],
    imports: [CommonModule, ReactiveFormsModule, ReviewsComponent]
})
export class TourdetailsComponent {
  error: boolean = false;
  route: ActivatedRoute = inject(ActivatedRoute);
  token :string |null ;

  tourService: TourService = inject(TourService);
  tourLocation: TourLocation = {
    tourid: 0,
    name: "",
    location: "",
    duration: 0,
    availableseats: 0,
    difficulty:"",
    avgRating: 0,
    price: 0,
    description: "",
    begindate: new Date(),
    imageurl: "",
    isActive :false,
  };

  toursDetails = new FormGroup({
    groupSize: new FormControl(''),
  });
  //flag : boolean = false
  display(): boolean {
    let date =  this.tourLocation.begindate ;
    //console.log(new Date().toDateString());
    //console.log(date.toDateString());
    console.log("---------------");
    console.log(new Date());
    console.log(date);
    
    console.log(new Date() > date)
    console.log("-------XX--------");
    
    if (new Date() > date ){
      return false;
    }
    else{
    
    return true;
    }
  }


  submitBooking() {
    let count = parseInt(this.toursDetails.value.groupSize ?? ' ');
    let availSeats: number | undefined;
    const tourLocationId = parseInt(this.route.snapshot.params['id'], 10);


    if(this.token ==null){
     this.router.navigate(['/login'])
     return
    }

    if (typeof this.tourLocation == undefined) availSeats = 0;
    else availSeats = this.tourLocation?.availableseats;
    if (availSeats != undefined && availSeats < count) {
      // console.log(count + '  ' + availSeats);
      this.error = true;
      return;
      /*this.display(true);
      return;*/
    } else {
      this.error = false;
      // this.display(false)
     const price = this.tourLocation?.price || 0
      this.tourService.submitApplication(count,tourLocationId,0,new Date());
      this.router.navigate([`/details/${tourLocationId}`])


    }
  }

  constructor(private router : Router) {
    const tourLocationId = parseInt(this.route.snapshot.params['id'], 10);
    this.tourService
      .getTourLocationById(tourLocationId)
      .then((t) => {
        // console.log(tourLocationId);
        this.tourLocation = t;
      });
      this.token = localStorage.getItem('token');
      // console.log(this.token);
  }

  
}
