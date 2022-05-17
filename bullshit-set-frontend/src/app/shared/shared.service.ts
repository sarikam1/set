import {Component, Injectable} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {Router, RouterModule, Routes} from '@angular/router';
import {Game} from "./models/game";
import {GameService} from "../Game/game.service";




@Injectable({
  providedIn: 'root',
  }
)
export class SharedService {
  public currentGame?: Game;

  constructor(private gameService: GameService, private router: Router) {
  }

  public createGame(): void {
    this.gameService.createGame().subscribe({
      next: (response: Game) => {
        this.currentGame = response;
        sessionStorage.setItem("currentGameId", String(response.id));
        console.log(this.currentGame);
        console.log(sessionStorage.getItem("currentGameId"));
        this.router.navigate(['app/waiting']).then(() => {
            //handle after navigation logic
          }
        );
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message)
      }
    });
  }

}
