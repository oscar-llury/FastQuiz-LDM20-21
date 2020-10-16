package com.code.fastquiz;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int correct;
    private String question;
    private boolean image;
    private String path;
    private List<String> answers;

    public Question() {
    }

    public Question(String question) {
        this.question = question;
    }

    public Question(String question, boolean image, String path) {
        this.question = question;
        this.image = image;
        this.path = path;
    }
    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct-1;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void addAnswer(String answer) {
        if (this.answers.isEmpty()) {
            this.answers = new ArrayList<String>();
        }
        this.answers.add(answer);
    }
}
