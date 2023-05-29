import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Input } from '@angular/core';
import { TourLocation } from 'src/app/interfaces/tour-location';

@Component({
  selector: 'app-tourlocation',
  standalone: true,
  imports: [CommonModule,RouterLink],
  template: `
    <div class="card mt-2 col" style="width: 18rem;">
  <img [src]="tourLocation.imageurl" style="height: 250px;" class="card-img-top img-fluid" id="source" alt="Exterior image {{tourLocation.location}}">
  <div class="card-body">
    <img src="./assets/location-pin.svg">
    <h4 class="card-title">{{tourLocation.name}},IN</h4>
    <h5 class="card-title">{{tourLocation.location}},IN</h5>
    <p class="card-text">{{tourLocation.discription}}</p>
    <a [routerLink]="['/details', tourLocation.tourid]">Learn More</a>
  </div>
</div>

  `,
  styleUrls: ['./tourlocation.component.css']
})
export class TourlocationComponent {
  @Input() tourLocation!: TourLocation
}
