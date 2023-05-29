import { Injectable } from '@angular/core';
import { UserService } from '../user-services/user.service';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Booking } from 'src/app/interfaces/Booking';


@Injectable({
  providedIn: 'root'
})
export class UserBookingsService {

  constructor( private http: HttpClient )

  { }
  private token:string="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYXlhbmo2NTFAZ21haWwuY29tIiwiaWF0IjoxNjg1MzUzNjM3LCJleHAiOjE2ODUzNzE2Mzd9.AI7Yg8MlEHCgPz6xUsD1ze2Oyo__zths3S3yM4pOQ_c"


  retrieveBookings(): Observable<Booking[]>
  {

    const apiEndpoint = 'http://localhost:8083/user/user/booking/getbookinghistory';


    const headers = new HttpHeaders().set('Authorization',`Bearer ${this.token}`);


    // Make an HTTP GET request to retrieve user data
    return this.http.get<Booking []>(apiEndpoint,{headers});

  }

  deletebooking(id:any):any
  {
    const apiEndpoint = `http://localhost:8083/user/user/booking/deleteBooking/${id}`;


    const headers = new HttpHeaders().set('Authorization',`Bearer ${this.token}`);
return this.http.delete(apiEndpoint,{headers});
  }


}
