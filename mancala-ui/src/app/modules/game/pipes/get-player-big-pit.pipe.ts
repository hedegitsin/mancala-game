import { Pipe, PipeTransform } from '@angular/core';
import {GameModel} from "../../../core/models/game.model";
import {PitModel} from "../../../core/models/pit.model";

@Pipe({
  name: 'getPlayerBigPit'
})
export class GetPlayerBigPitPipe implements PipeTransform {

  transform(value: GameModel, playerIndex: number): PitModel {
    return value.players[playerIndex].bigPit;
  }

}
