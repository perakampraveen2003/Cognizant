import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { BookResponseModel } from '../../models/BookResponseModel';

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css']
})
export class TicketListComponent implements OnInit {

  email:string=""
  book:BookResponseModel[]=[]
  showData:Boolean=false;
  noTicketMsg:Boolean=false;
  constructor(private httpClient:HttpClient) { }

  ngOnInit(): void {
  }

  getByEmail(){
    this.httpClient.get<BookResponseModel[]>("http://localhost:8989/api/v1.0/booking/history/"+this.email).subscribe(
      resp => {
          if(resp != null)
          {
            this.showData=true;
            this.noTicketMsg=false
            this.book=resp
          }
          else
          {
            this.showData=false;
            this.noTicketMsg=true;
          }

          console.log(resp)
      }
    );
  }

}
