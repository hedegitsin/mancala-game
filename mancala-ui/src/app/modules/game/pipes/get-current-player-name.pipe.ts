import { Pipe, PipeTransform } from '@angular/core';
import {GameModel} from "../../../core/models/game.model";

@Pipe({
  name: 'getCurrentPlayerName'
})
export class GetCurrentPlayerNamePipe implements PipeTransform {

  transform(value: GameModel): string {
    return value.players.find(player => player.id === value.currentPlayer)?.name || '';
  }

}
