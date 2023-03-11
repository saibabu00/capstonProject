import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http'
import { Observable } from 'rxjs';
import { CricPlayer } from '../cric-player';
export const USER_NAME = "username";


@Injectable({
  providedIn: 'root'
})
export class CplayerServiceService {

  cplayerApi: String;
  apiKey: String;
  playerInfo:string;
  bowlerInfo:String;
  favouritEndPoint: string;
  searchInfo:string;
  recommendEndPoint: string;
  username: string="";
  favend:String="";


  constructor(private http: HttpClient) {
    this.cplayerApi = 'https://api.cricapi.com/v1/players?';
  //this.apiKey = 'apikey=bb1861a3-6be3-4894-b8ac-b83b9183899c';
  this.apiKey = 'apikey=9af00c14-ae85-4d46-8e25-2ec5c7d6d689';
   //this.apiKey = 'apikey=937a2e45-b8d1-41f9-acbb-b288f52eae57';
    //this.apiKey = 'apikey=290b21e9-ab64-45cd-a7dd-a33e9dde791c';
    this.playerInfo='https://api.cricapi.com/v1/players_info?';
    this.searchInfo = "http://localhost:8091/player/searchPlayer/";
    this.favouritEndPoint= 'http://localhost:8083/api/v1/favoriteservice';
    this.favend = "http://localhost:8083/api/v1/favoriteservice/user/Deleteplayer/"

    this.recommendEndPoint= 'http://localhost:8082/api/v1/player/recommend/10';
    this.bowlerInfo = "http://localhost:8091/player/searchBowler/";

}
getAllPlayers(){
  return this.http.get<any>("http://localhost:8091/player/getPlayers");
}
getAllBowlers(){
  return this.http.get<any>("http://localhost:8091/player/getAllBowlers");
}

getPlayerDetails(pid:String):Observable<any>
{

  this.apiKey = 'apikey=9af00c14-ae85-4d46-8e25-2ec5c7d6d689&id='+pid;
  const url = `${this.playerInfo}${this.apiKey}`;
  return this.http.get(url);
}
addPlayerToFavoriteList(cPlayer:CricPlayer)
{
  console.log("called");
  this.username =  sessionStorage.getItem(USER_NAME) || '{}';
 const url = this.favouritEndPoint + "/user/" + this.username + "/player";
 console.log("sent.........")
 console.log(url);
 console.log(cPlayer)
 console.log(cPlayer.playerName)
return this.http.post(url, cPlayer)
}
getFavoriteList(): Observable<CricPlayer[]> {
this.username = sessionStorage.getItem(USER_NAME) || '{}';
const url = this.favouritEndPoint + "/user/" + this.username + "/getplayerList";
return this.http.get<CricPlayer[]>(url);

}
deleteFromFavoriteList(cPlayer:CricPlayer)
{
this.username = sessionStorage.getItem(USER_NAME) || '{}';
const url = this.favouritEndPoint + "/user/" + this.username + "/Deleteplayer/" + `${cPlayer.playerName}`;
// const url = `${this.favend}${this.username}${"/"}${cPlayer.playerName}`
return this.http.delete(url);
}

  getSearchPlayer(searchKey:String){
  const url = `${this.searchInfo}${searchKey}`;
  return this.http.get(url);
  }
  searchBowler(bowlerName:String){
    const url = `${this.bowlerInfo}${bowlerName}`;
    return this.http.get(url);
  }

  getRecommendPlayerList(){
    const headerDict = {
      'Accept': 'application/json, text/plain, */*',
      'Access-Control-Allow-Origin':'*',
      'Content-Type':'application/json',
    }
    const headers = new HttpHeaders(headerDict);
    return this.http.get(`${this.recommendEndPoint}`, {headers});
  }



}
