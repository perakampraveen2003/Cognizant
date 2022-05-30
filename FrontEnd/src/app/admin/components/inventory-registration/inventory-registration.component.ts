import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Inventory } from '../../Models/Inventory';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-inventory-registration',
  templateUrl: './inventory-registration.component.html',
  styleUrls: ['./inventory-registration.component.css']
})
export class InventoryRegistrationComponent implements OnInit {
  
  inventoryForm:FormGroup
  airlines:string[]=[];
  time:string[]=[];

  constructor(private adminService:AdminService) { 
    this.inventoryForm=new FormGroup({
      fNo:new FormControl("",[Validators.required]),
      airline:new FormControl("",[Validators.required]),
      fPlace:new FormControl("",[Validators.required]),
      tPlace:new FormControl("",[Validators.required]),
      sDate:new FormControl("",[Validators.required]),
      sTime:new FormControl("",[Validators.required]),
      eDate:new FormControl("",[Validators.required]),
      eTime:new FormControl("",[Validators.required]),
      schedule:new FormControl("",[Validators.required]),
      instrument:new FormControl("",[]),
      bSeats:new FormControl("",[Validators.required]),
      eSeats:new FormControl("",[Validators.required]),
      price:new FormControl("",[Validators.required]),
      noOfRows:new FormControl("",[Validators.required]),
      mealType:new FormControl("",[Validators.required])
    })
  }

  ngOnInit(): void {
    this.time=this.adminService.getTime();
  
    this.adminService.getAirlines().subscribe(resp=>{this.airlines=resp});
  }
  save(){
    let inventory:Inventory=
    new Inventory(
      this.inventoryForm.value.fNo,
      this.inventoryForm.value.airline,
      this.inventoryForm.value.fPlace,
      this.inventoryForm.value.tPlace,
      this.inventoryForm.value.sDate,
      this.inventoryForm.value.sTime,
      this.inventoryForm.value.eDate,
      this.inventoryForm.value.eTime,
      this.inventoryForm.value.schedule,
      this.inventoryForm.value.instrument,
      this.inventoryForm.value.bSeats,
      this.inventoryForm.value.eSeats,
      this.inventoryForm.value.price,
      this.inventoryForm.value.noOfRows,
      this.inventoryForm.value.mealType
    );
    let auth=sessionStorage.getItem("token")
    let httpHeaders:HttpHeaders=new HttpHeaders({
      "Authorization" : "Bearer "+auth
    });
    let options={
      headers:httpHeaders,
      responseType : 'text'
    }
    this.adminService.saveInventory(inventory,options).subscribe(resp=>{
      let msg:any=resp;
      if(msg==="Flight added Successfully"){
           alert(msg)
      }
    })
   
  }

}
