import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators,FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import {AuthService} from "../auth-service/auth-service/auth.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {

  signupForm:FormGroup|any;
  constructor(
    private service:AuthService,
    private fb:FormBuilder,
    private snackBar:MatSnackBar,
    private router:Router
    ) { }

  ngOnInit(): void {
    this.signupForm=this.fb.group({
      name:['',Validators.required],
      email:['',Validators.required],
      password:['',Validators.required]
    })
  }

  signup(){
    // @ts-ignore
    console.log(this.signupForm.value)
    this.service.signup(this.signupForm.value).subscribe((response)=>{
      console.log(response);
      if(response.id!=null){
        this.snackBar.open("You are signed up","Close",{duration :4000});
        this.router.navigateByUrl('/login');
      }
      else {
        this.snackBar.open(response.message,'Close',{duration: 4000});
      }
    },(error:any)=>{
      this.snackBar.open("Failed,try again",'Close',{duration:4000})
    })
  }

}

