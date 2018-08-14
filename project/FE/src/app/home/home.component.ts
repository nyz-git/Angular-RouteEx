import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  categoryMountaineering: {name:string,coverImg:string};
  categoryCamping: {name:string,coverImg:string};
  categoryGolf: {name:string,coverImg:string};
  categoryPersonal: {name:string,coverImg:string};
  categoryOutdoor: {name:string,coverImg:string};

  constructor() { }

  ngOnInit() {
    this.categoryMountaineering  = {"name":"Mountaineering Equipment","coverImg":"../../assets/images/mountaineering.jpg"};
    this.categoryCamping = {"name":"Camping Equipment","coverImg":"../../assets/images/camping.jpg"};
    this.categoryGolf = {"name":"Golf Equipment","coverImg":"../../assets/images/golf.jpg"};
    this.categoryPersonal = {"name":"Personal care","coverImg":"../../assets/images/personal.jpg"};
    this.categoryOutdoor = {"name":"Outdoor protection","coverImg":"../../assets/images/outdoor.jpg"};
  }

}
