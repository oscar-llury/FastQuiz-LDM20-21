package com.code.fastquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity {
    private int score;
    private Button answer1,answer2,answer3,answer4;
    private Question question_to_show;
    private ArrayList<Question> arrayQuestions;
    private boolean checking;
    private TextView question;
    private Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        // this.arrayQuestions = (ArrayList<Question>) getIntent().getSerializableExtra("arrayQuestions");
        Initializer ini = new Initializer();
        this.arrayQuestions = ini.getQuestion(5);

        this.player = new Player();
        question = findViewById(R.id.question_text);
        answer1 = findViewById(R.id.button_answer1);
        answer2 = findViewById(R.id.button_answer2);
        answer3 = findViewById(R.id.button_answer3);
        answer4 = findViewById(R.id.button_answer4);


    }
    @Override
    public void onStart() {
        super.onStart();
        Random rnd = new Random(System.currentTimeMillis()*1000);
        question_to_show = arrayQuestions.get((int) (rnd.nextDouble()*arrayQuestions.size()));
        question.setText(question_to_show.getQuestion());

        answer1.setText(question_to_show.getAnswer());
        answer2.setText(question_to_show.getAnswer());
        answer3.setText(question_to_show.getAnswer());
        answer4.setText(question_to_show.getAnswer());
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_answer1: {
                checking = question_to_show.checkCorrectAnswer(1);
                if (checking) {
                    answer1.setBackgroundColor(Color.GREEN);

                } else {
                    answer1.setBackgroundColor(Color.RED);
                }
                break;
            }
            case R.id.button_answer2: {
                checking = question_to_show.checkCorrectAnswer(2);
                if (checking) {
                    answer2.setBackgroundColor(Color.GREEN);
                } else {
                    answer2.setBackgroundColor(Color.RED);
                }
                break;
            }
            case R.id.button_answer3: {
                checking = question_to_show.checkCorrectAnswer(3);
                if (checking) {
                    answer3.setBackgroundColor(Color.GREEN);
                } else {
                    answer3.setBackgroundColor(Color.RED);
                }
                break;
            }
            case R.id.button_answer4: {
                checking = question_to_show.checkCorrectAnswer(4);
                if (checking) {
                    answer4.setBackgroundColor(Color.GREEN);
                } else {
                    answer4.setBackgroundColor(Color.RED);
                }
                break;
            }
        }
            answer1.setEnabled(false);
            answer2.setEnabled(false);
            answer3.setEnabled(false);
            answer4.setEnabled(false);
            //this.arrayQuestions.remove(question_to_show);
            if(checking){
                this.player.increaseScore();
            }else{
                this.player.decreaseScore();
            }
        Toast.makeText(this, "Puntos totales: " + this.player.getScore(), Toast.LENGTH_SHORT).show();
            Intent activity = new Intent(getApplicationContext(),Game.class);
            startActivity(activity);

    }
}