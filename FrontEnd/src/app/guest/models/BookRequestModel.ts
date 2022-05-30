import { Passenger } from "./Passeneger";

export class BookRequestModel{
    public constructor(public name:String,public email:number,public no_of_seats:String,public passengers:Passenger[],public mealType:String,public tripType:String,public returnDate:string){
          
    }
  }