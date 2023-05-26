import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UserComponent } from './user/user.component';
import { AdminComponent } from './admin/admin.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { UserBookingsComponent } from './user-bookings/user-bookings.component';
import { UserUpdateComponent } from './user-update/user-update.component';
import { AboutUsService } from './services/about-us-services/about-us.service';
import { AdminService } from './services/admin-services/admin.service';
import { ContactUsService } from './services/contact-us-services/contact-us.service';
import { FooterService } from './services/footer-services/footer.service';
import { HeaderService } from './services/header-services/header.service';
import { HomeService } from './services/home-services/home.service';
import { LoginService } from './services/login-services/login.service';
import { RegisterService } from './services/register-services/register.service';
import { TourService } from './services/tour-services/tour.service';
import { UserBookingsService } from './services/user-bookings-services/user-bookings.service';
import { UserUpdateService } from './services/user-update-services/user-update.service';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    UserComponent,
    AdminComponent,
    AboutUsComponent,
    ContactUsComponent,
    UserBookingsComponent,
    UserUpdateComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatButtonModule

  ],
  providers: [AboutUsService,AdminService,ContactUsService,
    FooterService,HeaderService,HomeService,LoginService,RegisterService,TourService,
    UserBookingsService,UserUpdateService],
  bootstrap: [AppComponent]
})
export class AppModule { }
