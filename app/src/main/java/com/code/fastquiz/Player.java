package com.code.fastquiz;

public class Player {

    private String name;
    private int score;

    public Player() {
        this.name="";
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
