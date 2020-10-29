package com.code.fastquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
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
    private TextView scoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        // this.arrayQuestions = (ArrayList<Question>) getIntent().getSerializableExtra("arrayQuestions");
        Initializer ini = new Initializer();
        arrayQuestions = ini.getQuestion(5);

        player = new Player();
        question = findViewById(R.id.question_text);
        answer1 = findViewById(R.id.button_answer1);
        answer2 = findViewById(R.id.button_answer2);
        answer3 = findViewById(R.id.button_answer3);
        answer4 = findViewById(R.id.button_answer4);
        scoreView = (TextView)findViewById(R.id.score);
        scoreView.setText("0");
    }
    @Override
    public void onStart() {
        super.onStart();
        playGame();
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

        this.arrayQuestions.remove(question_to_show);

        if(checking){
            this.player.increaseScore();
        }else{
            this.player.decreaseScore();

        }
        Toast.makeText(this, "Puntos totales: " + this.player.getScore(), Toast.LENGTH_SHORT).show();
        updateScore(this.player.getScore());
        showPopUp(checking);
        //SystemClock.sleep(2000);

        //playGame();

    }

    private void playGame(){

        if(this.arrayQuestions.size()>0) {
            Random rnd = new Random(System.currentTimeMillis() * 1000);
            question_to_show = arrayQuestions.get((int) (rnd.nextDouble() * arrayQuestions.size()));

            answer1.setBackgroundColor(Color.GRAY);
            answer2.setBackgroundColor(Color.GRAY);
            answer3.setBackgroundColor(Color.GRAY);
            answer4.setBackgroundColor(Color.GRAY);
            question.setText(question_to_show.getQuestion());

            System.out.println("intro sleep");
            // SystemClock.sleep(2000);
            System.out.println("out sleep");

            answer1.setText(question_to_show.getAnswer());
            answer2.setText(question_to_show.getAnswer());
            answer3.setText(question_to_show.getAnswer());
            answer4.setText(question_to_show.getAnswer());

            answer1.setEnabled(true);
            answer2.setEnabled(true);
            answer3.setEnabled(true);
            answer4.setEnabled(true);
        }else{
            initScoreActivity();
        }
    }

    public Dialog showPopUp(boolean correct) {
        androidx.appcompat.app.AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        String popUpText;
        if(correct) {
            popUpText = getString(R.string.popUpCorrect);
        }else{
            popUpText = getString(R.string.popUpIncorrect);
        }
        popUp.setTitle(R.string.wrong_answer_title)
                .setMessage(popUpText + "Tienes "+this.player.getScore()+" puntos.")
                .setPositiveButton(R.string.wrong_answer_continue, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        playGame();
                        dialog.cancel();
                    }
                })
                .setNegativeButton(R.string.wrong_answer_exit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        initScoreActivity();
                    }
                });
        return popUp.show();
    }

    private void initScoreActivity(){
        //star activity score
        Intent activity = new Intent(Game.this,FinalScore.class);
        activity.putExtra("score", this.player.getScore());
        startActivity(activity);
    }
    private void updateScore(int score){
        if(score<=0)
            initScoreActivity();
        scoreView.setText("" + score);
    }
}