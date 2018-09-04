import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { User } from '../user';
import { Cart } from '../cart';
import { HttpClient } from '../../../node_modules/@angular/common/http';
import { Router } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(private dataService: DataService, private http: HttpClient, private router: Router) { }

  user: User;
  userId: number;
  carts: Array<Cart> = [];
  userJsonCart: any;
  ngOnInit() {
    // calling all users cart first when cart component is loaded
    this.dataService.getCartsFromDb();

    // fetching current loggedIn user obj because i want userId of current logged in user
    this.user = this.dataService.getUser();

    // setting the userId from user obj
    this.userId = this.user.id;

    console.log("User ID : " + this.userId);

    // getting current user cart only via his/her userId
    console.log(this.dataService.getUserCart(this.userId));
    this.carts = this.dataService.getUserCart(this.userId);

  }

  getProductName(prodId: number) {
    return this.dataService.getProductDescription(prodId).name;
  }

  getProductDesc(prodId: number) {
    return this.dataService.getProductDescription(prodId).description;
  }

  getProductQuantity() {

  }
  getProductPrice(prodId: number) {
    return this.dataService.getProductDescription(prodId).price;
  }


  updateCart(cartId: number, quantity: number, amount: number) {

    alert(cartId + "  " + quantity + " " + amount);
    this.http.post<{ message: number }>("http://localhost:8080/JSON/UpdateCartServlet", { "cartId": cartId, "quantity": quantity, "amount": amount }).subscribe(

      (response) => {
        if (response.message == 1) {
          alert("Cart Updated Successfully");
          this.dataService.getCartsFromDb();
        } else {
          alert("Cart Update Unsuccessful");

        }
      },
      (error) => {
        alert("OOPS!!!! somethings in not right")
      }

    );
  }

  deleteCart(cartId: number) {
    alert(cartId);
    this.dataService.deleteCartDS(cartId);

  }

  totalCart() {
    // this.userJsonCart = JSON.parse(JSON.stringify(this.carts));console.log("ttt "+this.total );
    // console.log(this.userJsonCart);
    // for (let i = 0; i < this.userJsonCart.length; i++) {  //loop through the array
    //   this.total += Number.parseInt(this.userJsonCart[i].amount);  //adds the json value
    // }

    let total : number = 0;
    for(let j = 0; j< this.carts.length;j++){
      total+=this.carts[j].amount;
    }

    //const total = this.userJsonCart.value.reduce((sum, item) => sum + item.amount, 0);

    // let total1: number = this.userJsonCart.value.reduce(
    //   (a: number, b) => a + b.amount, 0);

      console.log(total);

    return total;
  }
}
