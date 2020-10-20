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

        Initializer ini = new Initializer();
        ArrayList<Question> arrayQuestions = ini.getQuestion(5);

        Random rnd = new Random(2582054);
        Question question_to_show = arrayQuestions.get((int) (rnd.nextDouble()*arrayQuestions.size()));
        question.setText(question_to_show.getQuestion());

        answer1.setText(question_to_show.getAnswer());
        answer2.setText(question_to_show.getAnswer());
        answer3.setText(question_to_show.getAnswer());
        answer4.setText(question_to_show.getAnswer());

    }
}