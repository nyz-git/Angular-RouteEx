import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MyServiceService {

  constructor() { }

  display(){
    alert("SERVICE CALLED");
  }
}
