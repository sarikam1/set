import {Player} from "./player";
import {Game} from "./game";

export interface Participant {
    id: number;
    player: Player;
    game: Game;
    Score: number;

}
