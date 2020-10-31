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
    private int total_questions, num_questions_count;
    private Button answer1,answer2,answer3,answer4;
    private Question question_to_show;
    private ArrayList<Question> arrayQuestions;
    private boolean checking, questions_with_images;
    private TextView question;
    private Player player;
    private TextView scoreView, questions_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent mIntent = getIntent();
        int playerMode = mIntent.getIntExtra("mode", 0);
        int images = mIntent.getIntExtra("images", 0);

        if(playerMode==1){
            this.total_questions = 1;
        }else if(playerMode==2){
            this.total_questions = 5;
        }else{
            //finalizar juego
        }
        this.questions_with_images = images==1;
        //Toast.makeText(this, "Puntos totales: " + this.questions_with_images, Toast.LENGTH_SHORT).show();

        Initializer ini = new Initializer();

        this.num_questions_count = 0;
        this.arrayQuestions = ini.getQuestion(this.total_questions);
        this.player = new Player();
        this.question = findViewById(R.id.question_text);
        this.answer1 = findViewById(R.id.button_answer1);
        this.answer2 = findViewById(R.id.button_answer2);
        this.answer3 = findViewById(R.id.button_answer3);
        this.answer4 = findViewById(R.id.button_answer4);
        this.scoreView = (TextView)findViewById(R.id.score);
        this.questions_count = (TextView)findViewById(R.id.questions_count);
        updateScore();
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

        this.num_questions_count ++;
        this.arrayQuestions.remove(question_to_show);

        if(checking){
            this.player.increaseScore();
        }else{
            this.player.decreaseScore();

        }
        updateScore();
        showPopUp(checking);
        //SystemClock.sleep(2000);
    }

    private void playGame(){

        if(this.arrayQuestions.size()>0) {
            Random rnd = new Random(System.currentTimeMillis() * 1000);
            question_to_show = arrayQuestions.get((int) (rnd.nextDouble() * arrayQuestions.size()));

            //answer1.setBackgroundColor(getResources().getColor(R.color.textColor));
            answer1.setBackgroundColor(R.drawable.button_answer);
            answer2.setBackgroundColor(R.drawable.button_answer);
            answer3.setBackgroundColor(R.drawable.button_answer);
            answer4.setBackgroundColor(R.drawable.button_answer);
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
        String popUpTitle;
        if(this.player.getScore()>0 && this.num_questions_count<this.total_questions) {
            if (correct) {
                popUpTitle = getString(R.string.popUpCorrect);
            } else {
                popUpTitle = getString(R.string.popUpIncorrect);
            }
            popUp.setTitle(popUpTitle)
                    .setMessage("Tienes " + this.player.getScore() + " puntos. \n" + getString(R.string.answer_text))
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
        }else{
            String tittle,text;
            if(correct && this.num_questions_count>=this.total_questions){
                tittle = getString(R.string.finalTittle);
                text = getString(R.string.popFinish);
            }else{
                tittle = getString(R.string.popUpOver);
                text = getString(R.string.popOverr);
            }
            popUp.setTitle(tittle)
                .setMessage(text)
                .setNegativeButton(R.string.wrong_answer_exit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        initScoreActivity();
                    }
                });
        }
        return popUp.show();
    }

    private void initScoreActivity(){
        Intent activity = new Intent(Game.this,FinalScore.class);
        activity.putExtra("score", this.player.getScore());
        startActivity(activity);
    }

    private void updateScore(){
        int score = this.player.getScore();
        if(score >0){
            this.scoreView.setText(""+score);

        }else{
            this.scoreView.setText("0");
        }
        this.questions_count.setText(this.num_questions_count+"/"+this.total_questions);
    }
}