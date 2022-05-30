import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Airline } from '../../Models/Airline';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-airline-registration',
  templateUrl: './airline-registration.component.html',
  styleUrls: ['./airline-registration.component.css']
})
export class AirlineRegistrationComponent implements OnInit {

  registrationForm:FormGroup;
  msg:any=""

  constructor(private adminService:AdminService) { 
    this.registrationForm=new FormGroup({
      airlineName:new FormControl("",[
        Validators.required,
        Validators.minLength(10)
      ]),
      contactNo:new FormControl("",[
        Validators.required
      ]),
      contactAddress:new FormControl("",[
        Validators.required,
        Validators.minLength(5)
      ])
    });
  }

  ngOnInit(): void {
  }

  registerAirline(){
    let auth=sessionStorage.getItem("token")||""
    let httpHeaders = new HttpHeaders({
      'Authorization': "Bearer "+auth
      }); 
    let options = {
       headers:httpHeaders,
       responseType : 'text'
    }
    
   let airline:Airline=new Airline(this.registrationForm.value.airlineName,this.registrationForm.value.contactNo,this.registrationForm.value.contactAddress);
   console.log(airline);
   this.adminService.registerAirline(airline,options).subscribe(response=>{this.msg=response});
 }
}
