import {Player} from "./player";
import {Deck} from "./deck";
import {Participant} from "./participant";

export interface Game {
  id: number;
  createdBy: Player;
  createdTime: string;
  endTime: string;
  numPlayers: number;
  gameStatus: string;
  deck: Deck;
  participants: Participant[];


}
