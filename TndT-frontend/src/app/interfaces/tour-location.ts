export interface TourLocation {
  tourid: number;
  name: string;
  location: string;
  duration: number;
  availableseats: number;
  difficulty:string;
  avgRating: number;
  price: number;
  description: string;
  begindate: Date;
  imageurl: string;
  isActive :boolean;
}
