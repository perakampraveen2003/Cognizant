import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-logout',
  templateUrl: './admin-logout.component.html',
  styleUrls: ['./admin-logout.component.css']
})
export class AdminLogoutComponent implements OnInit {

  constructor(private route:Router) { }

  ngOnInit(): void {
     sessionStorage.removeItem("token")
     this.route.navigateByUrl("http://localhost:4200/home")
  }

}
