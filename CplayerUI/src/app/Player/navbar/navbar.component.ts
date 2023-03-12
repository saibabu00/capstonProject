import { Component, Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { CplayerServiceService } from 'src/app/services/cplayer-service.service';

import { User } from 'src/app/services/user';
import { SearchComponent } from '../search/search.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
@Injectable({
  providedIn: 'root'
})
export class NavbarComponent implements OnInit {

  user!: User;
  public loggedIn=false;
  USERID!:string;

  public searchTerm : string ='';
  searchName!: string;
  searchKey:any;
  Detailes:any=[];
  DetailsData:any=[];

  constructor(private loginService:AuthServiceService, private router:Router,private PlayerService:CplayerServiceService,private snackbar:MatSnackBar) { }

  ngOnInit(): void {
    this.loggedIn=this.loginService.isLoggedIn();
    this.USERID = this.loginService.getUserId()!;
    // console.log("User----->"+this.user.userId);
  }


  logoutUser(){
    this.loginService.logout()
    this.loggedIn=false;

    //location.reload()
    this.snackbar.open("Logged out successfully!",'',{
      duration:4000,
      verticalPosition:'top'
    })
    this.router.navigate(['/']);

  }


  searchOnClick(){
  // console.log(this.searchComp.searchResults());
  sessionStorage.setItem("searchname",this.searchName);
    // console.log("Name",this.searchName)
    console.log( sessionStorage.getItem('searchname'));
    this.searchKey = sessionStorage.getItem('searchname');

    // sessionStorage.removeItem('searchname');
    console.log("redirected")
    this.searchKey = sessionStorage.getItem('searchname');
    this.PlayerService.getSearchPlayer(this.searchKey).subscribe((data:any)=>{
      this.Detailes=data;
      this.DetailsData=data.data;
       console.log(this.Detailes);
       console.log(this.DetailsData)
       for(let i=0;i<this.DetailsData.length;i++)
       {
         console.log(this.DetailsData[i].name)
   }
    this.router.navigate(['/search']);


    })
  }


}
