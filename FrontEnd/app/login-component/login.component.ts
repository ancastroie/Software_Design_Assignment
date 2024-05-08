import {Component, Input, Output , EventEmitter} from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  login:FormGroup|any;
  constructor(private _http:HttpClient, private _route:Router) { }

  ngOnInit(): void {
    this.login = new FormGroup({
      'email': new FormControl(),
      'password': new FormControl()
    })
  }
  logindata(login:FormGroup){
    // console.log(this.login.value);
    this._http.get<any>("http://localhost:3000/signupUsersList")
      .subscribe(res=>{
        const user = res.find((a:any)=>{
          return a.email === this.login.value.email && a.password === this.login.value.password
        });

        if(user){
          alert('you are successfully login');
          this.login.reset();
          this._route.navigate(['questions']);
        }else{
          alert('User Not Found');
          this._route.navigate(['login']);
        }

      }, err=>{
        alert('Something was wrong');
      })


  }

}
