package com.code.fastquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity {
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private Question question_to_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView question = findViewById(R.id.question_text);
        answer1 = findViewById(R.id.button_answer1);
        answer2 = findViewById(R.id.button_answer2);
        answer3 = findViewById(R.id.button_answer3);
        answer4 = findViewById(R.id.button_answer4);

        Initializer ini = new Initializer();
        ArrayList<Question> arrayQuestions = ini.getQuestion(5);

        Random rnd = new Random(2582054);
        question_to_show = arrayQuestions.get((int) (rnd.nextDouble()*arrayQuestions.size()));
        question.setText(question_to_show.getQuestion());

        answer1.setText(question_to_show.getAnswer());
        answer2.setText(question_to_show.getAnswer());
        answer3.setText(question_to_show.getAnswer());
        answer4.setText(question_to_show.getAnswer());

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_answer1:{
                boolean checking = question_to_show.checkCorrectAnswer(1);
                if(checking) {
                    answer1.setBackgroundColor(Color.GREEN);
                }else{
                    answer1.setBackgroundColor(Color.RED);
                }
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                answer4.setEnabled(false);
            }
            case R.id.button_answer2:{
                boolean checking2 = question_to_show.checkCorrectAnswer(2);
                if(checking2) {
                    answer2.setBackgroundColor(Color.GREEN);
                }else{
                    answer2.setBackgroundColor(Color.RED);
                }
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                answer4.setEnabled(false);
            }
            case R.id.button_answer3:{
                boolean checking3 = question_to_show.checkCorrectAnswer(3);
                if(checking3) {
                    answer3.setBackgroundColor(Color.GREEN);
                }else{
                    answer3.setBackgroundColor(Color.RED);
                }
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                answer4.setEnabled(false);
            }
            case R.id.button_answer4:{
                boolean checking4 = question_to_show.checkCorrectAnswer(4);
                if(checking4) {
                    answer4.setBackgroundColor(Color.GREEN);
                }else{
                    answer4.setBackgroundColor(Color.RED);
                }
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                answer4.setEnabled(false);
            }
        }

    }
}