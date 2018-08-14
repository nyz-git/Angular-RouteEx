import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes, Router } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ProductsComponent } from './products/products.component';
import { ProductComponent } from './product/product.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { CartComponent } from './cart/cart.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { BuyComponent } from './buy/buy.component';
import { CategoryComponent } from './category/category.component';

import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule } from '@angular/forms';


const appRoutes : Routes = [
  
  {path : '',component: HomeComponent},
  {path : 'products/:category',component: ProductsComponent},
  {path : 'product/:id',component: ProductComponent},
  {path : 'cart',component: CartComponent},
  {path : 'buy/:productId/:quantity/:cartId',component: BuyComponent},
  {path : 'about',component: AboutComponent},
  {path : 'contact',component: ContactComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component:RegisterComponent}

];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProductsComponent,
    ProductComponent,
    HeaderComponent,
    FooterComponent,
    CartComponent,
    AboutComponent,
    ContactComponent,
    BuyComponent,
    CategoryComponent,
    LoginComponent,
    RegisterComponent,
   

  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
