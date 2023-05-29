import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TourlocationComponent } from '../tourlocation/tourlocation.component';
import { TourLocation } from 'src/app/interfaces/tour-location';
import { TourService } from 'src/app/services/tour-services/tour.service';
import { inject } from '@angular/core';

@Component({
  selector: 'app-tourhome',
  standalone: true,
  imports: [CommonModule,
    TourlocationComponent],
  template: `
   <section>
  <form>

    <input type="text" placeholder="Filter by city" #filter ((keydown))="filterResults(filter.value)">
    <button class="primary" type="button" >Search</button>
    <div *ngIf="isAdmin()" class="float-end">
    <button class="primary "  >Add Tour</button>
    </div>

</form>
</section>
<br>
<div class="row row-cols-4">
<app-tourlocation  *ngFor="let tourLocation of filteredLocationList"
[tourLocation]="tourLocation"></app-tourlocation>
</div>
  `,
  styleUrls: ['./tourhome.component.css']
})
export class TourhomeComponent {
  roles:String | null = '';
  token : String | null;
  filteredLocationList : TourLocation[] =[]

  filterResults(text: string) {
    if (!text)
     this.filteredLocationList = this.tourLocationList

     console.log(text)

     this.filteredLocationList = this.filteredLocationList.filter(tourLocation=>tourLocation?.location.toLowerCase().includes(text.toLowerCase()))
     console.log(this.filteredLocationList);

  }
  isAdmin():boolean{


    if(typeof(this.token)==null)
     return false;

    if(this.roles==='ROLE_ADMIN')
    return true;

    return false;
  }

  tourLocationList: TourLocation[] = []
  tourService : TourService = inject(TourService);

constructor(){
  this.tourService.getAllTourLocations().then((tourLocationList:TourLocation[])=>{
    console.log(tourLocationList);

    this.tourLocationList = tourLocationList
    this.filteredLocationList = tourLocationList
  })
  this.filteredLocationList = this.tourLocationList
// localStorage.clear(  )
 this.roles = localStorage.getItem('roles');
 this.token  = localStorage.getItem('token');
}

}
