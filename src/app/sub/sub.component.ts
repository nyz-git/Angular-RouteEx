import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { allResolved } from '../../../node_modules/@types/q';
import { MyServiceService } from '../my-service.service';
@Component({
  selector: 'app-sub',
  templateUrl: './sub.component.html',
  styleUrls: ['./sub.component.css']
})
export class SubComponent implements OnInit {

  constructor( private service:MyServiceService) { }

  ngOnInit() {
  }

  number1:number;
  number2:number;

  sub(s:NgForm){
    this.number1 = s.value.num1;
    this.number2 = s.value.num2;

    alert(this.number1 - this.number2);
    this.service.display();
  }

}
