import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PassengerComponent } from '../components/passenger/passenger.component';
import { BookRequestModel } from '../models/BookRequestModel';
import { BookResponseModel } from '../models/BookResponseModel';
import { Passenger } from '../models/Passeneger';


@Injectable({
  providedIn: 'root'
})
export class BookingService {
  static passengers:Passenger[]=[]
  static i:number=0

  savePassengersList(passenger: Passenger) {
    BookingService.passengers[BookingService.i]=passenger
    BookingService.i = BookingService.i+1;
  }

  saveBooking(booking: BookRequestModel,flightId:number) {
    this.httpClient.post<BookResponseModel>("http://localhost:8989/api/v1.0/booking/"+flightId,booking).subscribe(resp=>{
      alert(resp.pnrNumber+" Generated. Check in My Tickets");
    });
  }

  constructor(private httpClient:HttpClient) { }
}
