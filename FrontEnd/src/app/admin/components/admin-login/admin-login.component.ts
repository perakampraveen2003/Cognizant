import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtRequest } from '../../Models/JwtRequest';
import { AdminService } from '../../services/admin.service';


@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  pattern = new RegExp('^[a-zA-Z1-9@.]*$');
  loginForm:FormGroup;
  authStatus:boolean =false;
  constructor(private adminService:AdminService,private httpClient:HttpClient,private router:Router) {
    this.loginForm = new FormGroup({
      username: new FormControl("", [
        Validators.required,
        Validators.pattern(this.pattern),
        Validators.minLength(12)
      ]),
      password: new FormControl("", [
        Validators.required,
        Validators.minLength(8)
      ])
    });
   }

  ngOnInit(): void {
   
  }
  authenticate(){
    let jwtRequestModel:JwtRequest=new JwtRequest(this.loginForm.value.username,this.loginForm.value.password)
    this.adminService.authenticateUser(jwtRequestModel).subscribe(
      resp=>{
        if(resp.token!=null){
          console.log(resp);
          sessionStorage.setItem("token",resp.token);
          this.router.navigate(["admin/home"]);
        }
      },
      error =>  {this.authStatus=true;}
    );
  }

}
