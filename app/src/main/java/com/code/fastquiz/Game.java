package com.code.fastquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView question = findViewById(R.id.question_text);
        Button answer1 = findViewById(R.id.button_answer1);


        Question question1 = new Question("¿Como de tonto soy?");
        question1.addAnswer("Mucho");
        question1.addAnswer("Poco");
        question1.addAnswer("Demasiado");
        question1.setCorrect(1);
        Question question2 = new Question("¿Vamos a aprobar?");
        question2.addAnswer("Sí");
        question2.addAnswer("No");
        question2.addAnswer("Con ayuda de Jesucristo puede ser");
        question2.setCorrect(1);
        Question question3 = new Question("¿Quién descubrió América?");
        question3.addAnswer("Cristiano Ronaldo");
        question3.addAnswer("Messi");
        question3.addAnswer("Cristóbal Colón");
        question3.setCorrect(3);

        question.setText(question1.getQuestion());
        //answer1.setText(question1.ge1);

    }
}
