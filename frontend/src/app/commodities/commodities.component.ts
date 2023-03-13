import { Component } from '@angular/core';
import {Commodity} from '../model/commodity';
import {Observable} from 'rxjs';
import { Location } from '@angular/common';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {GlobalConstants} from '../model/globalConstants';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-commodities',
  templateUrl: './commodities.component.html',
  styleUrls: ['./commodities.component.css']
})
export class CommoditiesComponent {
  commodities: Array<Commodity> =[]

  commodityForm = new FormGroup({
    name: new FormControl(''),
    price: new FormControl(0),
  })

  private url = GlobalConstants.apiURL + '/commodities';

  constructor(
    private http: HttpClient,
    private location: Location
  ) {}

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  save(): void {
    let name = this.commodityForm.controls.name.value;
    const price = this.commodityForm.controls.price.value;
    if (!name || !price) {
      return;
    }
    name = name.trim();
    this.createCommodity({name, price} as Commodity)
      .subscribe(commodity => {this.commodities.push(commodity)});
  }

  createCommodity(commodity: Commodity): Observable<Commodity> {
    return this.http.post<Commodity>(this.url, commodity, this.httpOptions).pipe();
  }

  updateCommodity(commodity: Commodity): Observable<Commodity> {
    return this.http.put<Commodity>(this.url+'/'+commodity.id, commodity, this.httpOptions).pipe();
  }
}
