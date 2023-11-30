package com.harun.mancala.domain;

import com.harun.mancala.domain.exception.EmptyPitSowException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GameTests {

    private Game game;

    @BeforeEach
    void setUp() {
        game = spy(new Game(UUID.randomUUID(), "Player 1", "Player 2"));
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testMakeMove_negativeIndexThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> game.makeMove(-1));
    }

    @Test
    void testMakeMove_indexGreaterThanPitSizeThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> game.makeMove(7));
    }

    @Test
    void testMakeMove_emptyPitThrowsException() {
        game.getCurrentPlayer().getPit(0).getAllStonesToHand();
        assertThrows(EmptyPitSowException.class, () -> game.makeMove(0));
    }

    @Test
    void testMakeMove_moveStonesToNextPit() {
        game.makeMove(1);
        assertEquals(0, game.getPlayerOne().getPit(1).getCurrentNumberOfStones());
        assertEquals(7, game.getPlayerOne().getPit(2).getCurrentNumberOfStones());
    }

    @Test
    void testMakeMove_moveStonesToBigPit() {
        game.makeMove(5);
        assertEquals(0, game.getPlayerOne().getPit(5).getCurrentNumberOfStones());
        assertEquals(1, game.getPlayerOne().getBigPit().getCurrentNumberOfStones());
    }

    @Test
    void testMakeMove_currentPlayerNotChangedWhenEndedInBigPit() {
        game.makeMove(0);
        assertEquals(game.getPlayerOne(), game.getCurrentPlayer());
    }

    @Test
    void testMakeMove_getStonesFromOppositePit() {
        final Player mockPlayerOne = spy(new Player(UUID.randomUUID(), "Mock Player 1", 0));
        final Player mockPlayerTwo = spy(new Player(UUID.randomUUID(), "Mock Player 2", 7));

        final ArrayList<Pit> mockPlayerOnePits = new ArrayList<>(){{
            add(new Pit(PitType.REGULAR, 0, 1, mockPlayerOne));
            add(new Pit(PitType.REGULAR, 1, 0, mockPlayerOne));
            add(new Pit(PitType.REGULAR, 2, 0, mockPlayerOne));
            add(new Pit(PitType.REGULAR, 3, 0, mockPlayerOne));
            add(new Pit(PitType.REGULAR, 4, 0, mockPlayerOne));
            add(new Pit(PitType.REGULAR, 5, 0, mockPlayerOne));
        }};


        when(mockPlayerOne.getPits()).thenReturn(mockPlayerOnePits);
        when(mockPlayerOne.getPit(0)).thenReturn(mockPlayerOnePits.get(0));
        when(mockPlayerOne.getPit(1)).thenReturn(mockPlayerOnePits.get(1));


        when(game.getPlayerOne()).thenReturn(mockPlayerOne);
        when(game.getPlayerTwo()).thenReturn(mockPlayerTwo);
        when(game.getCurrentPlayer()).thenReturn(mockPlayerOne);
        when(game.getOpponentPlayer()).thenReturn(mockPlayerTwo);


        game.makeMove(0);
        final int oppositePitIndex = 4; // Last sown pit is 1, so opposite pit is 5-1 = 4
        final int totalStonesInBigPit = 7; // 1 stone from last sown pit + 6 stones from opposite pit
        assertEquals(0, game.getPlayerTwo().getPit(oppositePitIndex).getCurrentNumberOfStones());
        assertEquals(7, game.getPlayerOne().getBigPit().getCurrentNumberOfStones());
    }



}
