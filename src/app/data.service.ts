import { Injectable } from '@angular/core';
import { HttpClient } from '../../node_modules/@angular/common/http';
import { Product } from './product';
import { User } from './user';
import { Cart } from './cart';
import { Router } from '../../node_modules/@angular/router';
import { BehaviorSubject } from '../../node_modules/rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient, private router: Router) { }

  products: Array<Product> = [];
  carts: Array<Cart> = [];
  user: User;
  cart: Cart;

  private userLoginUpdate = new BehaviorSubject(false);
  currentLoginUpdate = this.userLoginUpdate.asObservable();

  changeUserUpdate(message: boolean) {
    this.userLoginUpdate.next(message)
  }

  getProductsFromDb() {

    this.http.get<Array<Product>>("http://localhost:8080/JSON/ProductServlet").subscribe(

      (response) => {
        this.products = response;
        console.log(this.products);
      },
      (error) => {

      }

    );
  }

  getProducts(category: string) {

    return this.products.filter(
      (product) => product.category == category
    );
  }

  getProductDescription(productId: number) {
    return this.products.find(
      (product) => product.id == productId
    );
  }

  setUser(user: User) {
    this.user = user;
    //console.log(this.user);
  }

  getUser() {
    return this.user;
  }

  // setCart(cart : Cart){
  //   this.cart = cart;
  // }

  // getCart(){
  //   return this.cart;
  // }

  deleteCartDS(cartId: number) {
    this.http.post<{ message: number }>("http://localhost:8080/JSON/DeleteCartServlet", { "cartId": cartId }).subscribe(

      (response) => {
        if (response.message == 1) {
          alert("Cart Item Deleted Successfully");
          //this.getCartsFromDb();
          //location.reload();
          //this.router.navigate(['/cart']);
          this.router.navigateByUrl('/about', { skipLocationChange: true }).then(() =>
            this.router.navigate(["/cart"]));

          // this.router.onSameUrlNavigation : "reload";
        } else {
          alert("Cart Item Deletion Unsuccessful");

        }
      },
      (error) => {

      }

    );

  }

  getCartsFromDb() {

    this.http.get<Array<Cart>>("http://localhost:8080/JSON/GetCartServlet").subscribe(

      (response) => {
        this.carts = response;
        console.log("ALL CART");
        console.log(this.carts);
      },
      (error) => {

      }

    );
    return this.carts;
  }

  getUserCart(userId: number) {
    return this.carts.filter(
      (cart) => cart.userId == userId
    );


  }
}
