package com.harun.mancala.dto;

import com.harun.mancala.domain.Pit;
import com.harun.mancala.domain.PitType;

import java.util.List;

public class PitResponseDto {
    private final int stones;

    private final PitType type;
    public PitResponseDto(final Pit pit) {
        this.stones = pit.getCurrentNumberOfStones();
        this.type = pit.getType();
    }

    public static List<PitResponseDto> from(List<Pit> pits) {
        return pits.stream().map(PitResponseDto::new).toList();
    }

    public int getStones() {
        return stones;
    }

    public PitType getType() {
        return type;
    }
}
