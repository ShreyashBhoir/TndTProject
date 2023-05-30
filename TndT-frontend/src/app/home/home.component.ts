import { Component } from '@angular/core';
import { HomeService } from '../services/home-services/home.service';
import { TourEntity } from '../admin/Entities/TourEntity';
import { TourLocation } from '../interfaces/tour-location';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  styles : [`
      .listing {
        background: var(--accent-color);
        border-radius: 30px;
        padding-bottom: 30px;
      }
      .listing-heading {
        color: var(--primary-color);
        padding: 10px 20px 0 20px;
      }
      .listing-photo {
        height: 250px;
        width: 250px;
        object-fit: cover;
        border-radius: 30px 30px 0 0;
      }
      .listing-location {
        padding: 10px 20px 20px 20px;
      }
      .listing-location::before {
        content: url("/assets/location-pin.svg") / "";
      }

      section.listing a {
        padding-left: 20px;
        text-decoration: none;
        color: var(--primary-color);
      }
      section.listing a::after {
        content: "\203A";
        margin-left: 5px;
      }


  `]
})
export class HomeComponent {

  bannerImage = "/assets/banner.png";
  topTours:TourLocation[]=[]

  constructor(private _homeService:HomeService){}

  ngOnInit(){
    this._homeService.getTopTours().subscribe(
      (data:TourLocation[])=>{this.topTours=data.slice(0,3)},
      (error)=>{alert("SERVER ERROR: PLEASE TRY AGAIN AFTER SOMETIME")}
    )
  }
}
