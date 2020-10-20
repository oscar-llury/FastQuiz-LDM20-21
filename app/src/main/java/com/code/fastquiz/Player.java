package com.code.fastquiz;

public class Player {
    private int score;
    public Player() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void increaseScore() {
        this.score = this.score+3;
    }
    public void decreaseScore() {
        this.score = this.score-2;
    }
}
