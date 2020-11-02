package com.code.fastquiz;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Initializer extends AppCompatActivity {
    private ArrayList<Question> list_questions;

    public Initializer() {
        this.list_questions = new ArrayList<>();

        Question question1 = new Question("¿Cuál es la capital de Mongolia?");
        question1.addAnswer("Estambul", false);
        question1.addAnswer("Ulan Bator", true);
        question1.addAnswer("Madrid", false);
        question1.addAnswer("Tokio", false);
        question1.setImage(true);
        Question question2 = new Question("¿Quién escribió el Quijote?");
        question2.addAnswer("Miguel de Unamuno", false);
        question2.addAnswer("Federico García Lorca", false);
        question2.addAnswer("Miguel de Cervantes", true);
        question2.addAnswer("Gaspar Melchor de Jovellanos", false);
        Question question3 = new Question("¿Quién descubrió América?");
        question3.addAnswer("Cristiano Ronaldo", false);
        question3.addAnswer("Messi", false);
        question3.addAnswer("Cristóbal Colón", true);
        question3.addAnswer("Hernán Cortés", false);
        Question question4 = new Question("¿Cuándo acabó la 2º Guerra Mundial?",true,"jeff.jpg");
        question4.addAnswer("1936", false);
        question4.addAnswer("1938", false);
        question4.addAnswer("1942", false);
        question4.addAnswer("1945", true);
        Question question5 = new Question("¿Cuál es el océano más grande del mundo?");
        question5.addAnswer("Pacífico", true);
        question5.addAnswer("Índico", false);
        question5.addAnswer("Atlántico", false);
        question5.addAnswer("Antártico", false);
        Question question6 = new Question("¿Cómo se llama la reina de Inglaterra?");
        question6.addAnswer("María", false);
        question6.addAnswer("Elisa", false);
        question6.addAnswer("Isabel", true);
        question6.addAnswer("Juana", false);
        Question question7 = new Question("¿Cuál es el río más largo del mundo?");
        question7.addAnswer("Nilo", false);
        question7.addAnswer("Sena", false);
        question7.addAnswer("Danubio", false);
        question7.addAnswer("Amazonas", true);
        Question question8 = new Question("¿Cuándo acabó la 2º Guerra Mundial?");
        question8.addAnswer("1936", false);
        question8.addAnswer("1938", false);
        question8.addAnswer("1942", false);
        question8.addAnswer("1945", true);
        Question question9 = new Question("¿Dónde se originaron los juegos olímpicos?");
        question9.addAnswer("Italia", false);
        question9.addAnswer("Francia", false);
        question9.addAnswer("Grecia", true);
        question9.addAnswer("EEUU", false);
        Question question10 = new Question("¿Quién es el famoso rey del Rock de EEUU?");
        question10.addAnswer("Michael Jackson", false);
        question10.addAnswer("Elvis Presley", true);
        question10.addAnswer("Elton John", false);
        question10.addAnswer("Fredie Mercury", false);
        Question question11 = new Question("¿A qué país pertenecen los cariocas?");
        question11.addAnswer("Brasil", true);
        question11.addAnswer("Chile", false);
        question11.addAnswer("Venezuela", false);
        question11.addAnswer("Colombia", false);
        Question question12 = new Question("¿Cómo se llama el fundador de Amazon?");
        question12.addAnswer("Bill Gates", false);
        question12.addAnswer("Mark Zuckemberg", false);
        question12.addAnswer("Jeff Bezos", true);
        question12.addAnswer("Sundar Pichai", false);

        this.list_questions.add(question1);
        this.list_questions.add(question2);
        this.list_questions.add(question3);
        this.list_questions.add(question4);
        this.list_questions.add(question5);
        this.list_questions.add(question6);
        this.list_questions.add(question7);
        this.list_questions.add(question8);
        this.list_questions.add(question9);
        this.list_questions.add(question10);
        this.list_questions.add(question11);
        this.list_questions.add(question12);
    }

    public ArrayList<Question> getQuestion(int numquest) {
        if (numquest <= this.list_questions.size()) {
                return new ArrayList<>(this.list_questions.subList(0,numquest));
        } else {
            System.err.println("Se ha producido un error. El número de preguntas almacenado es inferior al solicitado.");
            return null;
        }
    }

    public int init_size(){
        return this.list_questions.size();
    }

}


