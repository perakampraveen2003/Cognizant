import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SearchService } from './guest/services/search.service';

const routes: Routes = [
  { "path" : "home",component : HomeComponent},
  { "path" : "guest", loadChildren: ()=>import("./guest/guest.module").then(m=>m.GuestModule)},
  { "path" : "admin", loadChildren: ()=>import("./admin/admin.module").then(m=>m.AdminModule)},
  { "path" : "hello", loadChildren: ()=>import("./helloModule/hello.module").then(m=>m.HelloModule)},
  { "path" : "**", redirectTo: "home"}
];

@NgModule({

  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [SearchService]
})
export class AppRoutingModule { }
