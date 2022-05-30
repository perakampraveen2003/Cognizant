import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/admin/services/admin.service';
import { SearchRequestModel } from '../../models/SearchRequestModel';
import { SearchResponseModel } from '../../models/SearchResponseModel';
import { SearchService } from '../../services/search.service';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  loginForm:FormGroup;
  times:string[]=[]
  fromPlace:string[] = []
  toPlace:string[] = []

  showtable:Boolean = false;
  showform:Boolean = true;
  noFlightsMessage:Boolean = false;

  responseList:SearchResponseModel[] = [];
    
  constructor(private adminService:AdminService,private search:SearchService,private router:Router,private httpClient:HttpClient) { 
    this.loginForm = new FormGroup({
      tripDate: new FormControl("", [Validators.required]),
      time: new FormControl("", [Validators.required]),
      fromPlace: new FormControl("", [Validators.required]),
      toPlace: new FormControl("", [Validators.required]),
      tripType: new FormControl("", [Validators.required])
    });
  }

  ngOnInit(): void {
    this.getAllDistinctPlaces();
    this.times=this.adminService.getTime();
  }

  getAllDistinctPlaces(){
    this.search.getDistinctFromPlaces().subscribe(
      (response) => {
        this.fromPlace = response 
      });

      this.search.getDistinctToPlaces().subscribe(
        (response) => {
          this.toPlace = response 
        });
  }
  sendSearchItems(){
    let searchRequestModel = new SearchRequestModel(this.loginForm.value.tripDate,this.loginForm.value.time,this.loginForm.value.fromPlace,this.loginForm.value.toPlace,this.loginForm.value.tripType); 
    this.search.SearchAll(searchRequestModel).subscribe(
      (response) => {
       console.log(response);
       if(response.length > 0)
       {
        this.responseList=response;
         this.showtable = true;
         this.showform = false;
         this.noFlightsMessage = false;
       }
       else{
         this.noFlightsMessage = true;
       }
     }
   );
  }
  bookNow(flightNo:any){
    this.router.navigate(['guest/book-flight',flightNo]);
  }

}
