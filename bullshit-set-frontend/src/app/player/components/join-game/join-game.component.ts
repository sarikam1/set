import {Component, Input, OnInit, Output} from '@angular/core';
import {filter, interval, Observable, of, share, Subject, Subscription, switchMap, takeUntil, timer} from "rxjs";
import {GameService} from "../../../Game/game.service";
import {Game} from "../../../shared/models/game";
import {catchError} from "rxjs/operators";
import {SharedService} from "../../../shared/shared.service";

@Component({
  selector: 'app-join-game',
  templateUrl: './join-game.component.html',
  styleUrls: ['./join-game.component.css']
})
export class JoinGameComponent implements OnInit {

  @Output() waitingGames: undefined | Array<Game> = new Array<Game>();
  private stopWaiting = new Subject<void>();
  subscription: Subscription | undefined;

  constructor(private gameService: GameService, private sharedService: SharedService) {
  }

  //https://stackoverflow.com/questions/66217247/make-http-request-call-every-x-minute-in-angular-9
  //https://blog.angulartraining.com/how-to-do-polling-with-rxjs-and-angular-50d635574965
  ngOnInit(): void {
    this.subscription = timer(0, 5000)
      .pipe( //to combine multiple functions into one function
        switchMap(() => { //switchMap turns an observable into another observable: number -> http request
          return this.getWaitingGames()
            .pipe(catchError(err => {
              // Handle errors
              console.error(err);
              return of(undefined);
            }));
        }),
        filter(data => data !== undefined),
        share(), //so that all subscribers share observable
        takeUntil(this.stopWaiting)
      )
      .subscribe(data => {
        this.waitingGames = data;
        console.log(this.waitingGames);
      });
  }

  getWaitingGames(): Observable<Array<Game>> {
    return this.gameService.getWaitingGames();
  }

  public joinGame(gameId: number): void {
    this.sharedService.joinGame(gameId);
  }

  ngOnDestroy() {
    this.stopWaiting.next();
  }

  columnDefs = [{ field: "make" }, { field: "model" }, { field: "price" }];

  rowData = [
    { make: "Toyota", model: "Celica", price: 35000 },
    { make: "Ford", model: "Mondeo", price: 32000 },
    { make: "Porsche", model: "Boxter", price: 72000 }
  ];




}
