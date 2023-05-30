import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserBookingsService } from '../services/user-bookings-services/user-bookings.service';

@Component({
  selector: 'app-deletebooking',
  templateUrl: './deletebooking.component.html',
  styleUrls: ['./deletebooking.component.css']
})
export class DeletebookingComponent implements OnInit {


   id:any=0;
constructor(private router:Router,private activatedroute:ActivatedRoute,private userbookingservice :UserBookingsService,private bookingService:UserBookingsService){

 
}

  ngOnInit(): void {
   
    this.id=this.activatedroute.snapshot.paramMap.get('id');
    console.log(this.id)
    this.userbookingservice.deletebooking(this.id).subscribe(()=>"Booking deleted ");
    
  this.router.navigate(['/user/bookings']);
  }

}
