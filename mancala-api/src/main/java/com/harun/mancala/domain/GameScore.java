package com.harun.mancala.domain;

public record GameScore(Player player, int score) {

    public Player getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

}


