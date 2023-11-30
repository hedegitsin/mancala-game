package com.harun.mancala.repository;

import com.harun.mancala.domain.Game;
import com.harun.mancala.domain.repository.IGameRepository;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class GameRepository implements IGameRepository {
    private final HashMap<UUID, Game> games = new HashMap<>();

    public void save(final Game game) {
        games.put(game.getId(), game);
    }

    public Optional<Game> get(final UUID id) {
        return Optional.ofNullable(games.get(id));
    }
}
