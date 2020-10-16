package com.code.fastquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.button_start_game);
        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent activity = new Intent(getApplicationContext(),Game.class);
                startActivity(activity);
            }
        });

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
        Question question3 = new Question("¿Quién descubrió América?")
                question3.addAnswer("Cristiano Ronaldo");
                question3.addAnswer("Messi");
                question3.addAnswer("Cristóbal Colón");
                question3.addAnswer(3);
    }

        }

