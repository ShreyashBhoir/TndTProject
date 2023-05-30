import { Component } from '@angular/core';
import { AdminService } from '../services/admin-services/admin.service';
import { BookingEntity } from './Entities/BookingEntity';
import { AdminEntity } from './Entities/AdminDetailsEntity';
import { TourEntity } from './Entities/TourEntity';
import { UserEntity } from './Entities/UserEntity';
import { Review } from '../interfaces/review';
import { ReviewService } from '../services/tours-services/review.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
  styles:[`
      .my-round-element{
        height: 100px;
        width: 100px;
        border-radius: 50%;
      }
      .subMenuButtonActive{
        border: none;
        color: white;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        background-color: #00265e;
      }
      .subMenuButtonNotActive{
        border: none;
        color: black;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        background-color: #e7e7e7;
      }
  `]
})


export class AdminComponent {
  
  public adminInitials ="";
  public showStats:boolean = true;
  public showTours:boolean = false;
  public showReviews:boolean = false;
  public showAdminControl:boolean=false;

  public admin:AdminEntity = { 
    userId:0,
    firstname:"",
    lastname:"",
    userName:"",
    email:"",
    password:"",
    mobileno:"",
    isActive:false,
    gender:"",
    roles:""
  };
  allUsers:UserEntity[]=[]
  allBookingStats:BookingEntity[]=[]
  allTours:TourEntity[]=[]
  allReviews:Review[]=[]

  constructor(private _router:Router,private _adminService:AdminService,private _reviewService:ReviewService){}

  logoutUser(){
    localStorage.clear();
    this._router.navigate(['/login'])
  }

  public isUserIdThisId(id:number){
    return this.admin.userId===id;
  }

  ngOnInit(){

    if(localStorage.getItem("token") === null || localStorage.getItem("roles")===null){
      alert("Invalid session. Please login again")
      this.logoutUser();
      return;
    }

    this.getAllBookingStats();
    this.populateAdminDetails();
    this.getAllTours();
    this.getAllUsers();
    this.getAllReviews();
  }

  populateAdminDetails(){
    this._adminService.getAdminDetails().subscribe(
      (data:AdminEntity)=>{
        // console.log(data)
        this.admin = data;
        this.adminInitials = data.firstname.substring(0,2).toUpperCase();
      },
      (error:Error)=>{
        //ToDo
        alert("SERVER ERROR. TRY AGAIN LATER.");
        this.logoutUser();
        return;
      }
    );
  
  }

  selectSubMenu(selection:number){
    switch(selection){
      case 1: this.showStats=true;
              this.showTours=false;
              this.showReviews=false;
              this.showAdminControl=false;
      break;
      case 2: this.showStats=false;
              this.showTours=true;
              this.showReviews=false;
              this.showAdminControl=false;

      break;
      case 3: this.showStats=false;
              this.showTours=false;
              this.showReviews=true;
              this.showAdminControl=false;

      break;
      case 4: this.showStats=false;
              this.showTours=false;
              this.showReviews=false;
              this.showAdminControl=true;

      break;
    }
  }

  private getAllBookingStats(){
    this._adminService.getAllBookingStats().subscribe(
      (data:BookingEntity[])=>{
        //console.log(data)
        this.allBookingStats = data;
      },
      (error:Error)=>{
        alert("Error fetching Booking details. try again later")
      }
    );
  }

  private getAllTours(){
    this._adminService.getAllTour().subscribe(
      (data:TourEntity[])=>{
        //console.log(data)
        this.allTours = data;
      },
      (error:Error)=>{
        alert("Error fetching Tour details. try again later")
      }
    );
  }
  private getAllUsers(){
    this._adminService.getAllUsers().subscribe(
      (data:UserEntity[])=>{
        //console.log(data)
        this.allUsers = data;
      },
      (error:Error)=>{
        alert("Error fetching user details. try again later")
      }
    );
  }
  public getAllReviews(){
    this._reviewService.getAllReviews().then(
      (allReviews:Review[])=>{
        console.log(allReviews);
        
        this.allReviews = allReviews;
        /*TEST*/
        // this.allReviews = [
        //   {reviewid:1,review:"review text",rating:3,
        //   dateofcreation:new Date(),tourid:10,userid:5},
        //   {reviewid:1,review:"review text",rating:3,
        //   dateofcreation:new Date(),tourid:10,userid:5},
        //   {reviewid:1,review:"review text",rating:3,
        //   dateofcreation:new Date(),tourid:10,userid:5},
        //   {reviewid:1,review:"review text",rating:3,
        //   dateofcreation:new Date(),tourid:10,userid:5}
        // ] 
      }
    ).catch(
      (error)=>{
        alert("Error fetching Review details. try again later")
      }
    );
  }

  public changeActiveStatus(newStatus:boolean,forUser:number){
    this._adminService.changeStatus(newStatus,forUser).subscribe(
      (resp)=>{ 
        console.log(resp)
        //window.location.reload(); 
        this.getAllUsers();
      },
      (error)=>{ 
        //alert("OPERATION FAILED! TRY AFTER SOMETIME.");
        console.log(error);
        this.getAllUsers();

        //window.location.reload(); 
      }
    )
  }

  public changeAdminStatus(newStatus:boolean,forUser:number){
    this._adminService.makeAdmin(newStatus,forUser).subscribe(
      (resp)=>{ 
        console.log(resp)
        //window.location.reload(); 
        this.getAllUsers();
      },
      (error)=>{ 
        //alert("OPERATION FAILED! TRY AFTER SOMETIME.");
        console.log(error);
        this.getAllUsers();

        //window.location.reload(); 
      }
    )
  }

 
}
