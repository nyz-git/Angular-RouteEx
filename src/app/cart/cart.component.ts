import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { User } from '../user';
import { Cart } from '../cart';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(private dataService : DataService) { }
  user : User
  userId : number;
  carts : Array<Cart> = [];
  ngOnInit() {
    //calling all users cart first when cart component is loaded 
    this.dataService.getCartsFromDb();

    //fetching current loggedIn user obj because i want userId of current logged in user
    this.user = this.dataService.getUser();

    //setting the userId from user obj
    this.userId = this.user.id;

    console.log("User ID : "+this.userId);

    //getting current user cart only via his/her userId
    console.log(this.dataService.getUserCart(this.userId));
    this.carts = this.dataService.getUserCart(this.userId);
  }

  getProductName(prodId : number){
    return this.dataService.getProductDescription(prodId).name;
  }

  getProductDesc(prodId : number){
    return this.dataService.getProductDescription(prodId).description;
  }

  getProductQuantity(){
    
  }
  getProductPrice(prodId : number){
    return this.dataService.getProductDescription(prodId).price;
  }

  
}
