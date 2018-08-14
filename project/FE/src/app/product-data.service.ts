import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Product } from './product';
import { User } from './user';
import { Cart } from './cart';
import { BehaviorSubject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ProductDataService {

  constructor(private http: HttpClient) { }
  
  products: Product[];
  
  user: User;

  isLoggedIn: boolean = false;

  carts: Cart[] = [];
  
  private userLoginUpdate = new BehaviorSubject(false);
  currentLoginUpdate = this.userLoginUpdate.asObservable();
  
  changeUserUpdate(message: boolean) {
    this.userLoginUpdate.next(message)
  }

  getProductsFromDatabase(){
   
    this.http.get<Product[]>("http://localhost:8080/GreatOutdoors/ProductServlet").subscribe(
        (response) => this.products = response
    );
  
  }

  getProducts(category: string): Product[]{
    
    return this.products.filter(
      product => product.category == category 
    )


  } 

  getProduct(id: number): Product{

     return this.products.find(
      product => product.id == id
    );
      
  }

  login(email: string,password: string){
     
     
    return this.http.post<{message:number,user:User}>("http://localhost:8080/GreatOutdoors/LoginServlet",{"email":email,"password":password});


  }

  register(user: User){
    
     return this.http.post<{message:number}>("http://localhost:8080/GreatOutdoors/RegisterServlet",user);
      

  }

  placeOrder(orderData){

    

     return this.http.post<{message:number}>("http://localhost:8080/GreatOutdoors/OrderServlet",orderData);

  }
  
  setUser(user:User){
      this.user = user; 
      this.isLoggedIn = true;
  }
  
  getUser(){
    return this.user;
  }

  // isUserLoggedIn(){
  //    return this.isLoggedIn;
  // }

  addToCart(productId,quantity){

   
    
    return this.http.post<{message:number}>("http://localhost:8080/GreatOutdoors/CartServlet",{"operation":1,"userId":this.user.id,"productId":productId,"quantity":quantity});

  }

  removeFromCart(cartId){

    return this.http.post<{message:number}>("http://localhost:8080/GreatOutdoors/CartServlet",{"operation":-1,"cartId":cartId});

  }


  returnUserCart(){

      return this.carts;
    
  }


  postUserQuery(queryData){

    return this.http.post<{message:number}>("http://localhost:8080/GreatOutdoors/QueryServlet",queryData);


  } 


  getCart(){

    

    const params = new HttpParams().set("userId",this.user.id+"");

  
    
    return this.http.get<Cart[]>("http://localhost:8080/GreatOutdoors/CartServlet",{params: params}).subscribe(
          
        (response) => {

               this.carts = response

        }

    );

  }


}
