import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { NgForm } from '../../../node_modules/@angular/forms';
import { HttpClient } from '../../../node_modules/@angular/common/http';
import { Router } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private http: HttpClient, private router:Router) { }

  ngOnInit() {
  }
  user: User;
  register(reg:NgForm){
    this.user = new User();
    this.user.firstName=reg.value.fname;
    this.user.lastName=reg.value.lname;
    this.user.phoneNumber=reg.value.phone;
    this.user.email=reg.value.email;
    this.user.password=reg.value.pass;
    this.user.addressLine1=reg.value.addressL1;
    this.user.addressLine2=reg.value.addressL2;
    this.user.state=reg.value.state;
    this.user.pincode=reg.value.pcode;

    //alert(this.user.firstName+" "+this.user.lastName+"\n"+this.user.email);

    this.http.post<{message:number}>("http://localhost:8080/JSON/RegisterServlet",
  {"firstName": this.user.firstName,"lastName":this.user.lastName,"phone":this.user.phoneNumber,"email":this.user.email,"password":this.user.password,"addressLine1":this.user.addressLine1,"addressLine2":this.user.addressLine2,"state":this.user.state,"pincode":this.user.pincode}).subscribe(
  
  (response) =>{
    if (response.message == 1) {
      alert("Registration Successfull!!!!! \n LOGIN NOW");
      this.router.navigate(['/login']);
    } else {
      alert("Registration UnSuccessful");
    }

  },

  (error) =>{
    alert("Something went wrong!!!!!!  Plz login again");
  });

  }
}
