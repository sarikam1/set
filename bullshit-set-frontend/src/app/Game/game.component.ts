import { Component } from '@angular/core';
import {Game} from "../shared/models/game";
import {GameService} from "./game.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Router, RouterModule, Routes} from '@angular/router';



@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
})
export class GameComponent {

}
