import { Component } from '@angular/core';
import { AdminService } from '../services/admin-services/admin.service';
import { BookingEntity } from './Entities/BookingEntity';
import { AdminEntity } from './Entities/AdminDetailsEntity';
import { TourEntity } from './Entities/TourEntity';
import { UserEntity } from './Entities/UserEntity';

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
  
  public adminInitials ="SJ";
  // public adminId = "01";
  // public adminName="Sanket Jadhav"
  // public adminUserName="Sanketjadhavmes";
  // public adminMobileNumber="9969131748";
  // public adminEmailId = "sanketjadhavmes@gmail.com";

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

  constructor(private _adminService:AdminService){}

  logoutUser(){
  
  }

  ngOnInit(){
    this.getAllBookingStats();
    this.populateAdminDetails();
    this.getAllTours();
    this.getAllUsers();
    //this.getAllReviews();
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
        //ToDo
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
        //ToDo
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
        //ToDo
      }
    );
  }

  public changeActiveStatus(newStatus:boolean,forUser:number){

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
