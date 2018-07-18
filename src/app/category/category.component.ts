import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  constructor(private route:ActivatedRoute, private router:Router) { }

  ngOnInit() {
    alert(this.route.snapshot.params["cat"]);
    this.router.navigate(['']);
  }

}
