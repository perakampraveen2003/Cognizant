import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { BookResponseModel } from '../../models/BookResponseModel';



@Component({
  selector: 'app-my-tickets',
  templateUrl: './my-tickets.component.html',
  styleUrls: ['./my-tickets.component.css']
})
export class MyTicketsComponent implements OnInit{
   pnr:string=""
  showData:Boolean=false;
  noPNRMsg=false;
  book:BookResponseModel=new BookResponseModel("",0,"","",0,"","",0,"","","","","","");
  disableButton:Boolean=false
  constructor(private httpClient:HttpClient) { 
  }

  ngOnInit(): void {
  }

  SavePDF(){
         let data = 
          '\r PNR Number       :' + this.book.pnrNumber + ' \r\n ' + 
              'Name             :' + this.book.email + ' \r\n ' + 
              'Passenger Details:' +this.book.passengers + ' \r\n ' + 
              'Email            :' + this.book.email + ' \r\n ' + 
              'Date of Journey  :' + this.book.arivalDate + ' \r\n ' + 
              'Place of Onboard :' + this.book.fromPlace + ' \r\n ' +
              'Return Date      :' + this.book.returnDate+ ' \r\n ' +
              'Booking Status   :' + this.book.bookStatus;

      // Convert the text to BLOB.
      const textToBLOB = new Blob([data], { type: 'text/plain' });
      const sFileName = this.book.name+"_"+this.book.pnrNumber+"_Ticket"+".txt";	   // The file to save the data.

      let newLink = document.createElement("a");
      newLink.download = sFileName;

      if (window.webkitURL != null) {
          newLink.href = window.webkitURL.createObjectURL(textToBLOB);
      }
      else {
          newLink.href = window.URL.createObjectURL(textToBLOB);
          newLink.style.display = "none";
          document.body.appendChild(newLink);
      }

      newLink.click(); 
  }
  getByPNR(){
    this.httpClient.get<BookResponseModel>("http://localhost:8989/api/v1.0/booking/ticket/"+this.pnr).subscribe(resp=>{
    if(resp!=null)  
    {
      this.showData=true;
      this.noPNRMsg=false;
      this.book =resp
      if(this.book.bookStatus=="Cancelled"){
        this.disableButton=true;
        this.disableButton=true;
      }
      else{
        this.disableButton=false;
        this.disableButton=false;
      }
    }
    else
    {
      this.showData=false;
      this.noPNRMsg=true;
    }
  });
  }
  cancel(pnrNo:string){
    this.httpClient.put<BookResponseModel>("http://localhost:8989/api/v1.0/booking/cancel/"+pnrNo,null).subscribe(resp=>{
      console.log(resp);
      if(resp){
        alert("Cancelled Successfully");
        this.getByPNR();
      }
    });
  }

}
