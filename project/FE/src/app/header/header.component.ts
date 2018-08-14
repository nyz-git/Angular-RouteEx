import { Component, OnInit } from '@angular/core';
import { ProductDataService } from '../product-data.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private productDataService: ProductDataService) { }

  isLoggedIn: boolean;

  ngOnInit() {

    

    this.productDataService.currentLoginUpdate.subscribe(
      
      (message) =>  {
        if(message)
          this.isLoggedIn = true;
        else
          this.isLoggedIn = false;
      }
    
    );
  }

}
