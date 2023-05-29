import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tour } from 'src/app/interfaces/Booking';
import { User } from 'src/app/interfaces/User';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }


  getJwtTokenFromLocalStorage(): string {
    // Retrieve the JWT token from local storage
    const token = localStorage.getItem('jwtToken');

    return token || ''; // Return an empty string if the token is null or undefined
  }

  private token:string="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYXlhbmo2NTFAZ21haWwuY29tIiwiaWF0IjoxNjg1MzUzNjM3LCJleHAiOjE2ODUzNzE2Mzd9.AI7Yg8MlEHCgPz6xUsD1ze2Oyo__zths3S3yM4pOQ_c"

   
  getalltours(): Observable<Tour[]> 
  {
    
    const apiEndpoint = 'http://localhost:9090/user/admin/getTours';

   
    const headers = new HttpHeaders().set('Authorization',`Bearer ${this.token}`);

    
    // Make an HTTP GET request to retrieve user data
    return this.http.get<Tour []>(apiEndpoint,{headers});
    
  }

  getallusers(): Observable<User> 
  {
    
    const apiEndpoint = 'http://localhost:9090/user/user/booking/getallusersbylogin';

   
    const headers = new HttpHeaders().set('Authorization',`Bearer ${this.token}`);

    
    // Make an HTTP GET request to retrieve user data
    return this.http.get<User>(apiEndpoint,{headers});
    
  }
}
