package com.harun.mancala.domain.repository;

import com.harun.mancala.domain.Game;

import java.util.Optional;
import java.util.UUID;

public interface IGameRepository {
    void save(final Game game);
    Optional<Game> get(final UUID id);
}
