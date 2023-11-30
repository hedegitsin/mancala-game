import {PitModel} from "./pit.model";

export interface PlayerModel{
    id: string;
    name: string;
    pits: PitModel[];
    bigPit: PitModel;
}
