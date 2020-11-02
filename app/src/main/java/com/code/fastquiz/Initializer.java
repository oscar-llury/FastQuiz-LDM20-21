package com.code.fastquiz;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Esta clase como almacenamiento persistente de datos
 *
 * @author Carlos González, Óscar Rivas
 */

public class Initializer extends AppCompatActivity {
    private ArrayList<Question> list_questions;

    public Initializer() {
        this.list_questions = new ArrayList<>();

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
        Question question8 = new Question("¿A qué se dedica la siguiente persona?",true,"martin");
        question8.addAnswer("Modelo", false);
        question8.addAnswer("Futbolista", false);
        question8.addAnswer("DJ", true);
        question8.addAnswer("Presentador de TV", false);
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
        Question question12 = new Question("Identifica a la siguiente persona",true,"jeff");
        question12.addAnswer("Bill Gates", false);
        question12.addAnswer("Mark Zuckemberg", false);
        question12.addAnswer("Jeff Bezos", true);
        question12.addAnswer("Sundar Pichai", false);
        Question question13 = new Question("Identifica el país",true,"mongolia");
        question13.addAnswer("España", false);
        question13.addAnswer("Filipinas", false);
        question13.addAnswer("Mongolia", true);
        question13.addAnswer("China", false);
        Question question14 = new Question("¿A qué empresa pertenece este logo?",true,"logomc");
        question13.addAnswer("Burger King", false);
        question13.addAnswer("McDonalds", true);
        question13.addAnswer("KFC", false);
        question13.addAnswer("Taco Bell", false);
        Question question15 = new Question("¿A qué empresa pertenece este logo?",true,"adidas");
        question13.addAnswer("Nike", false);
        question13.addAnswer("Reebok", false);
        question13.addAnswer("New Balance", false);
        question13.addAnswer("Adidas", true);


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
        this.list_questions.add(question13);
        this.list_questions.add(question14);
        this.list_questions.add(question15);
    }

    public ArrayList<Question> getQuestion(int numquest, boolean withImages) {
        if (numquest <= this.list_questions.size()) {
            if(withImages) {
                return new ArrayList<>(this.list_questions.subList(0, numquest));
            }else{
                ArrayList<Question> arrayQuestions = new ArrayList<>();
                Iterator<Question> it = this.list_questions.iterator();
                int cont = 0;
                while(it.hasNext() && cont<numquest){
                    Question q = it.next();
                    if(!q.isImage())
                        arrayQuestions.add(q);
                        cont++;
                }
                return arrayQuestions;
            }
        } else {
            System.err.println("Se ha producido un error. El número de preguntas almacenado es inferior al solicitado.");
            return null;
        }
    }

    public int init_size(){
        return this.list_questions.size();
    }

}


