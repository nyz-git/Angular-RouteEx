import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { Product } from '../product';
import { ActivatedRoute,Router } from '@angular/router';
import { ProductDataService } from '../product-data.service';
import { Address } from '../address';

@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  styleUrls: ['./buy.component.css']
})
export class BuyComponent implements OnInit {


  user: User;

  product: Product;

  productId: number;

  quantity: number;

  totalPrice: number;

  addresses: Address[] = [];
  
  address: Address; 

  orderData;

  cartId: number;

  states: string[] = ["Andhra pradesh",
                      "Arunachal pradesh",
                      "Assam","Bihar",
                      "Chhattisgarh",
                      "Goa",
                      "Gujarat",
                      "Haryana",
                      "Karnataka",
                      "Madhya Pradesh",
                      "Maharashtra",
                      "Manipur",
                      "Meghalaya",
                      "Mizoram",
                      "Nagaland",
                      "Odisha",
                      "Punjab",
                      "Rajasthan",
                      "Sikkim",
                      "Tamil Nadu",
                      "Telangana",
                      "Tripura",
                      "Uttarakhand",
                      "Uttar Pradesh",
                      "West Bengal",
                      "Andaman and Nicobar Islands",
                      "Chandigarh",
                      "Dadra and Nagar Haveli",
                      "Daman and Diu",
                      "Delhi",
                      "Lakshadweep",
                      "Puducherry"]; 


                   

  constructor(private activatedRoute: ActivatedRoute, private productDataService: ProductDataService,private router:Router) { }

  ngOnInit() {
    
      this.productId = this.activatedRoute.snapshot.params["productId"];
      this.quantity = this.activatedRoute.snapshot.params["quantity"];
      this.cartId = this.activatedRoute.snapshot.params["cartId"];
      this.product = this.productDataService.getProduct(this.productId);

      this.totalPrice = this.product.price * this.quantity;

      this.user = this.productDataService.getUser();
      
      this.address = new Address();
      this.address.addressLine1 = this.user.addressLine1;
      this.address.addressLine2 = this.user.addressLine2;
      this.address.state = this.user.state;
      this.address.pincode = this.user.pincode;      
 
      this.addresses.push(this.address);

  }

  calculatePrice(){

     
      this.totalPrice = this.product.price * this.quantity;

    
  }

  addNewAddress(){
      
    
    this.addresses.push(new Address());

  }

  placeOrder(){

     this.orderData = {"userId":this.user.id,"productId":this.productId,"quantity":this.quantity,"price":(this.totalPrice + 100),"addresses":this.addresses,"cartId":this.cartId};

     this.productDataService.placeOrder(this.orderData).subscribe(
       
        (response) => {
            
          console.log(response);
            
            if(response.message > 0){
              alert("Order placed successfully, order id "+response.message);

              if(this.cartId > 0){
                this.productDataService.getCart();
              }

              this.router.navigate(["/"]);
            }else
            alert("Could not place your order, something went wrong") 

        }
      
     );

  }

}
