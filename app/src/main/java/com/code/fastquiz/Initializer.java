package com.code.fastquiz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Initializer extends AppCompatActivity {
    private ArrayList<Question> list_questions;

    public Initializer() {
        this.list_questions = new ArrayList<Question>();

        Question question1 = new Question("¿Cuál es la capital de Mongolia?");
        question1.addAnswer("Estambul", false);
        question1.addAnswer("Ulan Bator", true);
        question1.addAnswer("Madrid", false);
        question1.addAnswer("Tokio", false);
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
        Question question4 = new Question("¿Cuándo acabó la 2º Guerra Mundial?");
        question4.addAnswer("1936", false);
        question4.addAnswer("1938", false);
        question4.addAnswer("1942", false);
        question4.addAnswer("1945", true);
        Question question5 = new Question("¿Cuál es el océano más grande del mundo?");
        question5.addAnswer("Pacífico", true);
        question5.addAnswer("Índico", false);
        question5.addAnswer("Atlántico", false);
        question5.addAnswer("Antártico", false);
        this.list_questions.add(question1);
        this.list_questions.add(question2);
        this.list_questions.add(question3);
        this.list_questions.add(question4);
        this.list_questions.add(question5);
    }

    public ArrayList<Question> getQuestion(int numquest) {
        if (numquest <= this.list_questions.size()) {
                return new ArrayList<Question>(this.list_questions.subList(0,numquest));
        } else {
            System.err.println("Se ha producido un error. El número de preguntas almacenado es inferior al solicitado.");
            return null;
        }
    }
}


