import { Component, OnInit } from '@angular/core';
import { NgForm } from '../../../node_modules/@angular/forms';
import { HttpClient } from '../../../node_modules/@angular/common/http';
import { User } from '../user';
import { Router } from '../../../node_modules/@angular/router';
import { DataService } from '../data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user : User
  constructor(private http: HttpClient, private router: Router, private dataService : DataService) { }

  ngOnInit() {
  }

  
  
  login(l:NgForm){
    this.user = new User();
    this.user.email= l.value.uname;
    this.user.password=l.value.pass;

    alert(this.user.email+"\n"+this.user.password);

    this.http.post<{message:number, user : User}>("http://localhost:8080/JSON/LoginServlet",
    {"email": this.user.email,"password": this.user.password}).subscribe(

      (response) =>{
        console.log(response.message);


        if (response.message==1) {
          alert("LOGIN SUCCESS");
          console.log(response.user);
          //setting the user object in dataService class once the user is loggedIn
          this.dataService.setUser(response.user);
          this.router.navigate(['']);
        } else {
          alert("LOGIN UNSUCCESSFUL");
        }

      },

      (error) =>{

      }
      );

    
  }

}
