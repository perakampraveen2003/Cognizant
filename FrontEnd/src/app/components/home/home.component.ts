import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  static showMsg:boolean=true
  constructor(public httpCLient:HttpClient) { }

  ngOnInit(): void {
   
    let options:any = {
       responseType : 'text'
    }
  
    //   this.httpCLient.get<any>("https://6gtcmm2cz2.execute-api.us-west-2.amazonaws.com/default/2183473Test",options).subscribe(
    //   resp=>{
    //     if(HomeComponent.showMsg)
    //     {
    //       alert(resp);
    //       HomeComponent.showMsg=false;
    //     }
    //   }
    // )
  }

}
