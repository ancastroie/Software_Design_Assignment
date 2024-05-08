import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators,FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  constructor( private _route:Router, private _http:HttpClient) { }
  signup:FormGroup|any;
  signupuser:any;
  ngOnInit(): void {
    this.signup = new FormGroup({
      'fname': new FormControl(),
      'lname':new FormControl(),
      'email':new FormControl(),
      'phone':new FormControl(),
      'password': new FormControl()
    })
  }

  signupdata(singup:FormGroup){
    //console.log(this.singup.value);
    this.signupuser = this.signup.value.fname
    this._http.post<any>("http://localhost:3000/signupUsersList", this.signup.value)
      .subscribe(res=>{
        alert('data added successfully');
        this.signup.reset();
        this._route.navigate(['login']);
      }, err=>{
        alert('Somthing went wrong');
      })

  }

}

