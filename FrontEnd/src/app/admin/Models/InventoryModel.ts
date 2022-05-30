import { Airline } from "./Airline";
import { AirlineModel } from "./AirlineModel";

export class InventoryModel{
    public constructor(
        public flightNumber:number,
        public airline:AirlineModel,
        public fromPlace:string,
        public toPlace:string,
        public startDate:any,
        public startTime:string,
        public endDate:any,
        public endTime:string,
        public schedule:string,
        public instrumentUsed:string,
        public total_business_class_seats:number,
        public total_economy_class_seats:number,
        public ticketPrice:number,
        public no_of_rows:number,
        public mealType:string,
    ){

    }
}
