import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '../../../node_modules/@angular/router';
import { Product } from '../product';
import { DataService } from '../data.service';
import { NgForm } from '../../../node_modules/@angular/forms';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  constructor(private activatedRoute : ActivatedRoute, private dataService : DataService) { }
  product : Product;
  ngOnInit() {
    let productId = this.activatedRoute.snapshot.params["id"];
    this.product = this.dataService.getProductDescription(productId);
    console.log(productId);
}

addToCart(f: NgForm) {
alert("Product added to cart");
}

}
