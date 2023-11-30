package com.harun.mancala.domain.service;

import com.harun.mancala.domain.Game;
import com.harun.mancala.domain.Player;
import com.harun.mancala.domain.exception.EntityNotFoundException;
import com.harun.mancala.domain.repository.IGameRepository;
import com.harun.mancala.domain.utils.UuidUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;


public class GameService implements IGameService {

    private final IGameRepository gameRepository;

    public GameService(final IGameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game startNewGame(String playerOneName, String playerTwoName) {
        final Game game = new Game(UUID.randomUUID(), playerOneName, playerTwoName);
        gameRepository.save(game);
        return game;
    }

    public Game makeMove(
            final String gameId,
            final int pitId
    ) {

        final UUID gameIdUUID = UuidUtils.fromString(gameId, "Invalid game id");

        final Game game = gameRepository.get(gameIdUUID)
                .orElseThrow(() -> new EntityNotFoundException("Could not find game with id " + gameId));

        game.makeMove(pitId);
        gameRepository.save(game);
        return game;
    }


}
