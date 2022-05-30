import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { BookRequestModel } from '../../models/BookRequestModel';
import { Passenger } from '../../models/Passeneger';
import { BookingService } from '../../services/booking.service';


@Component({
  selector: 'app-book-flight',
  templateUrl: './book-flight.component.html',
  styleUrls: ['./book-flight.component.css']
})
export class BookFLightComponent implements OnInit {

  pattern = new RegExp('^[a-zA-Z1-9@.]*$');
  pName:string=""
  pAge=0
  pGender=""
  pSeatNo=""
  ap:string=""
  bookingForm:FormGroup;
  seats:number=0;
  showPassengers:Boolean=false
  passengers:Passenger[]=[];

  constructor(private route:ActivatedRoute,private bookingService:BookingService) { 
    this.bookingForm = new FormGroup({
      UserName: new FormControl("", [Validators.required,Validators.pattern( new RegExp('^[a-zA-Z]*$')),Validators.minLength(6)]),
      email: new FormControl("", [Validators.required]),
      noOfSeats: new FormControl(0, [Validators.required,Validators.min(1)]),
      mealType: new FormControl("", [Validators.required]),
      tripType: new FormControl("", [Validators.required]),
      rDate:new FormControl("",[])
    });
  }

  ngOnInit(): void {
   
  }
  sendBookItems(){
    let passengers:Passenger[]=BookingService.passengers
    if(passengers.length!=this.bookingForm.value.noOfSeats)
    {
      alert("Please add passengers")
    }
    else
    {
      BookingService.passengers=[]
      BookingService.i=0
      let booking:BookRequestModel=new BookRequestModel(this.bookingForm.value.UserName,this.bookingForm.value.email,this.bookingForm.value.noOfSeats,passengers,this.bookingForm.value.mealType,this.bookingForm.value.tripType,this.bookingForm.value.rDate);
      let flightNo:number=this.route.snapshot.params['flightId'];
      console.log(booking)
      this.bookingService.saveBooking(booking,flightNo);
    }   
  }

  counter(i:number){
      let a:Number[]=new Array(i);
      for(let j=0;j<i;j++){
        a[j] = j+1;
      }
      return a;
  }


}
