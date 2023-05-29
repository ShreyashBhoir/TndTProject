export interface Booking{
    bookingid:number,
    bookingdate:Date,
    groupsize:number,
	price:number,
    status:string,
    tour:Tour
   
}

export interface Tour{


    name: string;
    location: string;
    duration: number;
    availableseats: number;
    difficulty:string;
    avgRating: number;
    price: number;
    discription: string;
    begindate: Date;
    imageurl: string;
    istouractive:boolean,





}