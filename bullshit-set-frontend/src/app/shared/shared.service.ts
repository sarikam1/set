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
        console.log("current game id is " + this.currentGame.id);
        ///' + this.currentGame.id]
        this.router.navigate(['/waiting']).then(() => {
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
