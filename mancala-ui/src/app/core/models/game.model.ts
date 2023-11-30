import {PlayerModel} from "./player.model";
import {GameScoreModel} from "./game-score.model";
import {GameStatus} from "./game-status.enum";

export interface GameModel {
  id: string;
  status: GameStatus;
  currentPlayer: string;
  players: PlayerModel[];
  winner: string;
  scores: GameScoreModel[]
}
