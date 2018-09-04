import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { DataService } from '../data.service';
import { Cart } from '../cart';

@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  styleUrls: ['./buy.component.css']
})
export class BuyComponent implements OnInit {

  constructor(private dataService : DataService) { }

  user : User;
  carts: Array<Cart> = [];

  ngOnInit() {
    this.user = this.dataService.getUser();
    console.log(this.user.firstName);

    this.carts=this.dataService.getUserCart(this.user.id);
  }

  getProductName(prodId: number) {
    return this.dataService.getProductDescription(prodId).name;
  }

  totalCart(){
    let total : number = 0;
    for(let j = 0; j< this.carts.length;j++){
      total+=this.carts[j].amount;
    }

    return total;
  }
}
