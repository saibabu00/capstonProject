import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable ,BehaviorSubject} from 'rxjs';
import { LoginComponent } from '../authentication/login/login.component';
import { NavbarComponent } from '../Player/navbar/navbar.component';
import { SearchComponent } from '../Player/search/search.component';
import { User } from './user';


export const USER_ID = "username";
export const TOKEN_NAME:string = "jwt_token";
@Injectable({
  providedIn: 'root'
})
export class AuthServiceService{


  loginPoint!: string;
  registerPoint!: string;
  userDetailesPoint!:string;
  username!: string;
  updatePoint!:string;
  public search = new BehaviorSubject<String>("");

  constructor(private http:HttpClient, private router: Router) {
   this.loginPoint =  "http://localhost:8081/api/v1/userservice/login";
   this.registerPoint = "http://localhost:8081/api/v1/userservice/register";
   this.userDetailesPoint = "http://localhost:8081/api/v1/userservice/user/";
  this.updatePoint = "http://localhost:8081/api/v1/userservice/user/";
  }

 
  
 
  
    //login user // generate token
  dologin(user: User) {
    const url = this.loginPoint;
    sessionStorage.setItem(USER_ID,user.userId);
    this.username = sessionStorage.getItem(USER_ID)!;
    return this.http.post(url, user);
  }


  //for login user
  loginUser(token:string){
    localStorage.setItem("token",token)
    return true;
  }

 


  isLoggedIn(){
    let token = localStorage.getItem("token");
    if(token==undefined || token==='' || token==null)
    {
      return false;
    }else{
      return true;
    }
  }


  
  getToken(){
    return localStorage.getItem("token");    
  }

  getUserId(){
    return sessionStorage.getItem(USER_ID);
  }

  registerUser(newUser: any) {
    const url =this.registerPoint;
    return this.http.post(url, newUser, {responseType: 'text'});
  }


  getUserDetailes(userId: any){
     const url = `${this.userDetailesPoint}${userId}` ;
     return this.http.get(url);
  }
  
  updateUser(newUser: any,userId:any) {
    const url =`${this.updatePoint}${userId}` ;
    return this.http.put(url, newUser, {responseType: 'text'});
  }
  
  //for logout the user
  logout(){
    localStorage.removeItem("token");
    localStorage.clear();
    sessionStorage.removeItem(USER_ID);
    sessionStorage.clear();
    console.log("logged out");
    return true;
  }
  // getSearchResults(){
  //   return this.searchResults.searchResults();
  // }
}



