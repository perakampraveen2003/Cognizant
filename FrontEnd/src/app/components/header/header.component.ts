import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor() {
    
   }

  ngOnInit(): void {
  }

  isLoggedIn(){
    if(sessionStorage.getItem("token") === null){
      return false
    }
    else{
      return true;
    }
  }
}
