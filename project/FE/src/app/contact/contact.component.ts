import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ProductDataService } from '../product-data.service';
import { Router } from '@angular/router';
import { AlertsService } from 'angular-alert-module';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  constructor(private productDataService: ProductDataService,private router:Router) { }

  ngOnInit() {
  }


  postQuery(f: NgForm){
       
     this.productDataService.postUserQuery({"firstName":f.value.firstname,"lastName":f.value.lastname,"email":f.value.usermail,"query":f.value.message}).subscribe(
         
       (response)=>{
          
            if(response.message == 1){
               
               
               alert("Query posted successfully, we will get back to you soon");
               
               this.router.navigate(["/"]);
               
            }else if(response.message == -1){
              alert("Something went wrong");
            }

       }  

     );
    
  }

}
