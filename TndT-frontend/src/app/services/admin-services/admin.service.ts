import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AdminEntity } from 'src/app/admin/Entities/AdminDetailsEntity';
import { BookingEntity } from 'src/app/admin/Entities/BookingEntity';
import { TourEntity } from 'src/app/admin/Entities/TourEntity';
import { UserEntity } from 'src/app/admin/Entities/UserEntity';
import { Review } from 'src/app/interfaces/review';



@Injectable({
  providedIn: 'root'
})
export class AdminService {
  /*
  V - ResponseEntity<List<Booking>> allStats()
  X - ResponseEntity<List<Booking>> allStatsOfYear(@PathVariable int year)
  ResponseEntity<List<Object[]>> getUserBookings()
    - ResponseEntity<Tour> addTour(@RequestBody Tour tour) 
  --X-- ResponseEntity<Tour>UpdateTour(@RequestBody Tour tour)
  V - ResponseEntity<List<Tour>> getAllTours()
  ResponseEntity<String> makeAdmin()
  
  */

  private getToken():string|null{
    return localStorage.getItem("token");
  }

  public getAdminDetails(){
    const token = this.getToken();
    const url="http://localhost:8083/user/admin/getAdminDetails";
    const headers = new HttpHeaders().set('Authorization',`Bearer ${token}`);
    return this._http.get<AdminEntity>(url,{headers});
  }

  public getAllBookingStats(){
    const token = this.getToken();
    const url="http://localhost:8083/user/admin/stats";
    const headers = new HttpHeaders().set('Authorization',`Bearer ${token}`);
    return this._http.get<BookingEntity[]>(url,{headers});
  }

  // public getBookingStatsByYear(year:number){

  // }

  public getUsersAndBookings(){

  }

  public getAllTour(){
    const token = this.getToken();
    const url="http://localhost:8083/user/admin/getTours";
    const headers = new HttpHeaders().set('Authorization',`Bearer ${token}`);
    return this._http.get<TourEntity[]>(url,{headers});
  }

  public addTour(){

  }

  public getAllUsers(){
    const token = this.getToken();
    const url="http://localhost:8083/user/admin/getUsers";
    const headers = new HttpHeaders().set('Authorization',`Bearer ${token}`);
    return this._http.get<UserEntity[]>(url,{headers});
  }

  // public getAllReviews(){
  //   const token = this.getToken();
  //   const url="http://localhost:8083/---------------------------------";
  //   const headers = new HttpHeaders().set('Authorization',`Bearer ${token}`);
  //   return this._http.get<Review[]>(url,{headers});
  // }

  public giveReply(id:number,reply:string){
    const token = this.getToken();
    const url="http://localhost:8083/user/admin/reply/"+id;
    const headers = new HttpHeaders().set('Authorization',`Bearer ${token}`);
    return this._http.post<string>(url,reply,{headers});
  }

  public makeAdmin(newStatus:boolean,forUser:number){
    const token = this.getToken();
    var url = "";
    if(newStatus)
      url=`http://localhost:8083/user/admin/makeadmin/${forUser}`;
    else 
      url=`http://localhost:8083/user/admin/makeuser/${forUser}`;

    const headers = new HttpHeaders().set('Authorization',`Bearer ${token}`);
    return this._http.patch<any>(url,forUser,{headers});

  }


  public changeStatus(newStatus:boolean,forUser:number){
    const token = this.getToken();
    var url = "";
    if(newStatus)
      url=`http://localhost:8083/user/admin/enableUser/${forUser}`;
    else 
      url=`http://localhost:8083/user/admin/disableUser/${forUser}`;

    const headers = new HttpHeaders().set('Authorization',`Bearer ${token}`);
    return this._http.patch<any>(url,forUser,{headers});

  }

  

  constructor(private _http:HttpClient) {

  }


}
