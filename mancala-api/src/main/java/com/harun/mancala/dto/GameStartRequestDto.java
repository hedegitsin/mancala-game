package com.harun.mancala.dto;

public record GameStartRequestDto(PlayerNameRequestDto playerNames) {
    public PlayerNameRequestDto getPlayerNames() {
        return playerNames;
    }
}
