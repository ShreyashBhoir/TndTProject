import { Component, OnInit } from '@angular/core';
import { UserUpdateService } from '../services/user-update-services/user-update.service';
import { Router } from '@angular/router';

import { FormControl, FormGroup } from '@angular/forms';
import { UserUpdate } from '../interfaces/UserUpdate';
import { User } from '../interfaces/User';
import { UserService } from '../services/user-services/user.service';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent  implements OnInit{

  userupdate: FormGroup;
  users:User;
user:UserUpdate  = 
{
  firstname:"",
  lastname:"",
mobileno:0,
email:"",
password:"",
gender:""


};
hideLabel:boolean=false;

  constructor(private  userupdateservice :UserUpdateService ,private router:Router,private userservice :UserService)
  {
    this.userupdate= new FormGroup({
    password: new FormControl(''),
      confirm: new FormControl(''),
      firstname: new FormControl(''),
      lastname: new FormControl(''),
      email: new FormControl(''),
      gender: new FormControl(''),
      mobileno:new FormControl('')
    });
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
    this.userservice.getallusers().subscribe((data)=>{this.users=data
      console.log(data);
      console.log(this.users);
    })
  }

  submitUserForm(): any{
    // Call the addUser() method from the UserService

    this.user.firstname=this.userupdate.get('firstname')?.value;
    this.user.lastname=this.userupdate.get('lastname')?.value;
    this.user.email=this.userupdate.get('email')?.value;
    this.user.password=this.userupdate.get('password')?.value;
    this.user.gender=this.userupdate.get('gender')?.value;
    this.user.mobileno=this.userupdate.get('mobileno')?.value;
    
    this.userupdateservice.updateProfile(this.user).subscribe(
        response => {
          console.log('User added successfully:', response);
          // Handle success response
        },
        error => {
          console.error('Error adding user:', error);
          // Handle error response
        }
      );
  }

  back(){

    this.router.navigate(["/user"]);
}

confirmpass():boolean
{
  if(this.userupdate.get('password')?.value ==this.userupdate.get('confirm')?.value)
  {
    
return false
  }
  else
  return true

}

}
