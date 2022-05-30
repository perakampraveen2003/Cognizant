import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Airline } from '../Models/Airline';
import { Inventory } from '../Models/Inventory';

import { JwtRequest } from '../Models/JwtRequest';
import { JwtResponse } from '../Models/JwtResponse';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  saveInventory(inventory: Inventory, options: any) {
   return this.httpClient.post("http://localhost:8989/api/v1.0/admin/airline/inventory/add",inventory,options);
  }

  constructor(private httpClient:HttpClient) { }

  authenticateUser(jwtRequestModel: JwtRequest) {
    return this.httpClient.post<JwtResponse>("http://localhost:8989/api/v1.0/admin/authenticate",jwtRequestModel);
   }

   registerAirline(airline:Airline,options:any){
    return this.httpClient.post('http://localhost:8989/api/v1.0/admin/airline/register',airline,options)
   }
   getAirlines(){
    return this.httpClient.get<string[]>("http://localhost:8989/api/v1.0/flight/airline/airlines");
   }
   getTime(){
     let time:string[]=["01:30","02:30","03:30","04:30","05:30","06:30","07:30","08:30","09:30",
     "10:30","12:30","13:30","14:30","15:30","16:30","17:30","18:30","19:30","20:30","21:30","22:30"
    ]
    return time;
   }
}
