import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup,FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { User } from 'src/app/services/user';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  // myGroup!:FormGroup
  newUser: User = new User();
  constructor(private loginService:AuthServiceService, private router:Router,private snackbar:MatSnackBar) { }

  ngOnInit(): void {
   
  }
  formgroup = new FormGroup({
    firstName:new FormControl('',[Validators.required,Validators.minLength(3),Validators.pattern('[a-zA-Z ]*')]),
    lastName: new FormControl('',[Validators.required,Validators.minLength(3),Validators.pattern('[a-zA-Z ]*')]),
    userId: new FormControl('',[Validators.required,Validators.minLength(6)]),
    password: new FormControl('',[Validators.required,Validators.minLength(8)]),
    email: new FormControl('',[Validators.required,Validators.pattern('^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$')]),
  }
  );



  registerUser() {
    console.log("Register User data:", this.newUser);
    this.loginService.registerUser(this.newUser).subscribe(data => {
      console.log("User registered", data);



      this.snackbar.open("Registered successfully!",'',{
        duration:4000,
        verticalPosition:'top'
      })
      this.router.navigate(['/']);



    },
    error =>{
      alert("User Already exist");
    });
  }




}
