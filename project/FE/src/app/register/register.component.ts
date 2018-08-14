import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ProductDataService } from '../product-data.service';
import { User } from '../user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  phoneValidate : boolean = false;

  user: User;

  states: string[] = ["Andhra pradesh",
  "Arunachal pradesh",
  "Assam","Bihar",
  "Chhattisgarh",
  "Goa",
  "Gujarat",
  "Haryana",
  "Karnataka",
  "Madhya Pradesh",
  "Maharashtra",
  "Manipur",
  "Meghalaya",
  "Mizoram",
  "Nagaland",
  "Odisha",
  "Punjab",
  "Rajasthan",
  "Sikkim",
  "Tamil Nadu",
  "Telangana",
  "Tripura",
  "Uttarakhand",
  "Uttar Pradesh",
  "West Bengal",
  "Andaman and Nicobar Islands",
  "Chandigarh",
  "Dadra and Nagar Haveli",
  "Daman and Diu",
  "Delhi",
  "Lakshadweep",
  "Puducherry"]; 

  constructor(private productDataService: ProductDataService,private router: Router) { }

  ngOnInit() {
  }

  register(f: NgForm){
    
    this.user = new User();

    this.user.firstName = f.value.firstname;
    this.user.lastName = f.value.lastname;
    this.user.phoneNumber = f.value.phonenumber;
    this.user.email = f.value.email;
    this.user.password = f.value.password;
    this.user.addressLine1 = f.value.addressLine2;
    this.user.addressLine2 = f.value.addressLine2;
    this.user.state = f.value.state;
    this.user.pincode = f.value.pincode;

    this.productDataService.register(this.user).subscribe(
          
        (response)=> {
         
             if(response.message > 0){
               alert("User registered successfully");
               this.user.id = response.message;
               this.productDataService.setUser(this.user);
               this.productDataService.changeUserUpdate(true);
               this.router.navigate(["/"])

             }else{
                  alert("User already exists")
              } 

        }

    );

     
    
  }

  
  validateNumber(e){
    if(e.srcElement.value.length == 10){
      e.srcElement.style.border = "none";
      this.phoneValidate = true;
      
    }else{
      e.srcElement.style.border = "1px solid red";
      this.phoneValidate = false;
          
    }
  }



}
