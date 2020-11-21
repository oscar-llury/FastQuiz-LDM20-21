package com.code.fastquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;

/**
 * Clase para desarrollar el juego
 *
 * @author Carlos González, Óscar Rivas
 */
public class Game extends AppCompatActivity {
    private int total_questions, num_questions_count;
    private Button answer1, answer2, answer3, answer4;
    private Question question_to_show;
    private ArrayList<Question> arrayQuestions;
    private boolean checking, questions_with_images;
    private TextView question;
    private Player player;
    private TextView scoreView, questions_count;
    private ImageView imageView_question;
    private TextView countDownTextView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("FASTQUIZ_CONFIG", Context.MODE_PRIVATE);
        boolean isNightModeEnabled = prefs.getBoolean("NIGHT_MODE", false);
        if (isNightModeEnabled) {
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.FastQuizTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent mIntent = getIntent();
        int playerMode = mIntent.getIntExtra("mode", 0);
        int images = mIntent.getIntExtra("images", 0);
        Toolbar toolb = findViewById(R.id.app_bar);
        setSupportActionBar(toolb);
        toolb.setNavigationIcon(R.mipmap.ic_fastquiz);
        Initializer ini = new Initializer();

        if (playerMode == 1) {
            this.total_questions = 5;
        } else if (playerMode == 2) {
            this.total_questions = ini.init_size();
        }
        this.questions_with_images = images == 1;
        this.num_questions_count = 0;

        boolean first_time = prefs.getBoolean("FIRST_TIME", true);
        if(!first_time){
            this.arrayQuestions = loadFileQuestions(this.total_questions, this.questions_with_images);
        }

        //this.arrayQuestions = ini.getQuestion(this.total_questions, this.questions_with_images);
        if (!this.questions_with_images && playerMode == 2) {
            this.total_questions = this.arrayQuestions.size();
        }
        this.player = new Player();
        this.question = findViewById(R.id.question_text);
        this.answer1 = findViewById(R.id.button_answer1);
        this.answer2 = findViewById(R.id.button_answer2);
        this.answer3 = findViewById(R.id.button_answer3);
        this.answer4 = findViewById(R.id.button_answer4);
        this.scoreView = findViewById(R.id.score);
        this.questions_count = findViewById(R.id.questions_count);
        this.imageView_question = findViewById(R.id.imageView_question);
        this.countDownTextView = findViewById(R.id.countDownText);
        updateScore();
    }
    @Override
    public void onStart() {
        super.onStart();
        playGame();
    }
    /**
     * Este método se ejecuta cuando el usuario pulsa una respuesta
     *
     * @param v View
     */
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
        checkingAnswer(true,checking);
    }

    /**
     * Método que establece la información de la pregunta en el layout
     *
     */
    private void playGame(){
        imageView_question.setVisibility(View.GONE);
        if(this.arrayQuestions.size()>0) {
            Random rnd = new Random(System.currentTimeMillis() * 1000);
            question_to_show = arrayQuestions.get((int) (rnd.nextDouble() * arrayQuestions.size()));

            answer1.setBackgroundColor(Color.parseColor("#b75c4c"));
            answer2.setBackgroundColor(Color.parseColor("#b75c4c"));
            answer3.setBackgroundColor(Color.parseColor("#b75c4c"));
            answer4.setBackgroundColor(Color.parseColor("#b75c4c"));
            question.setText(question_to_show.getQuestion());

            if(this.questions_with_images && this.question_to_show.isImage()) {
                int imgRsc =  getResources().getIdentifier(this.question_to_show.getPath(), "drawable", getApplicationContext().getPackageName());
                imageView_question.setVisibility(ImageView.VISIBLE);
                imageView_question.setImageDrawable(getResources().getDrawable(imgRsc));
            }
            answer1.setText(question_to_show.getAnswer());
            answer2.setText(question_to_show.getAnswer());
            answer3.setText(question_to_show.getAnswer());
            answer4.setText(question_to_show.getAnswer());
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            StartCountDown();
            answer1.setEnabled(true);
            answer2.setEnabled(true);
            answer3.setEnabled(true);
            answer4.setEnabled(true);
        }else{
            initScoreActivity();
        }
    }
    private void StartCountDown(){
        this.countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
            timeLeftInMillis = 0;
            updateCountDownText();
            checkingAnswer(false,false);
            }
        }.start();
    }

    private void checkingAnswer(boolean answered, boolean checking){
        countDownTimer.cancel();
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
        showPopUp(answered, checking);
    }
    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        countDownTextView.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            countDownTextView.setTextColor(Color.RED);
        }
    }
    /**
     * Método que crea y muestra una ventana emergente al pulsar una respuesta
     *
     * @param correct boolean si la respuesta es correcta o no
     * @return Dialog
     */
    public Dialog showPopUp(boolean answered, boolean correct) {
        androidx.appcompat.app.AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        String popUpTitle = "", message = "";
        if(!answered) {
            popUpTitle = "TIMEOUT";
            message = "No has respondido a tiempo a la pregunta.\n";
        }else{
            if (this.player.getScore() > 0 && this.num_questions_count < this.total_questions) {
                if (correct) {
                    popUpTitle = getString(R.string.popUpCorrect);
                } else {
                    popUpTitle = getString(R.string.popUpIncorrect);
                }
            }else{
                if (this.player.getScore() > 0 && this.num_questions_count >= this.total_questions) {
                    popUpTitle = getString(R.string.finalTittle);
                    message = getString(R.string.popFinish);
                }else{
                    popUpTitle = getString(R.string.popUpOver);
                    message = getString(R.string.popOverr);
                }
            }
        }
            if (this.player.getScore() > 0 && this.num_questions_count < this.total_questions) {
                popUp.setTitle(popUpTitle)
                        .setMessage(message + "Tienes " + this.player.getScore() + " puntos. \n" + getString(R.string.answer_text))
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
            } else {
                popUp.setTitle(popUpTitle)
                        .setMessage(message)
                        .setNegativeButton(R.string.wrong_answer_exit, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                initScoreActivity();
                            }
                        });
            }

        return popUp.show();
    }

    /**
     * Este método inicia la actividad Final Score
     *
     */
    private void initScoreActivity(){
        Intent activity = new Intent(Game.this,FinalScore.class);
        activity.putExtra("score", this.player.getScore());
        startActivity(activity);
    }
    /**
     * Este método actualiza el score
     *
     */
    private void updateScore(){
        int score = this.player.getScore();
        if(score >0){
            this.scoreView.setText(""+score);

        }else{
            this.scoreView.setText("0");
        }
        this.questions_count.setText(this.num_questions_count+"/"+this.total_questions);
    }


    public ArrayList<Question> loadFileQuestions(int numquest, boolean withImages){

        try {
            InputStreamReader archivo = new InputStreamReader(openFileInput("questions.txt"));
            BufferedReader fin = new BufferedReader(archivo);
            String texto = fin.readLine() + "\n";
            String textocomp = "";

            while(texto != null){
                textocomp = textocomp + texto + "\n";
                texto = fin.readLine();
            }
            fin.close();
            archivo.close();
            TextView textView8 = findViewById(R.id.textViewprueba);
            textView8.setText(textocomp);
            Toast.makeText(this,"Archivo leido",Toast.LENGTH_SHORT).show();


            /*
            if (numquest <= this.list_questions.size()) {
                ArrayList<Question> arrayQuestions;
                Random rnd = new Random(System.currentTimeMillis() * 17000);
                Collections.shuffle(this.list_questions,rnd);
                if(withImages) {
                    arrayQuestions= new ArrayList<>(this.list_questions.subList(0, numquest));
                }else{
                    arrayQuestions = new ArrayList<>();
                    Iterator<Question> it = this.list_questions.iterator();
                    int cont = 0;
                    while(it.hasNext() && cont<numquest){
                        Question q = it.next();
                        if(!q.isImage()) {
                            arrayQuestions.add(q);
                            cont++;
                        }
                    }
                }
                Random rnd2 = new Random(System.currentTimeMillis() * 23000);
                Collections.shuffle(arrayQuestions,rnd2);
                return arrayQuestions;
            } else {
                System.err.println("Se ha producido un error. El número de preguntas almacenado es inferior al solicitado.");
                return null;
            }
            */

            return null;
        } catch (IOException e) {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
            return null;
        }

    }
}