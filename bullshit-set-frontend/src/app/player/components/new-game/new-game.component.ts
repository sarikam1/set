import { Component, OnInit } from '@angular/core';
import {Game} from "../../../shared/models/game";
import {SharedService} from "../../../shared/shared.service";
import {GameService} from "../../../Game/game.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-new-game',
  templateUrl: './new-game.component.html',
  styleUrls: ['./new-game.component.css']
})
export class NewGameComponent {

  public canCreate: boolean | undefined;

  constructor(public sharedService: SharedService, public gameService: GameService) {
    gameService.canCreateGame().subscribe(data => {
      this.canCreate = data;
      console.log("can create game is " + this.canCreate);
    })
  }

  public newGame(): void {
    this.sharedService.createGame();
  }




}
