import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '../../../node_modules/@angular/router';
import { DataService } from '../data.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  constructor(private route:ActivatedRoute, private router:Router, private dataService : DataService) { }

  ngOnInit() {
    //alert(this.route.snapshot.params["cat"]);
    this.dataService.getProductsFromDb();
    
  }

}
