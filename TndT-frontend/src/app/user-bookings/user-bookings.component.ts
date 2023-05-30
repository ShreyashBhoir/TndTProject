import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user-services/user.service';
import { UserBookingsService } from '../services/user-bookings-services/user-bookings.service';

import { Router } from '@angular/router';
import { Booking } from '../interfaces/Booking';


@Component({
  selector: 'app-user-bookings',
  templateUrl: './user-bookings.component.html',
  styleUrls: ['./user-bookings.component.css']
})
export class UserBookingsComponent implements  OnInit {

 
public bookings :Booking[]=[];

startDate: Date=new Date('0000-01-01');
endDate: Date=new Date('0000-01-01');


  constructor(private userService:UserService,private bookingService:UserBookingsService,private router:Router)
  {
    
     
  }

  ngOnInit(): void 
  {
    
    
   this.bookingService.retrieveBookings().subscribe(data=>this.bookings=data);
   
  

  }

  deleteBooking(booking:any)
  {
    console.log("I am delete")

    this.router.navigate([`/user/bookings/delete/${booking.bookingid}`]);
    
}

calculateDateDifference(booking:Booking): boolean {
 
  const diffInTime = Math.abs(new Date(booking.tour.begindate).getTime()-new Date( booking.bookingdate).getTime());

  console.log(diffInTime)
  const diffInDays = Math.ceil(diffInTime/(1000 * 3600 * 24));
 console.log(diffInDays)
  if(diffInDays>=15)
  {  return true
  }
  else{ 
    return false}
  
 
}

back(){

    this.router.navigate(["/user"]);
}

}
