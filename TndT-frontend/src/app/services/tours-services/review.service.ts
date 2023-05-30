import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Review } from 'src/app/interfaces/review';
@Injectable({
  providedIn: 'root'
})
export class ReviewService {
 url = "http://localhost:8083/review"

  review : Review[] = []


  async getAllReviews():Promise<Review[]> {
    const data =fetch(this.url);
    return   (await data).json()??[]

  }

  async getAllReviewByTour(tourid:number):Promise<Review[]>{

    const data = fetch(this.url+`/getreview/${tourid}`)
    return await (await data).json()??[]
  }



  constructor(private http: HttpClient) { }
}
