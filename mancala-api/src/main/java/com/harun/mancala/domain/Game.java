package com.harun.mancala.domain;

import com.harun.mancala.domain.exception.EmptyPitSowException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;


class Players {
    Player one;
    Player two;
}


public class Game {

    public static final int NUMBER_OF_PITS_PER_PLAYER_WITH_BIG_PIT = 7;
    public static final int NUMBER_OF_PITS_PER_PLAYER = 6;
    private final UUID id;
    private final Players players;
    private final AtomicReference<Player> currentPlayer = new AtomicReference<>(null);


    public Game(UUID id, String playerOneName, String playerTwoName) {
        this.id = id;
        this.players = new Players() {{
            one = new Player(UUID.randomUUID(), playerOneName, 0);
            two = new Player(UUID.randomUUID(), playerTwoName, NUMBER_OF_PITS_PER_PLAYER_WITH_BIG_PIT);
        }};
        this.currentPlayer.set(getPlayerOne());
    }

    private void togglePlayerTurn() {
        currentPlayer.set(currentPlayer.get().equals(getPlayerOne()) ? getPlayerTwo() : getPlayerOne());
    }


    public void makeMove(int pitIndex) {

        final Pit lastSownPit = takeStonesFromPitAndSow(pitIndex);

        getStonesFromOpponent(lastSownPit);

        // Check if the game is over
        if (isGameOver()) {
            moveRemainingStonesToBigPit();
            return;
        }

        moveToTheNextPlayer(lastSownPit);
    }

    private Pit takeStonesFromPitAndSow(int pitIndex) {
        final Pit currentPit = getCurrentPlayer().getPit(pitIndex);

        int stonesToSow = currentPit.getAllStonesToHand();

        if (stonesToSow == 0) {
            throw new EmptyPitSowException();
        }

        final ArrayList<Pit> allPitsCombined = collectAllPits();
        int nextPitIndexToSow = pitIndex + getCurrentPlayer().getStartingIndex();

        while (stonesToSow > 0) {
            nextPitIndexToSow = (nextPitIndexToSow + 1) % allPitsCombined.size();
            final Pit nextPit = allPitsCombined.get(nextPitIndexToSow);
            if (checkIfPitCanBeSown(nextPit)) {
                nextPit.sow();
                stonesToSow = stonesToSow - 1;
            }
        }

        return allPitsCombined.get(nextPitIndexToSow);
    }

    private ArrayList<Pit> collectAllPits() {
        return new ArrayList<>() {{
            addAll(getPlayerOne().getPits());
            add(getPlayerOne().getBigPit());
            addAll(getPlayerTwo().getPits());
            add(getPlayerTwo().getBigPit());
        }};
    }

    private boolean checkIfPitCanBeSown(Pit pit) {
        return pit.getType().equals(PitType.REGULAR) ||
                (pit.getType().equals(PitType.BIG) && pit.getPlayer().id.equals(getCurrentPlayer().id));
    }


    private void getStonesFromOpponent(Pit lastSownPit) {
        if (lastSownPit.getType() != PitType.BIG &&
                lastSownPit.getPlayer().id.equals(getCurrentPlayer().id) &&
                lastSownPit.hasOneStone()
        ) {
            final Pit oppositePit = getOppositePit(lastSownPit.getIndex());
            final int stonesTakenFromOpponent = oppositePit.getAllStonesToHand();
            final int stonesTakenFromOwn = lastSownPit.getAllStonesToHand();
            getCurrentPlayer().getBigPit().addStones(stonesTakenFromOwn + stonesTakenFromOpponent);
        }
    }

    private Pit getOppositePit(int pitIndex) {
        return getOpponentPlayer().getPit(NUMBER_OF_PITS_PER_PLAYER - 1 - pitIndex);
    }


    public void moveRemainingStonesToBigPit() {
        getPlayerOne().moveRemainingStonesToBigPit();
        getPlayerTwo().moveRemainingStonesToBigPit();
    }

    public boolean isGameOver() {
        return getPlayerOne().getTotalNumberOfStonesInPits() == 0 || getPlayerTwo().getTotalNumberOfStonesInPits() == 0;
    }

    private void moveToTheNextPlayer(Pit lastSownPit) {
        if (!(lastSownPit.getType().equals(PitType.BIG) && lastSownPit.getPlayer().id.equals(getCurrentPlayer().id))) {
            togglePlayerTurn();
        }
    }


    public UUID getId() {
        return id;
    }

    public Player getCurrentPlayer() {
        return currentPlayer.get();
    }

    public Player getOpponentPlayer() {
        return currentPlayer.get().equals(getPlayerOne()) ? getPlayerTwo() : getPlayerOne();
    }

    public Player getPlayerOne() {
        return players.one;
    }

    public Player getPlayerTwo() {
        return players.two;
    }

    public List<GameScore> getScores() {
        return List.of(
                new GameScore(getPlayerOne(), getPlayerOne().getBigPit().getCurrentNumberOfStones()),
                new GameScore(getPlayerTwo(), getPlayerTwo().getBigPit().getCurrentNumberOfStones())
        );
    }

    public List<Player> getPlayersAsList() {
        return List.of(getPlayerOne(), getPlayerTwo());
    }
}
