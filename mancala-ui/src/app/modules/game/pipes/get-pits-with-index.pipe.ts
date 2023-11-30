import {Pipe, PipeTransform} from '@angular/core';
import {PitModel} from "../../../core/models/pit.model";

@Pipe({
  name: 'getPitsWithIndex'
})
export class GetPitsWithIndexPipe implements PipeTransform {

  transform(pits: PitModel[], reverse?: 'reverse'): Array<{ index: number, pit: PitModel }> {
    const orderedPits = pits.map((pit, index) => ({index, pit}));
    return reverse ? orderedPits.reverse() : orderedPits;
  }

}
