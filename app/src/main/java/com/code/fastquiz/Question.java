package com.code.fastquiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Question {
    private Integer correctPos; //Posición correcta en los botones
    private String question;
    private boolean image;
    private String path;
    private List<String> answers;
    private int contShownAnswers;

    public Question() {
    }

    public Question(String question) {
        this.contShownAnswers = 0;
        this.correctPos = null;
        this.question = question;
    }

    public Question(String question, boolean image, String path) {
        this.contShownAnswers = 0;
        this.correctPos = null;
        this.question = question;
        this.image = image;
        this.path = path;
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
        if(this.contShownAnswers == 0){
        Random rnd = new Random();
        correctPos = (int) (rnd.nextDouble()*4);
        System.out.println(correctPos);
        }
        contShownAnswers = contShownAnswers++;
        if(contShownAnswers==correctPos) {
            return this.answers.get(1);
        }else{
            Random rnd2 = new Random();
            System.out.println(this.answers.size());
            return this.answers.get((int) (rnd2.nextDouble()*this.answers.size()));
        }

    }

    public void addAnswer(String answer, boolean isCorrect) {
        if (this.answers.isEmpty()) {
            this.answers = new ArrayList<String>();
        }
        this.answers.add(answer);
        if(isCorrect) {
            this.answers.add(0,answer);
        }else{
            this.answers.add(answer);
        }

    }
}
