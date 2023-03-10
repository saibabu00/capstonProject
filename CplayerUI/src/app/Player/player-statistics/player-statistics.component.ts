// import { Component, OnInit ,Inject} from '@angular/core';
// import { ActivatedRoute } from '@angular/router';
// import {CplayerServiceService} from 'src/app/services/cplayer-service.service';
// import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
// import { MatTableDataSource } from '@angular/material/table';
// @Component({
//   selector: 'app-player-statistics',
//   templateUrl: './player-statistics.component.html',
//   styleUrls: ['./player-statistics.component.css']
// })
// export class PlayerStatisticsComponent implements OnInit {
//   playerId:string="";
//   CPlayersStatistics:any=[];
//   CPlayerValue:string[]=[];
//   public Batttingmatchtype:String[]=[];
//   public Battingvalue:string[][]=[];
//   public Bavalue:string[]=[]
//   public Bowlingmatchtype:string[]=[];
//   public Bowlingvalue:string[][]=[];
//   public Bovalue:string[]=[]
  
//   constructor(private CPlayerListService: CplayerServiceService, private route:ActivatedRoute, 
//     public dialogRef: MatDialogRef<PlayerStatisticsComponent>,
//     @Inject(MAT_DIALOG_DATA) public id:any ) {
//       this. playerId= this.id;
//      }


//   ngOnInit(): void {
//     this.CPlayerListService. getPlayerDetails(this.playerId).subscribe(data=>
//       {
//         console.log("-----------------------------------------");
//        this.CPlayersStatistics=data.data.stats;
//        console.log(this.CPlayersStatistics);
//       for(let item of this.CPlayersStatistics)
//        {
//      if(item.fn=="batting")
//      {
//        if(this.Batttingmatchtype.indexOf(item.matchtype) == -1)
//        {
//         this.Batttingmatchtype.push(item.matchtype)
//        }

//       this.Bavalue.push(item.value);
//      }
//      else if(item.fn=="bowling")
//      {
//       if(this.Bowlingmatchtype.indexOf(item.matchtype) == -1)
//       {
//        this.Bowlingmatchtype.push(item.matchtype)
//       }
//    this.Bovalue.push(item.value);
//      }
    

//  }

//  var c=0
//  for(var i=0;i< this.Batttingmatchtype.length;i++)
//  {
//   this.Battingvalue.push([])
//    for(var j=0;j<13;j++)
//    {
//      this.Battingvalue[i].push(this.Bavalue[c])
//      c=c+1;
//    }
//  }
//  var cz=0
//  for(var i=0;i< this.Bowlingmatchtype.length;i++)
//  {
//   this.Bowlingvalue.push([])
//    for(var j=0;j<12;j++)
//    {
//      this.Bowlingvalue[i].push(this.Bovalue[cz])
//      cz=cz+1;
//    }
//  }
         

// });
    
//     }
   

//     numSequence(n: number): Array<number> {
     
//       return Array(n);
//     }
//     onNoClick(): void {
//       this.dialogRef.close();
//     }

// }



import { Component, OnInit ,Inject} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {CplayerServiceService} from 'src/app/services/cplayer-service.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
@Component({
  selector: 'app-player-statistics',
  templateUrl: './player-statistics.component.html',
  styleUrls: ['./player-statistics.component.css']
})
export class PlayerStatisticsComponent implements OnInit {
  playerId:string="";
  CPlayersStatistics:any=[];
  CPlayerValue:string[]=[];
  public Batttingmatchtype:String[]=[];
  public Battingvalue:string[][]=[];
  public Bavalue:string[]=[]
  public Bowlingmatchtype:string[]=[];
  public Bowlingvalue:string[][]=[];
  public Bovalue:string[]=[]

  constructor(private CPlayerListService: CplayerServiceService, private route:ActivatedRoute,
    public dialogRef: MatDialogRef<PlayerStatisticsComponent>,
    @Inject(MAT_DIALOG_DATA) public id:any ) {
      this. playerId= this.id;
     }


  ngOnInit(): void {
    this.CPlayerListService. getPlayerDetails(this.playerId).subscribe(data=>
      {
        console.log("-----------------------------------------");
       this.CPlayersStatistics=data.data.stats;
       console.log(this.CPlayersStatistics);
       if (this.CPlayersStatistics == null) {
         return;
       }
      for(let item of this.CPlayersStatistics) {
         if(item.fn=="batting")
         {
           if(this.Batttingmatchtype.indexOf(item.matchtype) == -1)
           {
            this.Batttingmatchtype.push(item.matchtype)
           }

          this.Bavalue.push(item.value);
         }
         else if(item.fn=="bowling")
         {
          if(this.Bowlingmatchtype.indexOf(item.matchtype) == -1)
          {
           this.Bowlingmatchtype.push(item.matchtype)
          }
       this.Bovalue.push(item.value);
         }
      }

 var c=0
 for(var i=0;i< this.Batttingmatchtype.length;i++)
 {
  this.Battingvalue.push([])
   for(var j=0;j<13;j++)
   {
     this.Battingvalue[i].push(this.Bavalue[c])
     c=c+1;
   }
 }
 var cz=0
 for(var i=0;i< this.Bowlingmatchtype.length;i++)
 {
  this.Bowlingvalue.push([])
   for(var j=0;j<12;j++)
   {
     this.Bowlingvalue[i].push(this.Bovalue[cz])
     cz=cz+1;
   }
 }


});

    }


    numSequence(n: number): Array<number> {

      return Array(n);
    }
    onNoClick(): void {
      this.dialogRef.close();
    }

}
