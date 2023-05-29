import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user-services/user.service';
import { Tour } from '../interfaces/Booking';
import { User } from '../interfaces/User';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {


  tours:Tour[]=[];
  users:User;
  constructor(private router :Router,private userservice :UserService)
  {
this.users={
  userId:0,
  firstname:"",
  lastname:"",
  userName:"",
  email:"",
  password:"",
  mobileno:"",
  isActive:true,
  gender:"",
  roles:""

}

  }

  ngOnInit(): void {
this.userservice.getalltours().subscribe((data)=>this.tours=data)
this.userservice.getallusers().subscribe((data)=>{this.users=data
  console.log(data);
  console.log(this.users);
})

}


gotobookings()
{
this.router.navigate(["/user/bookings"]);
}


updateProfile(){

  this.router.navigate(["user/settings"]);
}

Logout()
{
  
    
    localStorage.removeItem('jwtToken');

    this.router.navigate(['/login']);
 

}
}