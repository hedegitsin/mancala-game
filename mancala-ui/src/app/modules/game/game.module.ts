import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {GameRoutingModule} from './game-routing.module';
import {GamePage} from './pages/game/game.page';
import {PitComponent} from './components/pit/pit.component';
import {GetPlayerPitsPipe} from './pipes/get-player-pits.pipe';
import {GameInProgressComponent} from './components/game-in-progress/game-in-progress.component';
import {GetPlayerBigPitPipe} from './pipes/get-player-big-pit.pipe';
import {GetCurrentPlayerNamePipe} from './pipes/get-current-player-name.pipe';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { GetPitsWithIndexPipe } from './pipes/get-pits-with-index.pipe';


@NgModule({
  declarations: [
    GamePage,
    PitComponent,
    GetPlayerPitsPipe,
    GameInProgressComponent,
    GetPlayerBigPitPipe,
    GetCurrentPlayerNamePipe,
    GetPitsWithIndexPipe,

  ],
  imports: [
    CommonModule,
    GameRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class GameModule {
}
