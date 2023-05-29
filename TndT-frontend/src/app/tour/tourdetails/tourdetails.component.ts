import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
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
                  [src]="tourLocation?.imageurl"
                  alt="Exterior photo of {{ tourLocation?.name }}"
                />
              </article>
            </div>
            <div class="col-md-6">
              <section class="listing-description">
                <h2 class="listing-heading">{{ tourLocation?.name }}</h2>
                <img src="./assets/location-pin.svg" />
                <p class="listing-location">{{ tourLocation?.location }}, IN</p>
              </section>
              <section class="listing-features">
                <h2 class="section-heading">About this tour location</h2>
                <ul>
                  <li>Seats Available: {{ tourLocation?.availableseats }}</li>
                  <li>Duration: {{ tourLocation?.duration }}</li>
                  <li>
                    When does the tour start: {{ tourLocation?.beginDate }}
                  </li>
                  <li>Price: {{ tourLocation?.price }} PP</li>
                  <li>Difficulty : {{ tourLocation?.difficulty }}</li>

                  <li>Desciption : {{ tourLocation?.discription }}</li>
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
                      (click)="submitBooking()"
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

  tourService: TourService = inject(TourService);
  tourLocation: TourLocation | undefined;

  toursDetails = new FormGroup({
    groupSize: new FormControl(''),
  });
  //flag : boolean = false
  display(flag: boolean): boolean | undefined {
    return flag;
  }

  submitBooking() {
    let count = parseInt(this.toursDetails.value.groupSize ?? ' ');
    let availSeats: number | undefined;

    if (typeof this.tourLocation == undefined) availSeats = 0;
    else availSeats = this.tourLocation?.availableseats;
    if (availSeats != undefined && availSeats < count) {
      console.log(count + '  ' + availSeats);
      this.error = true;
      return;
      /*this.display(true);
      return;*/
    } else {
      this.error = false;
      // this.display(false)

      this.tourService.submitApplication(count);
      let token = localStorage.getItem('token');
    }
  }

  constructor() {
    const tourLocationId = parseInt(this.route.snapshot.params['id'], 10);
    this.tourService
      .getTourLocationById(tourLocationId)
      .then((tourLocationId) => {
        console.log(tourLocationId);

        this.tourLocation = tourLocationId;
      });
  }
}
