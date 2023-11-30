import {Component, inject, OnInit} from '@angular/core';
import {GameApiService} from "../../services/game-api.service";
import {GameModel} from "../../../../core/models/game.model";
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-game',
  templateUrl: './game.page.html',
  styleUrl: './game.page.scss'
})
export class GamePage {
  private gameService = inject(GameApiService);
  private formBuilder = inject(FormBuilder);

  game?: GameModel;

  gameForm = this.formBuilder.group({
      playerOne: this.formBuilder.control('', {validators: [Validators.required]}),
      playerTwo: this.formBuilder.control('', {validators: [Validators.required]})
    }
  )

  startGame() {
    console.log('game starting');
    this.gameForm.updateValueAndValidity();
    if (this.gameForm.invalid) {
      console.log(this.gameForm.errors);
      return;
    }
    this.gameService.startGame(this.gameForm.getRawValue()).subscribe((game) => this.game = game);
  }

}
