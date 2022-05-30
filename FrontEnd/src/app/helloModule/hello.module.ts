import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HelloComponent } from "./hello.component";

const routes:Routes=[
    {
        path:"hello",
        component:HelloComponent
    }
]
@NgModule({
    declarations:[],
    imports:[RouterModule.forChild(routes)],
    exports:[],
    providers:[]
})
export class HelloModule{

}