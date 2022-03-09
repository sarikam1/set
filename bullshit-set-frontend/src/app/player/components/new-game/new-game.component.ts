import { Component, OnInit } from '@angular/core';
import {GameComponent} from "../../../Game/game.component";
import {Game} from "../../../shared/models/game";
import {SharedService} from "../../../shared/shared.service";

@Component({
  providers: [GameComponent],
  selector: 'app-new-game',
  templateUrl: './new-game.component.html',
  styleUrls: ['./new-game.component.css']
})
export class NewGameComponent implements OnInit {

  constructor(public sharedService: SharedService) {
  }

  ngOnInit(): void {
  }

  public newGame(): void {
    this.sharedService.createGame();
  }


}
