import {Player} from "./player";
import {Game} from "./game";
import {gameStatus} from "../enums/gameStatus";
import {Participant} from "./participant";

export interface waitingRoomDTO {
  gameStatus: gameStatus;
  participants: Array<Participant>;
}
