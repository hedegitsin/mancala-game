import {Component, inject, Input} from '@angular/core';
import {GameModel} from "../../../../core/models/game.model";
import {PlayerModel} from "../../../../core/models/player.model";
import {GameApiService} from "../../services/game-api.service";
import {PitModel} from "../../../../core/models/pit.model";

@Component({
  selector: 'app-game-in-progress',
  templateUrl: './game-in-progress.component.html',
  styleUrl: './game-in-progress.component.scss'
})
export class GameInProgressComponent {

  private gameService = inject(GameApiService);
  private _game!: GameModel;
  @Input()
  set game(val: GameModel) {
    this._game = val;
    this._playerOne = val.players[0];
    this._playerTwo = val.players[1];
  }

  private _playerOne!: PlayerModel;
  private _playerTwo!: PlayerModel;

  makeMove(pitIndex: number) {
    this.gameService.makeMove(this.game.id, pitIndex)
      .subscribe((game) => this.game = game);
  }

  get game(): GameModel {
    return this._game;
  }

  get playerOne(): PlayerModel {
    return this._playerOne;
  }

  get playerTwo(): PlayerModel {
    return this._playerTwo;
  }
}
