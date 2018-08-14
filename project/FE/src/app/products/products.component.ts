import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductDataService } from '../product-data.service';


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  
 // product: Product;

  products : Product[];

  constructor(private route: ActivatedRoute, private router: Router, private productDataService: ProductDataService) { }

  ngOnInit() {

      
     this.products = this.productDataService.getProducts(this.route.snapshot.params["category"]); 
       
    
  }
  
  showDescription(id){
     
    this.router.navigate(["/product",id]);
 }

}
