package com.harun.mancala.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Player {

    private static final int NUMBER_OF_PITS_PER_SECTION = 6;
    private static final int INITIAL_NUMBER_OF_STONES_PER_SMALL_PIT = 6;
    private static final int INITIAL_NUMBER_OF_STONES_PER_STORE = 0;

    public final UUID id;
    public final String name;

    private final ArrayList<Pit> pits;

    private final Pit bigPit;
    private final int startingIndex;

    public Player(final UUID id, final String name, int startingIndex) {
        this.id = id;
        this.name = name;
        this.startingIndex = startingIndex;

        this.pits = IntStream.range(0, NUMBER_OF_PITS_PER_SECTION)
                .mapToObj(i -> createPit(INITIAL_NUMBER_OF_STONES_PER_SMALL_PIT, PitType.REGULAR, i))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        this.bigPit = createPit(INITIAL_NUMBER_OF_STONES_PER_STORE, PitType.BIG, -1);
    }

    private Pit createPit(int initialNumberOfStonesPerSmallPit, PitType type, int index) {
        return new Pit(type, index, initialNumberOfStonesPerSmallPit, this);
    }

    public void moveRemainingStonesToBigPit() {
        this.bigPit.addStones(
                getPits()
                        .stream()
                        .mapToInt(Pit::getAllStonesToHand)
                        .sum()
        );
    }

    public Pit getPit(int index) {
        return pits.get(index);
    }

    public List<Pit> getPits() {
        return pits;
    }

    public Pit getBigPit() {
        return bigPit;
    }

    public int getTotalNumberOfStonesInPits() {
        return pits.stream()
                .mapToInt(Pit::getCurrentNumberOfStones)
                .sum();
    }

    public int getStartingIndex() {
        return startingIndex;
    }
}
