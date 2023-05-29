import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-reviews',
  standalone: true,
  imports: [CommonModule],
  template: `
     <div class="container-fluid form-floating" id="textArea">
      <textarea
        class="form-control"
        placeholder="Leave a comment here"
        id="floatingTextarea"
        style="height: 100px"
      #textMessage ></textarea>

    </div>
    <div class="container-fluid" id="Submit_Button">
    <button class="btn btn-primary" (click)="ReviewSubmit(textMessage.value)">
      Click
    </button>
    </div>
    <div class="container">
    <p>
      Lorem ipsum dolor sit amet consectetur adipisicing elit. Libero deleniti, deserunt quasi numquam velit soluta quidem magni! Officiis accusamus qui optio numquam omnis pariatur dicta quibusdam nemo, maxime, quas provident!
    </p>
    </div>
  `,
  styleUrls: ['./reviews.component.css']
})
export class ReviewsComponent {
  ReviewSubmit(text: String) {
    console.log(text);

    localStorage.getItem('token');
  }

}
