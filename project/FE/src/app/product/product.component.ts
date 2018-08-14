import { Component, OnInit, Input } from '@angular/core';
import { Product } from '../product';
import { ActivatedRoute, Router } from '@angular/router'; 
import { ProductDataService } from '../product-data.service';



@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  
  product: Product;

  productImages: string[];

  mainImageSrc: string;

  isLoggedIn: boolean;

  productId: number;

  quantity: number;

  cartData;

  constructor(private route: ActivatedRoute, private productDataService: ProductDataService,private router: Router) { }

  ngOnInit() {

    this.quantity = 1;

    this.productId = this.route.snapshot.params["id"];

    this.product = this.productDataService.getProduct(this.productId);

    this.mainImageSrc = this.product.image1;
    this.productImages = [this.product.image1,this.product.image2,this.product.image3,this.product.image4,this.product.image5]

    this.productDataService.currentLoginUpdate.subscribe(
      
      (message) =>  {
        if(message)
          this.isLoggedIn = true;
        else
          this.isLoggedIn = false;
      }
    
    );
  
  }

 
  buyProduct(){

       //this.isLoggedIn = this.productDataService.isUserLoggedIn();
       
       
       if(this.isLoggedIn){
           this.router.navigate(["/buy",this.productId,this.quantity,0]);
       }else{
         alert("Login first");
       }

  }


  addToCart(){
   
    //this.isLoggedIn = this.productDataService.isUserLoggedIn();


    if(this.isLoggedIn){
      
      
    
      this.productDataService.addToCart(this.productId,this.quantity).subscribe(
           
             (response) => {

                 
               if(response.message == 1){
                 alert("Added to cart successfully")
                 
                 this.productDataService.getCart();
                 

                } else if(response.message == -1)
                 alert("Something went wrong") 
                 

             }

      );
   
    }else{
       alert("Login first");
    }

  }

  highlight(e){

     if(e.type==="mouseenter"){
       this.mainImageSrc = e.srcElement.src;
       e.srcElement.style.border = "solid 2px blue"; 
     }else{
        e.srcElement.style.border = "none";
       this.mainImageSrc = this.product.image1;   
     }
  }

}
