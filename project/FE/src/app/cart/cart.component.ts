import { Component, OnInit } from '@angular/core';
import { Cart } from '../cart';
import { ProductDataService } from '../product-data.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  
  carts: Cart[] = [];
  

  constructor(private productDataService: ProductDataService,private router: Router) { }

  ngOnInit() {

    this.carts = this.productDataService.returnUserCart();
    
    
    
  }

  getProductName(productId){
     return this.productDataService.getProduct(productId).name;
  }

  getProductPrice(productId){
    return this.productDataService.getProduct(productId).price;
  }

  getProductImage(productId){
    return this.productDataService.getProduct(productId).image1;
  }

  placeOrder(productId,quantity,cartId){
    this.router.navigate(["/buy",productId,quantity,cartId]);
  }


  removeItem(cartId,index){
  
      this.productDataService.removeFromCart(cartId).subscribe(
        
         (response) => {
            
             if(response.message == 1){
                 
                  if(this.carts.length == 1){
                     this.carts = [];
                  }else{

                  this.carts.splice(1,index);                

                  }
             }else if(response.message == -1){
               alert("Something went wrong")
             }

         }


      );
     
  }

}
