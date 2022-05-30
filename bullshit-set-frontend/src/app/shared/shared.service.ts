import {Component, Injectable} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {Router, RouterModule, Routes} from '@angular/router';
import {Game} from "./models/game";
import {GameService} from "../Game/game.service";
import {Participant} from "./models/participant";




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
        )
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message)
      }
    })
  }

  public joinGame(gameId: number): void {
    this.gameService.joinGame(gameId).subscribe({
      next: (response: Participant) => {
        sessionStorage.setItem("currentParticipantId", String(response.id));
        sessionStorage.setItem("currentGameId", String(gameId));
        console.log(sessionStorage.getItem("currentParticipantId"));
        this.router.navigate(['app/waiting']).then(
          () => {/*handle after nav*/
          }
        )
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message)
      }
    })
  }


}




