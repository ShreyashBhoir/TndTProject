import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserUpdate } from 'src/app/interfaces/UserUpdate';


@Injectable({
  providedIn: 'root'
})
export class UserUpdateService {

  constructor( private http: HttpClient )

  { }
  private token:string |null=localStorage.getItem('token')
  updateProfile(user:UserUpdate): Observable<UserUpdate[]>
  {
    console.log("I am in update")
    const apiEndpoint = 'http://localhost:8083/user/user/booking/updateprofile';


    const headers = new HttpHeaders().set('Authorization',`Bearer ${this.token}`);



    return this.http.put<UserUpdate[]>(apiEndpoint,user,{headers});

  }

  deletebooking(id:any):any
  {
    const apiEndpoint = `http://localhost:8083/user/user/booking/deleteBooking/${id}`;


    const headers = new HttpHeaders().set('Authorization',`Bearer ${this.token}`);
return this.http.delete(apiEndpoint,{headers});
  }


}

