import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../services/login-services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  jwt:any; 
  constructor(private builder: FormBuilder,
    private router: Router,private userdata:LoginService) {
      sessionStorage.clear();
      this.errorMessage=null;
    }
  result: any;
  warning:any;
  errorMessage:any;
  errorMessages:any;
  loginform = this.builder.group({
  username: this.builder.control('', Validators.email),
  password: this.builder.control('', Validators.compose([Validators.required, Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}')]))
  
}
  );
  proceedlogin() {
    if (this.loginform.valid) {
      console.log(this.loginform.value);
       this.jwt=this.userdata.users(); 
      
       /*
      if(this.loginform.value.password!=this.loginform.value.passwordConfirm)
      {
      //  this.toastr.warning('Please enter valid data.');
      console.log("hello ");
     // this.errorMessages="INVALID DATA";
      alert('PASSWORD AND CONFIRMPASSWORD NOT SAME');
      return;
    }
    */
      this.userdata.login(this.loginform.value).subscribe((result:any)=>{
        console.log(result); 
        this.jwt=result;
        let token=result;
        console.log(token);
        console.log(this.jwt.token);
        localStorage.setItem("roles",this.jwt.roles);
        localStorage.setItem("token",this.jwt.token);
        this.errorMessage=null;
        //this.errorMessages=null;
        //localStorage.setItem("token",);
       },(err:any)=>{
            this.errorMessage="INVALID CREDENTIALS";
       })
    } else {
      //this.toastr.error("wrong")
      this.errorMessage="INVALID DATA";
    }
  }
}
