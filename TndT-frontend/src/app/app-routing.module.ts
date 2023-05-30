import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutUsComponent } from './about-us/about-us.component';
import { AdminComponent } from './admin/admin.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { TourhomeComponent } from './tour/tourhome/tourhome.component';
import { TourdetailsComponent } from './tour/tourdetails/tourdetails.component';
import { UserComponent } from './user/user.component';
import { UserBookingsComponent } from './user-bookings/user-bookings.component';
import { UserUpdateComponent } from './user-update/user-update.component';

import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { DeletebookingComponent } from './deletebooking/deletebooking.component';

const routes: Routes = [
  {path:"about-us",component:AboutUsComponent,title:"about-us"},
  {path:"admin",component:AdminComponent,title:"admin"},
  {path:"contact-us",component:ContactUsComponent,title:"contact-us"},
  {path:"home",component:HomeComponent,title:"home"},
  {path:"",component:HomeComponent,title:"home"},
  {path:"login",component:LoginComponent,title:"login"},
  {path:"register",component:RegisterComponent,title:"register"},
  {path:"tour",component:TourhomeComponent,title:"tour"},
  {path:"details/:id",component:TourdetailsComponent,title:"tour-details"},
  {path:"user",component:UserComponent,title:"user"},
  {path:"user/bookings",component:UserBookingsComponent,title:"bookings"},
  {path:"user/settings",component:UserUpdateComponent,title:"settings"},
  {
    path:"user/bookings/delete/:id",component:DeletebookingComponent
  },
  {path:"**",component:PageNotFoundComponent,title:"page not found"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
// export const RoutingComponents = [AboutUsComponent,AdminComponent,ContactUsComponent,HomeComponent,
//     LoginComponent,RegisterComponent,TourdetailsComponent,UserComponent,UserBookingsComponent,
//     UserUpdateComponent,PageNotFoundComponent]
