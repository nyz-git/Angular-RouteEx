import { Injectable } from '@angular/core';
import { HttpClient } from '../../node_modules/@angular/common/http';
import { Product } from './product';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http:HttpClient) { }

  products : Array<Product> = [];

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
  
}
