import { Component, OnInit } from '@angular/core';
import { NgForm } from '../../../node_modules/@angular/forms';

@Component({
  selector: 'app-mul',
  templateUrl: './mul.component.html',
  styleUrls: ['./mul.component.css']
})
export class MulComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  number1 : number;
  number2: number;

  mul(m:NgForm){
    this.number1=m.value.n1;
    this.number2=m.value.n2;
    alert(this.number1 * this.number2);
  }

}
