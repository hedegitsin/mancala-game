package com.harun.mancala.dto;

import com.harun.mancala.domain.Game;
import com.harun.mancala.domain.GameScore;
import com.harun.mancala.domain.GameStatus;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class GameResponseDto {

    private final UUID id;
    private final UUID currentPlayer;
    private final GameStatus status;
    private final List<GameScoreResponseDto> scores;
    private final String winner;

    private final List<PlayerResponseDto> players;

    public GameResponseDto(final Game game) {
        this.id = game.getId();
        this.status = getStatus(game);
        this.currentPlayer = game.getCurrentPlayer().id;
        final List<GameScore> gameScores = game.getScores();
        this.scores = GameScoreResponseDto.from(gameScores);
        this.winner = setWinner(game, gameScores);
        this.players = PlayerResponseDto.from(game.getPlayersAsList());

    }


    private GameStatus getStatus(Game game) {
        return game.isGameOver() ? GameStatus.FINISHED : GameStatus.IN_PROGRESS;
    }

    private String setWinner(Game game, List<GameScore> gameScores) {
        if (!game.isGameOver()) {
            return null;
        }
        return gameScores.stream()
                .max(Comparator.comparingInt(GameScore::getScore))
                .map(score -> score.getPlayer().id.toString())
                .orElse(null);
    }

    public UUID getId() {
        return id;
    }

    public UUID getCurrentPlayer() {
        return currentPlayer;
    }

    public GameStatus getStatus() {
        return status;
    }

    public List<GameScoreResponseDto> getScores() {
        return scores;
    }

    public String setWinner() {
        return winner;
    }

    public List<PlayerResponseDto> getPlayers() {
        return players;
    }
}
