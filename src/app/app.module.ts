import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule} from '@angular/router';
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
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot([
        { path:'addition',component:AddComponent },
        { path:'about',component:AboutComponent },
        {path:'subtraction',component:SubComponent},
        {path:'login',component:LoginComponent},
        {path:'register',component:RegisterComponent},
        {path:'category/:cat',component:CategoryComponent}
      ])
],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
