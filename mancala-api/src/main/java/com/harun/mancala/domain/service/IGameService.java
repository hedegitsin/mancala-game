package com.harun.mancala.domain.service;

import com.harun.mancala.domain.Game;

public interface IGameService {
    Game startNewGame(String playerOneName, String playerTwoName);

    Game makeMove(
            final String gameId,
            final int pitId
    );
}
