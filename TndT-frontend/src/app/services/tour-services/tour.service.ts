import { Injectable } from '@angular/core';
import { TourLocation } from 'src/app/interfaces/tour-location';

@Injectable({
  providedIn: 'root'
})
export class TourService {

  tourLocationList: TourLocation[] =[]
  url = "http://localhost:8083/user/admin"
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
  submitApplication(groupSize:number){
    console.log(`group Size: ${groupSize}
    Booking Date: ${new Date()}`)

  }

    constructor() { }
}
