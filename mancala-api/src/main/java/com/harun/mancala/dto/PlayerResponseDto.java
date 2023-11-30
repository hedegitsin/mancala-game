package com.harun.mancala.dto;

import com.harun.mancala.domain.Player;

import java.util.List;
import java.util.UUID;

public class PlayerResponseDto {

    private final UUID id;
    private final String name;
    private final List<PitResponseDto> pits;
    private final PitResponseDto bigPit;

    public PlayerResponseDto(final Player player) {
        this.id = player.id;
        this.name = player.name;
        this.pits = PitResponseDto.from(player.getPits());
        this.bigPit = new PitResponseDto(player.getBigPit());
    }

    public static List<PlayerResponseDto> from(final List<Player> players) {
        return players.stream().map(PlayerResponseDto::new).toList();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<PitResponseDto> getPits() {
        return pits;
    }

    public PitResponseDto getBigPit() {
        return bigPit;
    }
}
