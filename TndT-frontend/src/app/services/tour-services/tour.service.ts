import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Booking } from 'src/app/interfaces/Booking';
import { Bookingtemp } from 'src/app/interfaces/bookingtemp';
import { TourLocation } from 'src/app/interfaces/tour-location';

@Injectable({
  providedIn: 'root'
})
export class TourService {

  tourLocationList: TourLocation[] =[]
  url = "http://localhost:8083/user/"

   token:string| null = localStorage.getItem('token');

  // getAllTourLocations():Tourlocation[]{
  //   return this.tourLocationList;
  // }
  async getAllTourLocations():Promise<TourLocation[]>{
    const data = await fetch(this.url+"admin/getTours");
    return await data.json()??[];
  }

 async getTourLocationById(id: number):Promise<TourLocation | undefined> {
    const data = await fetch(`${this.url}admin/getTourById/${id}`);
    return await data.json() ?? {};  }

    submitApplication(groupSize:number,tourId : number,rate: number, date : Date){
      const temp : Bookingtemp  = {
        bookingdate : date,
        groupsize : groupSize,
        price : groupSize*rate
      }

      this.addBooking(tourId,temp).subscribe(res=>{console.log(res    );
      },err=>{console.log(err);
      })


  }
  addBooking(id:number,booking: Bookingtemp)
  {
    const url = `http://localhost:8083/user/user/booking/addBooking/${id}`;
    const headers = new HttpHeaders().set('Authorization',`Bearer ${this.token}`)

    return this.http.post(url,booking,{headers})

  }

    constructor(private http : HttpClient) { }
}
