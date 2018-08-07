import { Component, OnInit } from '@angular/core';
import { NgForm } from '../../../node_modules/@angular/forms';
import { HttpClient } from '../../../node_modules/@angular/common/http';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user : User
  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  
  
  login(l:NgForm){
    this.user = new User();
    this.user.email= l.value.uname;
    this.user.password=l.value.pass;

    alert(this.user.email+"\n"+this.user.password);

    this.http.post("http://localhost:8080/JSON/LoginServlet",
    {"email": this.user.email,"password": this.user.password}).subscribe(

      (response) =>{

      },

      (error) =>{

      }
      );

    
  }

}
