import { Component, OnInit, ViewChild} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort'
import { MatTableDataSource } from '@angular/material/table';
import {CplayerServiceService} from 'src/app/services/cplayer-service.service';
import {Router} from '@angular/router';
import { PlayerDetailsComponent } from 'src/app/Player/player-details/player-details.component';
import { MatDialog } from '@angular/material/dialog';
import { CricPlayer } from 'src/app/cric-player';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-playerlist',
  templateUrl: './playerlist.component.html',
  styleUrls: ['./playerlist.component.css']
})
export class PlayerlistComponent implements OnInit {

  columnsToDisplay:string[] = ['playerName','matches','span','runs','hundreds','fifties','actions'];

  public CPlayersList:any=[]
  public cplayer: CricPlayer={} as CricPlayer;
  message:string="hellolo"
  dataSource:any;
   showSpinner:boolean=true;
   public check: String='1';
   public id:any;
   statusCode!: number;
   errorStatus: string="";
   @ViewChild(MatPaginator) paginator: MatPaginator |undefined;
   @ViewChild(MatSort) sort: MatSort |undefined;
   constructor(private CPlayerListService: CplayerServiceService,private router:Router,public dialog: MatDialog,private snackbar:MatSnackBar) { }

   ngOnInit(): void {

     this.CPlayerListService. getAllPlayers().subscribe
     (data=>
       {
        for(let i=0;i<data.length;i++){
        this.CPlayersList.push(data[i]);
        }
      
        this.getList();
        console.log(data);
       },
       error=>{
        console.log(error);
       }
       
     );
      }

//  getDetails(index:String)
//  {

//    console.log("index is "+index);
//    this.id=index;

//    const dialogRef = this.dialog.open(PlayerDetailsComponent, {
//     height: '75vh',
//     width: '60vw',
//      disableClose:true,
//     data:this.id
//    })
//   }
  addToFavorite(element:any)
  {

 this.cplayer.playerName=element.playerName;
 this.cplayer.matches=element.matches;
 this.cplayer.span=element.span;
 this.cplayer.runs=element.runs;
 this.cplayer.hundreds=element.hundreds;
 this.cplayer.fifties=element.fifties;

//  this.cplayer.country=element.country;
 console.log("---------------------------------------")
 console.log( this.cplayer)
 this.CPlayerListService. addPlayerToFavoriteList(this.cplayer).subscribe(
   data => {

     console.log("data is ",data);
     this.snackbar.open("Added to Favorites",'',{
       duration:4000,
       verticalPosition:'top'
     })
     }, error => {

      this.errorStatus = `${error.status}`;
      const errorMsg = `${error.error.message}`;
      this.statusCode = parseInt(this.errorStatus, 10);
      console.log("error messages are")
      console.log(this.errorStatus)
      console.log(errorMsg)
      console.log( this.statusCode)
      alert("Already added to Favourites")
    })


 }
  getList() {
    this.showSpinner=false;
  this.check='0';
  //console.log("the total players list is")
   //  console.log(this.CPlayersList.length);
     this.dataSource=new MatTableDataSource<any>(this.CPlayersList);
     this.dataSource.paginator=this.paginator;
     this.dataSource.sort=this.sort;
  }
}

