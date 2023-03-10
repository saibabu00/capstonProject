import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlayerlistComponent } from 'src/app/Player/playerlist/playerlist.component';
import { PlayerDetailsComponent } from 'src/app/Player/player-details/player-details.component';
import { AppComponent } from './app.component';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';
import { NavbarComponent } from './Player/navbar/navbar.component';
import { AuthGuard } from './auth.guard';
import { DashboardComponent } from './Player/dashboard/dashboard.component';
import { UpdateComponent } from './authentication/update/update.component';
import { SearchComponent } from './Player/search/search.component';
import { FavouritesComponent } from './Player/favourites/favourites.component';
import { RecommendComponent } from './Player/recommend/recommend.component';
import { BowlerlistComponent } from './Player/bowlerlist/bowlerlist.component';


const routes: Routes = [
  {
    path:'',
    component:LoginComponent
  },
  {
    path:'search',
    component:SearchComponent
  },
  {
    path:'login',
    component:LoginComponent
  },
  {
    path:'register',
    component:RegisterComponent
  },
  {
    path:'Playerlist',
    component:PlayerlistComponent,
  canActivate:[AuthGuard]
  },
  {
    path:'PlayerDetails/:id',
    component: PlayerDetailsComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'UserProfile',
    component:DashboardComponent
  },
  {
    path:'update',
    component:UpdateComponent
  },
  {
    path:'favourites',
    component:FavouritesComponent
  },
  {
    path:'recommend',
    component:RecommendComponent
  },
  {
  path:'bowlerlist',
  component:BowlerlistComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents=[PlayerDetailsComponent,PlayerDetailsComponent]
