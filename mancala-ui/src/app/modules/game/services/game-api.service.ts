import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {GameModel} from "../../../core/models/game.model";
import {GameResponseModel} from "../../../core/models/game-response.model";
import {map} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class GameApiService {

  private http = inject(HttpClient);


  startGame(playerNames: { playerOne: string | null; playerTwo: string | null; }) {
    return this.http.post<GameResponseModel>(environment.api.game.start, {playerNames}).pipe(
      map((response: GameResponseModel) => response.game)
    );
  }

  makeMove(gameId: string, pitIndex: number) {
    return this.http.post<GameResponseModel>(environment.api.game.play, {gameId, pitIndex}).pipe(
      map((response: GameResponseModel) => response.game)
    );
  }
}
