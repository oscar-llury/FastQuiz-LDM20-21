package com.code.fastquiz;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Question question1 = new Question("¿Como de tonto soy?");
        question1.addAnswer("Mucho");
        Question question2 = new Question("¿Vamos a aprobar?");
        question2.addAnswer("Sí");
                         }

        }

