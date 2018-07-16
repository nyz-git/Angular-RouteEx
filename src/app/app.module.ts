import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule} from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AddComponent } from './add/add.component';
import { SubComponent } from './sub/sub.component';
import { MulComponent } from './mul/mul.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AddComponent,
    SubComponent,
    MulComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot([
        { path:'addition',component:AddComponent },
        {path:'subtraction',component:SubComponent}
      ])
],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
