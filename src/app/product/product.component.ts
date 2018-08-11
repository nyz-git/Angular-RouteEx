import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '../../../node_modules/@angular/router';
import { Product } from '../product';
import { DataService } from '../data.service';
import { NgForm } from '../../../node_modules/@angular/forms';
import { Cart } from '../cart';
import { User } from '../user';
import { HttpClient } from '../../../node_modules/@angular/common/http';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  constructor(private http : HttpClient,private activatedRoute : ActivatedRoute, private dataService : DataService) { }
  product : Product;
  cart : Cart;
  user : User;
  ngOnInit() {
    let productId = this.activatedRoute.snapshot.params["id"];
    this.product = this.dataService.getProductDescription(productId);
    console.log(productId);
    this.user = this.dataService.getUser();
}

addToCart(f: NgForm) {

alert("Add to cart clicked");
this.cart = new Cart();
this.cart.productId = this.product.id;
this.cart.userId =  this.user.id;
this.cart.quantity = f.value.quantity;
alert(this.product.id+" "+this.cart.userId +" "+this.cart.quantity);

this.http.post<{message:number}>("http://localhost:8080/JSON/CartServlet",this.cart/*{"productId":this.cart.productId,"userId":this.cart.userId,"quantity":this.cart.quantity}*/).subscribe(

(response) => {
  if (response.message == 1) {
    alert("Added to cart successfully");  
        
  } else {
    alert("Add to cart unsuccessful");
  
  }
},
(error) =>{

}

);

} 

}
