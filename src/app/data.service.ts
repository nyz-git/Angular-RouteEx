import { Injectable } from '@angular/core';
import { HttpClient } from '../../node_modules/@angular/common/http';
import { Product } from './product';
import { User } from './user';
import { Cart } from './cart';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http:HttpClient) { }

  products : Array<Product> = [];
  carts : Array<Cart> = [];
  user: User;
  getProductsFromDb(){

    this.http.get<Array<Product>>("http://localhost:8080/JSON/ProductServlet").subscribe(

      (response) =>{
        this.products = response;
        console.log(this.products);
      },
      (error)=>{

      }

    );
  }

  getProducts(category: string) {

    return this.products.filter(
      (product) => product.category == category
    );
  }
  
getProductDescription(productId : number){
 return this.products.find(
   (product) => product.id == productId
 );
}

setUser(user : User){
this.user = user;
//console.log(this.user);
}

getUser(){
return this.user;
}


getCartsFromDb(){

  this.http.get<Array<Cart>>("http://localhost:8080/JSON/GetCartServlet").subscribe(

    (response) =>{
      this.carts = response;
      console.log(this.carts);
    },
    (error)=>{

    }

  );
}

getUserCart(userId:number) {
  
  return this.carts.filter(
    (cart) => cart.userId == userId
  );

  
}
}
