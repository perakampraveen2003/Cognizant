import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { SearchComponent } from './components/search/search.component';
import { RouterModule, Routes } from '@angular/router';
import { BookFLightComponent } from './components/book-flight/book-flight.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MyTicketsComponent } from './components/my-tickets/my-tickets.component';
import { TicketListComponent } from './components/ticket-list/ticket-list.component';
import { PassengerComponent } from './components/passenger/passenger.component';
import jsPDF from 'jspdf';

const routes:Routes = [
  {path: "search", component: SearchComponent },
  {path: "book-flight/:flightId", component : BookFLightComponent},
  {path: "mytickets", component : MyTicketsComponent},
  {path: "ticketlist", component : TicketListComponent}
]

@NgModule({
  declarations: [
    SearchComponent,
    BookFLightComponent,
    MyTicketsComponent,
    TicketListComponent,
    PassengerComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forChild(routes)
  ]
})
export class GuestModule { }
