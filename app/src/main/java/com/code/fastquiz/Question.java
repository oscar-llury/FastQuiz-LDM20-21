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
        this.image = false;
        this.answers = new ArrayList<String>();
    }

    public Question(String question, boolean image, String path) {
        this.contShownAnswers = 0;
        this.correctPos = null;
        this.question = question;
        this.image = image;
        this.path = path;
        this.answers = new ArrayList<String>();
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
        this.path = "R.drawable" + path;
    }

    public String getAnswer(){
        if(this.contShownAnswers == 0){
            Random rnd = new Random(421248915);;
            this.correctPos = (int) (rnd.nextDouble()*4);
            System.out.println("correctPos "+correctPos);
        }
        this.contShownAnswers = this.contShownAnswers+1;
        if(this.contShownAnswers==this.correctPos) {
            System.out.println("correcto");
            return this.answers.get(0);
        }else{
            Random rnd2 = new Random(525982935);;
            System.out.println("size "+this.answers.size());
            //System.out.println((int) (rnd2.nextDouble()*this.answers.size()-1)+1);
            int posarray = (int) (rnd2.nextDouble()*this.answers.size()-1)+1;
            System.out.println(posarray);
            String a = this.answers.get(posarray);
            this.answers.remove(posarray);
            return a;
        }

    }

    public void addAnswer(String answer, boolean isCorrect) {
        if (this.answers.isEmpty()) {
            this.answers = new ArrayList<>();
        }
        //this.answers.add(answer);
        if (isCorrect) {
            this.answers.add(0, answer);
        } else {
            this.answers.add(answer);
        }
    }
        public boolean checkCorrectAnswer(int numAnswer){
        return numAnswer == this.correctPos;
        }

}
