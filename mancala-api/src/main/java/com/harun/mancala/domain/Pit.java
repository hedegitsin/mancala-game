package com.harun.mancala.domain;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Pit {


    private final PitType type;

    private final Player player;

    private final AtomicInteger stones = new AtomicInteger(0);
    private final int index;


    public Pit(PitType type, final int index, final int stones, final Player player) {
        this.type = type;
        this.stones.set(stones);
        this.player = player;
        this.index= index;
    }

    public void sow() {
        this.stones.getAndIncrement();
    }

    public void addStones(int stones) {
        this.stones.getAndAdd(stones);
    }

    public int getAllStonesToHand() {
        return stones.getAndSet(0);
    }

    public int getCurrentNumberOfStones() {
        return stones.get();
    }

    public boolean hasOneStone() {
        return stones.get() == 1;
    }

    @Override
    public String toString() {
        return "Pit{" +
                ", type=" + type +
                ", stones=" + stones +
                '}';
    }

    public Player getPlayer() {
        return player;
    }

    public PitType getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }
}
