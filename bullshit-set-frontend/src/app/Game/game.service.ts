import { Observable, throwError} from "rxjs";
import { catchError, retry } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Game} from "../shared/models/game";
import {environment} from "../../environments/environment";


@Injectable(
  {
    providedIn: 'root',
  }

)
export class GameService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient){}

  public createGame(): Observable<Game> {
    return this.http.get<Game>(`${this.apiServerUrl}/api/game/create`);
  }

  public getWaitingGames(): Observable<Array<Game>> {
    let currentUser = sessionStorage.getItem('currentUser');
    let params = new HttpParams().set('username', currentUser!); //! throws error when currentUser is undefined
    return this.http.get<Array<Game>>(`${this.apiServerUrl}/api/game/waiting`, {params:params});
  }

}
