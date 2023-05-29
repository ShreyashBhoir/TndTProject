export interface TourLocation {
  tourid: number;
  name: string;
  location: string;
  duration: number;
  availableseats: number;
  difficulty:string;
  avgRating: number;
  price: number;
  discription: string;
  beginDate: Date;
  imageurl: string;
  isActive :boolean;
}
