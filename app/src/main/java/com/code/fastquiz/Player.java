package com.code.fastquiz;

public class Player {
    private int score;

    public Player() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }
    /**
     * Este método aumenta la puntuación del jugador
     *
     */
    public void increaseScore() {
        this.score = this.score+3;
    }
    /**
     * Este método disminuye la puntuación del jugador
     *
     */
    public void decreaseScore() {
        this.score = this.score-2;
    }
}
