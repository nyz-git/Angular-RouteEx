import { Component, OnInit } from '@angular/core';
import { NgForm } from '../../../node_modules/@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  name:string;
  password:string;
  
  login(l:NgForm){
    this.name= l.value.uname;
    this.password=l.value.pass;

    alert(this.name+"\n"+this.password);
    
  }

}
