import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes} from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AddComponent } from './add/add.component';
import { SubComponent } from './sub/sub.component';
import { MulComponent } from './mul/mul.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CategoryComponent } from './category/category.component';
import { FooterComponent } from './footer/footer.component';
import { AboutComponent } from './about/about.component';
import { HttpClientModule } from '@angular/common/http';
import { ProductsComponent } from './products/products.component';
import { ProductComponent } from './product/product.component';
import { CartComponent } from './cart/cart.component';
import { BuyComponent } from './buy/buy.component';


export const routes: Routes = [
  { path:'addition',component:AddComponent },
  { path:'about',component:AboutComponent },
  {path:'subtraction',component:SubComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterComponent},
  {path:'products/:cat',component:ProductsComponent},
  {path:'product/:id',component:ProductComponent},
  {path:'cart',component:CartComponent},
  {path:'buy', component:BuyComponent}
  ]

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AddComponent,
    SubComponent,
    MulComponent,
    LoginComponent,
    RegisterComponent,
    CategoryComponent,
    FooterComponent,
    AboutComponent,
    ProductsComponent,
    ProductComponent,
    CartComponent,
    BuyComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
