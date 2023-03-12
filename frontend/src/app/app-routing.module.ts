import { NgModule } from '@angular/core';
import {OrdersComponent} from './orders/orders.component';
import {RouterModule, Routes} from '@angular/router';
import {OwnerComponent} from './owner/owner.component';
import {FavorsComponent} from './favors/favors.component';
import {CommoditiesComponent} from './commodities/commodities.component';
import {MastersComponent} from './masters/masters.component';
import {CarComponent} from './car/car.component';

const routes: Routes = [{
    path: 'auto_service',
    children: [
      {
        path: 'orders',
        component: OrdersComponent
      },
      {
        path: 'owners',
        component: OwnerComponent
      },
      {
        path: 'favors',
        component: FavorsComponent
      },
      {
        path: 'commodities',
        component: CommoditiesComponent
      },
      {
        path: 'masters',
        component: MastersComponent
      },
      {
        path: 'cars',
        component: CarComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
