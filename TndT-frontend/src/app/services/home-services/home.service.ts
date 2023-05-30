import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TourEntity } from 'src/app/admin/Entities/TourEntity';
import { TourLocation } from 'src/app/interfaces/tour-location';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private _http:HttpClient) { }

  public getTopTours(){
    const url="http://localhost:8083/user/admin/getTopTours";
    return this._http.get<TourLocation[]>(url);
  }
}
