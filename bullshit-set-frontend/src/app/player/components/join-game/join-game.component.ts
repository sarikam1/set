import {Component, Input, OnInit, Output} from '@angular/core';
import {filter, interval, Observable, of, Subscription, switchMap, timer} from "rxjs";
import {GameService} from "../../../Game/game.service";
import {Game} from "../../../shared/models/game";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-join-game',
  templateUrl: './join-game.component.html',
  styleUrls: ['./join-game.component.css']
})
export class JoinGameComponent implements OnInit {

  @Output() waitingGames: any;
  subscription: Subscription | undefined;

  constructor(private gameService: GameService) {
  }

  ngOnInit(): void {
    this.subscription = timer(0, 10000)
      .pipe(
        switchMap(() => {
          return this.getWaitingGames()
            .pipe(catchError(err => {
              // Handle errors
              console.error(err);
              return of(undefined);
            }));
        }),
        filter(data => data !== undefined)
      )
      .subscribe(data => {
        this.waitingGames = data;
        console.log(this.waitingGames);
      });
  }

  getWaitingGames(): Observable<Array<Game>> {
    return this.gameService.getWaitingGames();
  }

  columnDefs = [{ field: "make" }, { field: "model" }, { field: "price" }];

  rowData = [
    { make: "Toyota", model: "Celica", price: 35000 },
    { make: "Ford", model: "Mondeo", price: 32000 },
    { make: "Porsche", model: "Boxter", price: 72000 }
  ];




}
