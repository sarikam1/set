import { Observable, throwError} from "rxjs";
import { catchError, retry } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from '@angular/common/http';
import {Game} from "../shared/models/game";
import {environment} from "../../environments/environment";
import {Participant} from "../shared/models/participant";
import {Router} from "@angular/router";
import {waitingRoomDTO} from "../shared/models/waitingRoomDTO";


@Injectable(
  {
    providedIn: 'root',
  }

)
export class GameService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient, private router: Router){}

  public createGame(): Observable<Game> {
    return this.http.get<Game>(`${this.apiServerUrl}/api/game/create`);
  }

  public getWaitingGames(): Observable<Array<Game>> {
    let currentUser = sessionStorage.getItem('currentUser');
    let params = new HttpParams().set('username', currentUser!); //! throws error when currentUser is undefined
    return this.http.get<Array<Game>>(`${this.apiServerUrl}/api/game/waiting`, {params:params});
  }

  public getWaitingParticipantsInGame(): Observable<waitingRoomDTO> {
    let currentGameId = sessionStorage.getItem('currentGameId');
    let params = new HttpParams().set('gameId', currentGameId!);
    return this.http.get<waitingRoomDTO>(`${this.apiServerUrl}/api/game/get-waiting-info`, {params:params});

  }
  public canCreateGame(): Observable<boolean> {
    //default response type is json, specify string (text)
    return this.http.get<boolean>(`${this.apiServerUrl}/api/game/can-create-game`);
  }



  public getCreatorUsername(): Observable<String> {
    let currentGameId = sessionStorage.getItem('currentGameId');
    let params = new HttpParams().set('gameId', currentGameId!);
    //default response type is json, specify string (text)
    return this.http.get(`${this.apiServerUrl}/api/game/get-creator-username`, {params:params, responseType: 'text'});

  }

  public joinGame(gameId: number): Observable<Participant> {
    let currentUser = sessionStorage.getItem('currentUser');
    let params = new HttpParams().set('username', currentUser!);
    //params is immutable!
    params = params.append('gameId', gameId);
    return this.http.get<Participant>(`${this.apiServerUrl}/api/game/join-game`, {params:params});
  }

  public startGame() {
    let currentGameId = sessionStorage.getItem('currentGameId');
    this.router.navigate(['app/game/play/' + currentGameId]).then(r =>
    {
    });
  }

  public changeStatusToInProgress(): Observable<void>  {
    let currentGameId = sessionStorage.getItem('currentGameId');
    let params = new HttpParams().set('gameId', currentGameId!);
    //default response type is json, specify string (text)
    console.log("changing game status!");
    return this.http.get<void>(`${this.apiServerUrl}/api/game/start-game`, {params:params});

  }



}
