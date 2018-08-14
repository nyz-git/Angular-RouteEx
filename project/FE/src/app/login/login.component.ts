import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ProductDataService } from '../product-data.service';
import { User } from '../user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  


  user: User;

  constructor(private productDataService: ProductDataService,private router: Router) { }

  ngOnInit() {
  }


  login(f: NgForm){
       
      this.productDataService.login(f.value.email,f.value.password).subscribe(
        
          (response) => {
            
            if(response.message == -1)
              alert("Wrong password");
            else if(response.message == -2)
               alert("User does not exist");  
            else if(response.message == 1){
            
                alert("Logged in successfully");

                this.user = new User();

                this.user.id = response.user.id;
                this.user.firstName = response.user.firstName;
                this.user.lastName = response.user.lastName;
                this.user.phoneNumber = response.user.phoneNumber;
                this.user.email = response.user.email;
                this.user.password = this.user.password;
                this.user.addressLine1 = response.user.addressLine1;
                this.user.addressLine2 = response.user.addressLine2;
                this.user.state = response.user.state;
                this.user.pincode = response.user.pincode;
                
                this.productDataService.setUser(this.user);

                this.productDataService.getCart();

                this.productDataService.changeUserUpdate(true);

                this.router.navigate(["/"]); 
                
            }

          }
           
      );

  }
}
