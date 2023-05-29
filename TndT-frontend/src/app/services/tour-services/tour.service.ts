import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TourLocation } from 'src/app/interfaces/tour-location';

@Injectable({
  providedIn: 'root'
})
export class TourService {

  tourLocationList: TourLocation[] =[]
  url = "http://localhost:8083/user/admin"

  private token:string="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYXlhbmo2NTFAZ21haWwuY29tIiwiaWF0IjoxNjg1MzUzNjM3LCJleHAiOjE2ODUzNzE2Mzd9.AI7Yg8MlEHCgPz6xUsD1ze2Oyo__zths3S3yM4pOQ_c"

  // getAllTourLocations():Tourlocation[]{
  //   return this.tourLocationList;
  // }
  async getAllTourLocations():Promise<TourLocation[]>{
    const data = await fetch(this.url+"/getTours");
    return await data.json()??[];
  }

 async getTourLocationById(id: number):Promise<TourLocation | undefined> {
    const data = await fetch(`${this.url}/getTourById/${id}`);
    return await data.json() ?? {};  }

    submitApplication(groupSize:number,tourId : number){
    console.log(`group Size: ${groupSize}
    Booking Date: ${new Date()}`)


  }
  addBooking(id:number)
  {
    const url = `http://localhost:8083/user/user/booking/addBooking/${id}`;
    const headers = new HttpHeaders().set('Authorization',`Bearer ${this.token}`)
  }

    constructor() { }
}
