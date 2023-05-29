import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  public logoPath = "/assets/logo.jpg";

  constructor(private _router: Router) { 

  }

  routeUserorAdmin(){

    let userStorage = localStorage.getItem("token")
    let userRole = localStorage.getItem("roles")
    if(userStorage==null || userStorage.length==0 ||
       userRole==null || userRole.length==0){
      //go to login
      this._router.navigate(['/login']);
    }

    if(userRole=="ROLE_USER"){
      this._router.navigate(['/user']);
    }else if(userRole=="ROLE_ADMIN"){
      this._router.navigate(['/admin']);
    }
    
  }

  logoutUser(){
    sessionStorage.clear();
  }
}
