import {Component, Input, OnInit, Output} from '@angular/core';
import {filter, interval, Observable, of, share, Subject, Subscription, switchMap, takeUntil, timer} from "rxjs";
import {GameService} from "../../../Game/game.service";
import {Game} from "../../../shared/models/game";
import {catchError} from "rxjs/operators";
import {Participant} from "../../../shared/models/participant";
import {SharedService} from "../../../shared/shared.service";

@Component({
  selector: 'app-joined-so-far',
  templateUrl: './joined-so-far.component.html',
  styleUrls: ['./joined-so-far.component.css']
})
export class JoinedSoFarComponent implements OnInit {

  @Output() waitingParticipants: undefined | Array<Participant> = new Array<Participant>();
  @Output() currentGame: undefined | Game;
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
          return this.getWaitingParticipants()
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
        this.waitingParticipants = data;
        console.log(this.waitingParticipants);
      });
  }

  getWaitingParticipants(): Observable<Array<Participant>> {
    return this.gameService.getWaitingParticipantsInGame();
  }


  ngOnDestroy() {
    this.stopWaiting.next();
  }

}




