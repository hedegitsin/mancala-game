package com.harun.mancala.dto;

import com.harun.mancala.domain.Game;

public class PlayResponseDto {

    private final GameResponseDto game;


    public PlayResponseDto(Game game) {
        this.game = new GameResponseDto(game);

    }

    public GameResponseDto getGame() {
        return game;
    }
}
