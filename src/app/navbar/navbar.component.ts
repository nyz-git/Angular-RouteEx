import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private dataService : DataService) { }

  isLoggedIn : boolean;

  ngOnInit() {
    this.dataService.currentLoginUpdate.subscribe(

      (loginStatus) => {

        if (loginStatus) {
          this.isLoggedIn = true;
        } else {
          this.isLoggedIn = false;
        }

      }

    );
  }

}
