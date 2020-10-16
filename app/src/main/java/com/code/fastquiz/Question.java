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
        this.answers = new ArrayList<String>();
    }

    public Question(String question, boolean image, String path) {
        this.question = question;
        this.image = image;
        this.path = path;
        this.answers = new ArrayList<String>();
    }
    public int getCorrect() {
        return correct;
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

    public String getAnswer(){
        return this.answers.get(1);
    }

    public void addAnswer(String answer,boolean isCorrect) {
        if (this.answers.isEmpty()) {
            this.answers = new ArrayList<String>();
        }
        this.answers.add(answer);
        if(isCorrect) {
            this.correct = this.answers.size();
            System.out.println(this.answers.size());
        }

    }
}
