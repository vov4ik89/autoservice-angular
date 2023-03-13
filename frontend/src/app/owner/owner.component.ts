import { Component } from '@angular/core';
import {Owner} from '../model/owner';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Order} from '../model/order';
import {GlobalConstants} from '../model/globalConstants';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-owner',
  templateUrl: './owner.component.html',
  styleUrls: ['./owner.component.css']
})
export class OwnerComponent {
  private url = GlobalConstants.apiURL + '/owners';
  owners: Array<Owner> = [];
  orders: Array<Order> = [];

  constructor(
    private http: HttpClient
  ) {}

  reactiveForm = new FormGroup({
    name: new FormControl(''),
  })

  idForm = new FormGroup({
    id: new FormControl(0),
  })

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  save() {
    let name = this.reactiveForm.controls.name.value;
    if (!name) {
      return;
    }
    name = name.trim();
    this.createOwner({name} as Owner)
      .subscribe(owner => {this.owners.push(owner);});
  }

  private createOwner(owner: Owner): Observable<Owner> {
    return this.http.post<Owner>(this.url, owner, this.httpOptions)
  }

  private updateOwner(owner: Owner): Observable<Owner> {
    return this.http.put<Owner>(this.url+'/'+owner.id, owner, this.httpOptions);
  }

  getAllOrderById() {
    const id = this.idForm.controls.id.value;
    this.http.get<Array<Order>>(this.url+'/'+id+'/orders')
      .subscribe(responseOrders => this.orders = responseOrders);
  }
}
