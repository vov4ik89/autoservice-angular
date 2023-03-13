import {Favor} from './favor';
import {Commodity} from './commodity';

export interface Order {
  id: number;
  carId: number;
  description: string;
  acceptDate: string;
  favors: Array<Favor>;
  parts: Array<Commodity>;
  status: string;
  price: number;
  endDate: string;
}
