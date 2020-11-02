package com.code.fastquiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase de tipo objeto Question
 *
 * @author Carlos González, Óscar Rivas
 */
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
        this.path = path;
    }

    /**
     * Este método devuelve aleatoriamente una de las posibles respuestas de la pregunta
     *
     * @return String la respuesta
     */
    public String getAnswer(){
        if(this.contShownAnswers == 0){
            Random rnd = new Random(System.currentTimeMillis() * 13000);;
            this.correctPos = (int) (rnd.nextDouble()*4)+1;
        }
        this.contShownAnswers = this.contShownAnswers+1;
        if(this.contShownAnswers==this.correctPos) {
            return this.answers.get(0);
        }else{
            Random rnd2 = new Random(System.currentTimeMillis() * 7000);;
            int posarray = (int) (rnd2.nextDouble()*this.answers.size()-1)+1;
            String a = this.answers.get(posarray);
            this.answers.remove(posarray);
            return a;
        }

    }

    /**
     * Este método añade una respuesta para la pregunta
     *
     * @param answer String la respuesta
     * @param isCorrect boolean si es correcta o no
     */

    public void addAnswer(String answer, boolean isCorrect) {
        if (this.answers.isEmpty()) {
            this.answers = new ArrayList<>();
        }
        if (isCorrect) {
            this.answers.add(0, answer);
        } else {
            this.answers.add(answer);
        }
    }
    /**
     * Este método comprueba si la posición de la respuesta es la correcta
     *
     * @param numAnswer int el número de la respuesta clicada
     * @return boolean si la respuesta es correcta
     */
    public boolean checkCorrectAnswer(int numAnswer){
        return numAnswer == this.correctPos;
    }

}
