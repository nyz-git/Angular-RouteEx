import { Component, OnInit } from '@angular/core';
import { NgForm } from '../../../node_modules/@angular/forms';
import { Router } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  constructor(private route:Router) { }

  ngOnInit() {
  }

  number1 : number;
  number2: number;

  add(b:NgForm){
    this.number1=b.value.n1;
    this.number2=b.value.n2;
    alert(this.number1*1 + this.number2*1);
    this.route.navigate(['']);
  }
}
