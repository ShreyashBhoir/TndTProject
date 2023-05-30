
import { Component, ComponentFactoryResolver } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterService } from '../services/register-services/register.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  constructor(private builder: FormBuilder,  private router: Router,
    private userdata:RegisterService) {

  }

  registerform = this.builder.group({
    userName: this.builder.control('', Validators.compose([Validators.required, Validators.minLength(3)])),
    firstname:this.builder.control('', Validators.compose([Validators.required, Validators.minLength(3)])),
    lastname:this.builder.control('', Validators.compose([Validators.required, Validators.minLength(3)])),
    password: this.builder.control('', Validators.compose([Validators.required, Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}')])),
    confirmpassword: this.builder.control('', Validators.compose([Validators.required, Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}')])),
    email: this.builder.control('', Validators.compose([Validators.required, Validators.email])),
    mobileno:this.builder.control('', Validators.compose([Validators.required, Validators.minLength(3)])),
    gender: this.builder.control('male')
  });

  errorMessage:any;
  errorMessages:any;
  proceedregister() {

    if(this.registerform.valid)
    {
        console.log(this.registerform.value);
        if(this.registerform.value.password!=this.registerform.value.confirmpassword)
        {
        //  this.toastr.warning('Please enter valid data.');
        console.log("hello ");
       // this.errorMessages="INVALID DATA";
        alert('PASSWORD AND CONFIRMPASSWORD NOT SAME');
        return;
      }
        this.userdata.saveusers(this.registerform.value).subscribe((result:any)=>{
         console.log(result); 
         this.errorMessage=null;
         alert("USER SUCCESSFULLY REGISTERED AND EMAIL HAS BEEN SEND!!!");
        },(err:any)=>{
            this.errorMessage="USER ALREADY EXISTS";
        })
    } 
    else
    {
     // this.toastr.warning('Please enter valid data.')
      this.errorMessage="INVALID DATA";
    }
    /*
    if (this.registerform.valid) {
      this.service.RegisterUser(this.registerform.value).subscribe(result => {
        this.toastr.success('Registered successfully')
        this.router.navigate(['login'])
        console.log(this.registerform);
      });
    } else {
      this.toastr.warning('Please enter valid data.')
    }*/
  }
}
