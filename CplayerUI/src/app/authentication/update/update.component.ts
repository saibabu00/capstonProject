import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { User } from 'src/app/services/user';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
  myGroup!:FormGroup
  newUser: User = new User();
  users:any;
  USERID!:string;
  name: string;
  constructor(private loginService:AuthServiceService, private router:Router) {
    this.USERID = loginService.getUserId()!;
    this.name = loginService.getUserId()!;
   }

  ngOnInit(): void {
    this.myGroup = new FormGroup({
      'firstName':new FormControl(),
      'lastName': new FormControl(),
      'email':new FormControl(),
      'password': new FormControl(),
      'userId':new FormControl()
  
      
    });

    this.loginService.getUserDetailes(this.name).subscribe((data)=>{
      console.log("Data",data);
     this.users = data!;
    })
    
  }

  UpdateUser(){
    this.loginService.updateUser(this.users,this.name).subscribe((detailes)=>{
      console.log(detailes)
      alert("updated")
      this.router.navigate(['/UserProfile']);
    })
    
  }
}
