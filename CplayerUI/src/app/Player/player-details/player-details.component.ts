import { Component, OnInit, Inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {CplayerServiceService} from 'src/app/services/cplayer-service.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatDialog } from '@angular/material/dialog';
import {Router} from '@angular/router';
import {PlayerStatisticsComponent} from 'src/app/Player/player-statistics/player-statistics.component'
@Component({
  selector: 'app-player-details',
  templateUrl: './player-details.component.html',
  styleUrls: ['./player-details.component.css']
})
export class PlayerDetailsComponent implements OnInit {
  playerId:string="";

  CPlayersDetails:any=[];

  constructor(private router:Router,public dialog: MatDialog,private CPlayerListService: CplayerServiceService, private route:ActivatedRoute, 
    public dialogRef: MatDialogRef<PlayerDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public id:any ) {
      this. playerId= this.id;
     }

  ngOnInit(): void {
  
  
    //let id=this.route.snapshot.paramMap.get('id')+"";
    //this.playerId=id;
    this.CPlayerListService. getPlayerDetails(this.playerId).subscribe(data=>
      {
        console.log("-----------------------------------------");
       this.CPlayersDetails=data.data;
       console.log(this.CPlayersDetails);
       
      });
      
  }
  onNoClick(): void {
    this.dialogRef.close();
  }
  viewStatistics()
   {
 console.log("view statistics is enabled");
 console.log(this.playerId);
this.id=this.playerId;
   
   //this.router.navigate(['/PlayerDetails',this.CPlayersList[index].id]);
   const dialogRef = this.dialog.open(PlayerStatisticsComponent, {
    height: '75vh',
    width: '60vw',
     disableClose:true,
    data:this.id
   })

   }

}
