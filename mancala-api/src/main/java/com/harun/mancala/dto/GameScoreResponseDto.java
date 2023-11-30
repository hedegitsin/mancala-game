package com.harun.mancala.dto;

import com.harun.mancala.domain.GameScore;

import java.util.List;
import java.util.UUID;

public record GameScoreResponseDto(UUID player, int score) {

    public static List<GameScoreResponseDto> from(List<GameScore> scores) {
        return scores.stream()
                .map(score -> new GameScoreResponseDto(score.getPlayer().id, score.getScore()))
                .toList();
    }
}
