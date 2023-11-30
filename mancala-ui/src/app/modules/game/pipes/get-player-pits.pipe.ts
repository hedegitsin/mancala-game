import { Pipe, PipeTransform } from '@angular/core';
import {GameModel} from "../../../core/models/game.model";
import {PitModel} from "../../../core/models/pit.model";

@Pipe({
  name: 'getPlayerPits'
})
export class GetPlayerPitsPipe implements PipeTransform {

  transform(value: GameModel, playerIndex: number, reverse?: 'reverse'): PitModel[] {
    const pits = value.players[playerIndex].pits;
    return reverse ? pits.slice().reverse() : pits;
  }

}
