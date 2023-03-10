import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { User } from 'src/app/services/user';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  userID!:string;
  firstName!:string;
  lastName!:string;
  email!:string;
  password!:string;
  name!:string;
  USERID!:string;
  // user: User = new User;
  users:any;
  constructor(private LoginService:AuthServiceService,private router:Router){
    this.USERID = LoginService.getUserId()!;
    this.name = LoginService.getUserId()!;
   

  }

  ngOnInit(): void {
    this.LoginService.getUserDetailes(this.name).subscribe((data)=>{
      console.log("Data",data);
     this.users = data!;
    })
  }
    Update(){
      this.router.navigate(['/UserProfile']);
    }

 
 
}
