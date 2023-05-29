export interface TourEntity {
    tourid: number;
    name: string;
    location: string;
    duration: number;
    availableseats: number;
    difficulty: string;
    avgRating: number;
    price: number;
    description: string;
    //istouractive: boolean;
    begindate:string;
	//imageurl:string;
    reviewcount:number;
    bookingcount:number;
  }
  