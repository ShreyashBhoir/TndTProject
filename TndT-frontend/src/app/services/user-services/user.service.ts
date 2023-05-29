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

  private token:string | null=localStorage.getItem('token');


  getalltours(): Observable<Tour[]>
  {

    const apiEndpoint = 'http://localhost:8083/user/admin/getTours';


    const headers = new HttpHeaders().set('Authorization',`Bearer ${this.token}`);


    // Make an HTTP GET request to retrieve user data
    return this.http.get<Tour []>(apiEndpoint,{headers});

  }

  getallusers(): Observable<User>
  {

    const apiEndpoint = 'http://localhost:8083/user/user/booking/getallusersbylogin';


    const headers = new HttpHeaders().set('Authorization',`Bearer ${this.token}`);


    // Make an HTTP GET request to retrieve user data
    return this.http.get<User>(apiEndpoint,{headers});

  }
}
