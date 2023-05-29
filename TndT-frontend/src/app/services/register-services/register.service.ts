import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  constructor(private http:HttpClient ) { 

  }
   url1= "http://localhost:8083/login";
   url2= "http://localhost:8083/register";
  users()
  {
    return [
      {
        name:'anil',age:28
      }
    ]
  }
  saveusers(data:any)
  {
     return this.http.post(this.url2,data)
  }
  login(data:any)
  {
    return this.http.post(this.url1,data);
  }
}
