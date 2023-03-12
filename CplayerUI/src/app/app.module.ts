import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './Player/dashboard/dashboard.component';
import { NavbarComponent } from './Player/navbar/navbar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PlayerlistComponent } from './Player/playerlist/playerlist.component';
import { PlayerDetailsComponent } from './Player/player-details/player-details.component';
import {MatButtonModule} from '@angular/material/button';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {MatCardModule} from '@angular/material/card';
import {MatTableModule} from '@angular/material/table';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import {MatDialogModule} from '@angular/material/dialog';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialsModule } from './materials/materials.module';
import {MatFormFieldModule} from '@angular/material/form-field';
import { AuthGuard } from './auth.guard';
import { AuthServiceService } from './services/auth-service.service';
import { AuthInterceptor } from './services/auth.interceptor';
import { UpdateComponent } from './authentication/update/update.component';
import { SearchComponent } from './Player/search/search.component';
// import {MaterialsModule} from
import { FavouritesComponent } from './Player/favourites/favourites.component';
import { PlayerStatisticsComponent } from './Player/player-statistics/player-statistics.component';
import { MatIconModule } from '@angular/material/icon'
// import {MaterialsModule} from
import {RecommendComponent} from "./Player/recommend/recommend.component";
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { BowlerlistComponent } from './Player/bowlerlist/bowlerlist.component';
import { AdminComponent } from './Player/admin/admin.component';
import { AddplayerComponent } from './Player/addplayer/addplayer.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    NavbarComponent,
    PlayerlistComponent,
    PlayerDetailsComponent,
    LoginComponent,
    RegisterComponent,

    UpdateComponent,
    SearchComponent,

    FavouritesComponent,
    PlayerStatisticsComponent,

    UpdateComponent,
    SearchComponent,
    RecommendComponent,
    BowlerlistComponent,
    AdminComponent,
    AddplayerComponent



  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    HttpClientModule,
    MatCardModule,
    MatTableModule,
    MatProgressSpinnerModule,
    MatPaginatorModule ,
    MatSortModule,
    MatDialogModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialsModule,
    MatSnackBarModule,
    MatFormFieldModule,
    MatIconModule,
    MatCardModule
  ],
  providers:[AuthServiceService],
  //  [[{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}]],
  bootstrap: [AppComponent]
})
export class AppModule { }
