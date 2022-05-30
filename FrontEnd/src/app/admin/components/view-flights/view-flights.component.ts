import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Inventory } from '../../Models/Inventory';
import { InventoryModel } from '../../Models/InventoryModel';

@Component({
  selector: 'app-view-flights',
  templateUrl: './view-flights.component.html',
  styleUrls: ['./view-flights.component.css']
})
export class ViewFlightsComponent implements OnInit {

  flights:InventoryModel[]=[]
  constructor(private httpClient:HttpClient) { }

  ngOnInit(): void {
    this.getFlights()
  }
  getFlights(){
    this.httpClient.get<InventoryModel[]>("http://localhost:8989/api/v1.0/flight/airline/flights").subscribe(resp=>{
      this.flights=resp;
    })
  }
  deleteFlight(flightNumber:number){
    let auth=sessionStorage.getItem("token")
    let httpHeaders=new HttpHeaders({
      "Authorization" : "Bearer "+auth
    })
    let options={
      headers:httpHeaders
    }
    this.httpClient.delete("http://localhost:8989/api/v1.0/admin/airline/delete/"+flightNumber,options).subscribe(resp=>{
      console.log(resp)
      this.getFlights()
    })
    // location.reload()
  }

}
