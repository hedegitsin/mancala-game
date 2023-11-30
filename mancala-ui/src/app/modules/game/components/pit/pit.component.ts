import {Component, EventEmitter, Input, Output} from '@angular/core';
import {PitModel} from "../../../../core/models/pit.model";
import {PitType} from '../../../../core/models/pit-type.enum';

@Component({
  selector: 'app-pit',
  templateUrl: './pit.component.html',
  styleUrl: './pit.component.scss'
})
export class PitComponent {
  PitType = PitType;

  @Input()
  pit!: PitModel;

  @Input()
  index!: number;

  @Input()
  isCurrentPlayer: boolean = false;

  @Output()
  onPitClicked = new EventEmitter<number>();

  makeMove() {
    if (this.pit.type !== PitType.BIG && this.isCurrentPlayer) {
      this.onPitClicked.emit(this.index);
    }
  }
}
