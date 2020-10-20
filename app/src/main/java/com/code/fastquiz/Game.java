package com.code.fastquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView question = findViewById(R.id.question_text);
        Button answer1 = findViewById(R.id.button_answer1);
        Button answer2 = findViewById(R.id.button_answer2);
        Button answer3 = findViewById(R.id.button_answer3);
        Button answer4 = findViewById(R.id.button_answer4);

        ArrayList<Question> list_questions = new ArrayList<Question>();

        Question question1 = new Question("¿Cuál es la capital de Mongolia?");
        question1.addAnswer("Estambul",false);
        question1.addAnswer("Ulan Bator",true);
        question1.addAnswer("Madrid",false);
        question1.addAnswer("Tokio",false);
        Question question2 = new Question("¿Quién escribió el Quijote?");
        question2.addAnswer("Miguel de Unamuno",false);
        question2.addAnswer("Federico García Lorca",false);
        question2.addAnswer("Miguel de Cervantes",true);
        question2.addAnswer("Gaspar Melchor de Jovellanos",false);
        Question question3 = new Question("¿Quién descubrió América?");
        question3.addAnswer("Cristiano Ronaldo",false);
        question3.addAnswer("Messi",false);
        question3.addAnswer("Cristóbal Colón",true);
        question3.addAnswer("Hernán Cortés",false);
        Question question4 = new Question("¿Cuándo acabó la 2º Guerra Mundial?");
        question4.addAnswer("1936",false);
        question4.addAnswer("1938",false);
        question4.addAnswer("1942",false);
        question4.addAnswer("1945",true);
        Question question5 = new Question("¿Cuál es el océano más grande del mundo?");
        question5.addAnswer("Pacífico",true);
        question5.addAnswer("Índico",false);
        question5.addAnswer("Atlántico",false);
        question5.addAnswer("Antártico",false);

        list_questions.add(question1);
        list_questions.add(question2);
        list_questions.add(question3);
        list_questions.add(question4);

        Random rnd = new Random(2582054);
        Question question_to_show = list_questions.get((int) (rnd.nextDouble()*list_questions.size()));
        question.setText(question_to_show.getQuestion());

        answer1.setText(question_to_show.getAnswer());
        answer2.setText(question_to_show.getAnswer());
        answer3.setText(question_to_show.getAnswer());
        answer4.setText(question_to_show.getAnswer());

    }
}
