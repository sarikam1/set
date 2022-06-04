import {Player} from "./player";
import {Deck} from "./deck";
import {Participant} from "./participant";
import {gameStatus} from "../enums/gameStatus";

export interface Game {
  id: number;
  createdBy: Player;
  createdTime: string;
  endTime: string;
  numPlayers: number;
  gameStatus: gameStatus;
  deck: Deck;
  participants: Participant[];


}
