import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {map, Observable, tap} from "rxjs";
import {StorageService} from "../storage-service/storage.service";

const BASIC_URL=['http://localhost:8080/']
export const AUTH_HEADER="Authorization";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http:HttpClient,
    private storage:StorageService) { }

  signup(signupRequest:any):Observable<any>{
    return this.http.post(BASIC_URL+"signup/sign-up",signupRequest);
  }
  login(loginRequest:any):Observable<any>{
    return this.http.post(BASIC_URL+"authentication",loginRequest,{observe:'response'}).pipe(
      tap(_=>this.log("User Authentication")),
      map((res:HttpResponse<any>)=>{
      this.storage.saveUser(res.body);
      // @ts-ignore
        const tokenLenght=res.headers.get(AUTH_HEADER).length;
        // @ts-ignore
        const bearerToken=res.headers.get(AUTH_HEADER).substring(7,tokenLenght);
        this.storage.saveToken(bearerToken);
        return res;
    }));
  }
  log(message:string){
    console.log("user auth service"+message);
  }
}
