import {Component, OnInit, Output} from '@angular/core';
import {filter, Observable, of, share, Subject, Subscription, switchMap, takeUntil, timer} from "rxjs";
import {GameService} from "../../../Game/game.service";
import {catchError} from "rxjs/operators";
import {Participant} from "../../../shared/models/participant";
import {SharedService} from "../../../shared/shared.service";
import {waitingRoomDTO} from "../../../shared/models/waitingRoomDTO";
import {gameStatus} from "../../../shared/enums/gameStatus";

@Component({
  selector: 'app-joined-so-far',
  templateUrl: './joined-so-far.component.html',
  styleUrls: ['./joined-so-far.component.css']
})
export class JoinedSoFarComponent implements OnInit {

  @Output() waitingParticipants: undefined | Array<Participant> = new Array<Participant>();
  @Output() currentGameId: string;
  private stopWaiting = new Subject<void>();
  subscription: Subscription | undefined;
  public creatorUsername: String = "someone";

  constructor(public gameService: GameService, public sharedService: SharedService) {
    this.currentGameId = sessionStorage.getItem('currentGameId') || "-1";
  }

  //https://stackoverflow.com/questions/66217247/make-http-request-call-every-x-minute-in-angular-9
  //https://blog.angulartraining.com/how-to-do-polling-with-rxjs-and-angular-50d635574965
  ngOnInit(): void {
    this.gameService.getCreatorUsername().pipe().subscribe(res => {
      console.log(res);
      this.creatorUsername = res.toString();
    });

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
        console.log(data);
        this.waitingParticipants = data?.participants;
        if(data?.gameStatus == gameStatus.IN_PROGRESS) {
          this.startGame();
        }
      });

  }

  isCurrentUserCreator(): boolean {
    return this.creatorUsername == (sessionStorage.getItem('currentUser'));
  }

  getWaitingParticipants(): Observable<waitingRoomDTO> {
    return this.gameService.getWaitingParticipantsInGame();
  }

  // getGameReady(): Observable<Array<Participant>> {
  //   return this.gameService.getWaitingParticipantsInGame();
  // }

  startGame() {
    this.gameService.startGame();
  }

  hostStartGame() {
    this.gameService.changeStatusToInProgress().subscribe(res => {
      console.log("done changing");
      this.gameService.startGame();
    })
  }


  ngOnDestroy() {
    this.stopWaiting.next();
  }

}




