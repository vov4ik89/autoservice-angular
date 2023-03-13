import { Component } from '@angular/core';
import {Observable} from 'rxjs';
import {Order} from '../model/order';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Master} from '../model/master';
import {GlobalConstants} from '../model/globalConstants';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-masters',
  templateUrl: './masters.component.html',
  styleUrls: ['./masters.component.css']
})
export class MastersComponent {
  private url = GlobalConstants.apiURL + '/masters';
  masters: Array<Master>=[]
  orders: Array<Order>=[]
  salary?: number;

  masterForm = new FormGroup({
    name: new FormControl(''),
  })

  masterIdForm = new FormGroup({
    id: new FormControl(0),
  })

  salaryForm = new FormGroup({
    masterId: new FormControl(0),
    orderId: new FormControl(0),
  })

  constructor(private http: HttpClient){}

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' ,
    'Access-Control-Allow-Origin': '*'})
  };

  toNumber(value: string) {
    return Number(value);
  }

  save() {
    let name = this.masterForm.controls.name.value;
    if (!name) {
      return;
    }
    name = name.trim();
    this.createMaster({name} as Master)
      .subscribe(master => this.masters.push(master));
  }

  private createMaster(master: Master): Observable<Master> {
    return this.http.post<Master>(this.url, master, this.httpOptions).pipe();
  }

  getOrder() {
    const id = this.masterIdForm.controls.id.value;
    if (!id) {return;}
    this.http.get<Array<Order>>(this.url+'/'+id+'/orders')
      .subscribe(orders => this.orders = orders);
  }

  getSalary() {
    const masterId = this.salaryForm.controls.masterId.value;
    const orderId = this.salaryForm.controls.orderId.value;
    if (!masterId) {return;}
    this.http.get<number>(this.url+'/'+masterId+'/orders/'+orderId+'/salary')
      .subscribe(salary => this.salary = salary);
  }
}
