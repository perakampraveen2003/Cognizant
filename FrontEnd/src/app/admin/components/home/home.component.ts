import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SearchService } from 'src/app/guest/services/search.service';
import { Airline } from '../../Models/Airline';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  showBlockDiv:Boolean=false;
  showUnBlockDiv:Boolean=false;
  airlines:string[]=[];
  time:string[]=[]
  a1:string=""
  constructor(private router:Router,private httpClient:HttpClient,private adminService:AdminService) { 
    
  }

  ngOnInit(): void {
    this.adminService.getAirlines().subscribe(resp=>{this.airlines=resp});
    this.time=this.adminService.getTime();
    // this.httpClient.get<string[]>("http://localhost:8989/api/v1.0/flight/airline/airlines").subscribe(resp=>{this.airlines=resp});
  }

  addAirline(){
     this.router.navigate(["admin/airline-registration"]);
  }
  addInventory(){
    this.router.navigate(["admin/inventory-registration"]);
  
  }
  showBlock(){
    this.showUnBlockDiv=false;
    this.showBlockDiv=true;
  }
  showUnBlock(){
    this.showUnBlockDiv=true;
    this.showBlockDiv=false;
  }

  blockAirline(){
    let auth=sessionStorage.getItem("token")||""
    let httpHeaders = new HttpHeaders({
      'Authorization': "Bearer "+auth
      }); 
    let options = {
       headers:httpHeaders
    }
    const body={}
   this.httpClient.put<any>("http://localhost:8989/api/v1.0/admin/airline/block/"+this.a1,body,options).subscribe((resp:any)=>{
   alert(resp['t'])  
   console.log(resp['t'])
   });
  }
  unBlockAirline(){
    let auth=sessionStorage.getItem("token")||""
    let httpHeaders = new HttpHeaders({
      'Authorization': "Bearer "+auth
      }); 
    let options = {
       headers:httpHeaders
    }
    const body={}
   this.httpClient.put<any>("http://localhost:8989/api/v1.0/admin/airline/unblock/"+this.a1,body,options).subscribe((resp:any)=>{
   alert(resp['t'])  
   console.log(resp['t'])
   });
  }
}
