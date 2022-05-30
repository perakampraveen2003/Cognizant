import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminLoginComponent } from './components/admin-login/admin-login.component';
import { Route } from '@angular/compiler/src/core';
import { Router, RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './components/home/home.component';
import { AdminLogoutComponent } from './components/admin-logout/admin-logout.component';
import { AirlineRegistrationComponent } from './components/airline-registration/airline-registration.component';
import { InventoryRegistrationComponent } from './components/inventory-registration/inventory-registration.component';
import { ViewFlightsComponent } from './components/view-flights/view-flights.component';


const routes:Routes = [
  {"path" : "login",component : AdminLoginComponent},
  {"path" : "home",component : HomeComponent},
  {"path" : "log-out",component:AdminLogoutComponent},
  {"path" : "airline-registration",component:AirlineRegistrationComponent},
  {"path" : "inventory-registration",component:InventoryRegistrationComponent},
  {"path" : "view-flights",component:ViewFlightsComponent}
]

@NgModule({
  declarations: [
    AdminLoginComponent,
    HomeComponent,
    AdminLogoutComponent,
    AirlineRegistrationComponent,
    InventoryRegistrationComponent,
    ViewFlightsComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes)
  ],
  providers: []
})
export class AdminModule { }
