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

  async getAllReviewByTour(tourid:string):Promise<Review[]>{

    const data = fetch(this.url+`/getreview/${tourid}`)
    return await (await data).json()??[]
  }

  constructor() { }
}
