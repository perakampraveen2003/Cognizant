export class BookResponseModel {

	public constructor(public pnrNumber:string,public flightId:number,public name:string,public email:string,public no_of_seats:number,public passengers:string,public mealType:string,public fare:number,public bookStatus:string,public arivalTime:string,public arivalDate:string,public fromPlace:string,public toPlace:string,public returnDate:String){

    }

}
