import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
// import { MatTableDataSource } from '@angular/material/table/table-data-source';
import { Router } from '@angular/router';
import { CricPlayer } from 'src/app/cric-player';
import { CplayerServiceService } from 'src/app/services/cplayer-service.service';

@Component({
  selector: 'app-bowlerlist',
  templateUrl: './bowlerlist.component.html',
  styleUrls: ['./bowlerlist.component.css']
})
export class BowlerlistComponent implements OnInit {

  columnsToDisplay: string[] = ['bowlerName', 'matches', 'span', 'wickets', 'fiveWktHaul', 'actions'];

  public bowlersList: any = []
  public cplayer: CricPlayer = {} as CricPlayer;
  // message:string="hellolo"
  dataSource: any;
  showSpinner: boolean = true;
  public check: String = '1';
  public id: any;
  statusCode!: number;
  errorStatus: string = "";
  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;
  @ViewChild(MatSort) sort: MatSort | undefined;
  constructor(private CPlayerListService: CplayerServiceService, private router: Router, public dialog: MatDialog, private snackbar: MatSnackBar) { }

  ngOnInit(): void {
    this.CPlayerListService.getAllBowlers().subscribe
      (data => {
        // this.bowlersList  = data;
        for (let i = 0; i < data.length; i++) {
          this.bowlersList.push(data[i]);
        }
        this.getList();
        console.log(data);
      },
        error => {
          console.log(error);
        }

      );
  }

  getList() {
    this.showSpinner = false;
    this.check = '0';
    //console.log("the total players list is")
    //  console.log(this.CPlayersList.length);
    this.dataSource = new MatTableDataSource<any>(this.bowlersList);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }


}
