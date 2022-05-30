import { Component, OnInit} from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Passenger } from '../../models/Passeneger';
import { BookingService } from '../../services/booking.service';

@Component({
  selector: 'app-passenger',
  templateUrl: './passenger.component.html',
  styleUrls: ['./passenger.component.css']
})
export class PassengerComponent implements OnInit {

  static passengers:Passenger[]=[]
  static i:number=0
  passengerForm:FormGroup;
  constructor(private service:BookingService) {
    this.passengerForm=new FormGroup({
      pName:new FormControl("",[Validators.required,Validators.pattern( new RegExp('^[a-zA-Z]*$')),Validators.minLength(6)]),
      pAge:new FormControl(0,[Validators.required,Validators.max(100)]),
      pSeatNo:new FormControl("",[Validators.required]),
      pGender:new FormControl("",[Validators.required]),
    })
   }

  ngOnInit(): void {
  }
  savePassenger(){
    let passenger:Passenger= new Passenger(this.passengerForm.value.pName,
      this.passengerForm.value.pAge,
      this.passengerForm.value.pGender,
      this.passengerForm.value.pSeatNo);
      this.service.savePassengersList(passenger)
      alert("Passenger Added")
  }
  
}
