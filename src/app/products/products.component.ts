import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '../../../node_modules/@angular/router';
import { Product } from '../product';
import { DataService } from '../data.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  constructor(private activateRoute : ActivatedRoute, private router : Router, private dataService : DataService) { }
products : Array<Product> = [];

  ngOnInit() {
    let category = this.activateRoute.snapshot.params["cat"];
    console.log(category);
   this.products = this.dataService.getProducts(category);
    console.log(this.products);

  }


}
