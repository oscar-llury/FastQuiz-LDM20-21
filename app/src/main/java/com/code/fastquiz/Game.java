package com.code.fastquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 * Clase para desarrollar el juego
 *
 * @author Carlos González, Óscar Rivas
 */
public class Game extends AppCompatActivity {

    private int total_questions, num_questions_count,correctSound,incorrectSound;
    private Button answer1, answer2, answer3, answer4;
    private Question question_to_show;
    private ArrayList<Question> arrayQuestions;
    private boolean checking, questions_with_images, minuteIsPlaying, isDarkMode;
    private TextView question, countDownTextView, scoreView, questions_count;
    private Player player;
    private ImageView imageView_question;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private static final long COUNTDOWN_IN_MILLIS = 15000;
    private static MediaPlayer mpSecconds;
    private static SoundPool spAnswer_correct,spAnswer_incorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("FASTQUIZ_CONFIG", Context.MODE_PRIVATE);
        this.isDarkMode = prefs.getBoolean("NIGHT_MODE", false);
        if (isDarkMode) {
            setTheme(R.style.darkTheme);
            this.isDarkMode=true;
        } else {
            setTheme(R.style.FastQuizTheme);
            this.isDarkMode=false;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent mIntent = getIntent();
        int playerMode = mIntent.getIntExtra("mode", 0);
        int images = mIntent.getIntExtra("images", 0);

        Toolbar toolb = findViewById(R.id.app_bar);
        setSupportActionBar(toolb);
        toolb.setNavigationIcon(R.mipmap.ic_fastquiz);

        this.questions_with_images = images == 1;
        QuestionRepositoryHelper qrh = new QuestionRepositoryHelper();
        this.num_questions_count = 0;
        this.arrayQuestions = qrh.readQuestionRepository(loadJSONFromAsset(), this.questions_with_images);

        if (playerMode == 1) {
            this.total_questions = 5;
        } else if (playerMode == 2) {
            this.total_questions = this.arrayQuestions.size();
        }


        if (!this.questions_with_images && playerMode == 2) {
            this.arrayQuestions.size();
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
        spAnswer_correct = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        correctSound = spAnswer_correct.load(this, R.raw.correcto,1);
        spAnswer_incorrect = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        incorrectSound = spAnswer_incorrect.load(this, R.raw.incorrecto,1);

    }
    @Override
    public void onStart() {
        super.onStart();
        this.minuteIsPlaying=false;
        if(this.isDarkMode)
            this.countDownTextView.setTextColor(getResources().getColor(R.color.primaryTextColor_dark));
        else
            this.countDownTextView.setTextColor(getResources().getColor(R.color.primaryTextColor_light));
        updateScore();
        playGame();
    }

    /**
     * Este método se ejecuta cuando el usuario pulsa una respuesta
     *
     * @param v View
     */
    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_answer1: {
                checking = question_to_show.checkCorrectAnswer(1);
                if (checking) {
                    answer1.setBackgroundColor(getResources().getColor(R.color.answerCorrect));
                } else {
                    answer1.setBackgroundColor(getResources().getColor(R.color.answerIncorrect));
                }
                break;
            }
            case R.id.button_answer2: {
                checking = question_to_show.checkCorrectAnswer(2);
                if (checking) {
                    answer2.setBackgroundColor(getResources().getColor(R.color.answerCorrect));
                } else {
                    answer2.setBackgroundColor(getResources().getColor(R.color.answerIncorrect));
                }
                break;
            }
            case R.id.button_answer3: {
                checking = question_to_show.checkCorrectAnswer(3);
                if (checking) {
                    answer3.setBackgroundColor(getResources().getColor(R.color.answerCorrect));
                } else {
                    answer3.setBackgroundColor(getResources().getColor(R.color.answerIncorrect));
                }
                break;
            }
            case R.id.button_answer4: {

                checking = question_to_show.checkCorrectAnswer(4);
                if (checking) {
                    answer4.setBackgroundColor(getResources().getColor(R.color.answerCorrect));
                } else {
                    answer4.setBackgroundColor(getResources().getColor(R.color.answerIncorrect));
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

            answer1.setBackgroundColor(getResources().getColor(R.color.primaryDarkColor));
            answer2.setBackgroundColor(getResources().getColor(R.color.primaryDarkColor));
            answer3.setBackgroundColor(getResources().getColor(R.color.primaryDarkColor));
            answer4.setBackgroundColor(getResources().getColor(R.color.primaryDarkColor));
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

    /**
     * Método que inicializa la cuenta atrás de las preguntas
     *
     */
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

    /**
     * Método que se ejecuta cuando el jugador pulsa un botón de respuesta.
     * Actualiza la información del score, para el timer y reproduce el sonido de respuesta
     * correcta o incorrecta.
     *
     */
    private void checkingAnswer(boolean answered, boolean checking){
        countDownTimer.cancel();
        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
        answer4.setEnabled(false);

        this.num_questions_count ++;
        this.arrayQuestions.remove(question_to_show);

        if (timeLeftInMillis < 10000) {
            mpSecconds.stop();
            mpSecconds.reset();
        }

        if(checking){
            spAnswer_correct.play(correctSound,1,1,1,0,0);
            this.player.increaseScore();
        }else{
            spAnswer_incorrect.play(incorrectSound,1,1,1,0,0);
            this.player.decreaseScore();
        }
        updateScore();
        showPopUp(answered, checking);
    }

    /**
     * Método que inicia el sonido de la cuenta atrás cuando se alcanzan 10 segundos restantes
     * Actualiza la información del texto del contador.
     *
     */
    private void updateCountDownText() {
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d", seconds);
        countDownTextView.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            if(!this.minuteIsPlaying) {
                this.minuteIsPlaying=true;
                mpSecconds = MediaPlayer.create(this, R.raw.minute);
                mpSecconds.start();
                this.countDownTextView.setTextColor(getResources().getColor(R.color.answerIncorrect));
            }
            if (timeLeftInMillis == 0 && mpSecconds.isPlaying()){
                mpSecconds.stop();
                mpSecconds.reset();
                spAnswer_incorrect.play(incorrectSound,1,1,1,0,0);
            }
        }
    }

    /**
     * Método que crea y muestra una ventana emergente al pulsar una respuesta
     *
     * @param correct boolean si la respuesta es correcta o no
     */
    private void showPopUp(boolean answered, boolean correct) {
        androidx.appcompat.app.AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        String popUpTitle, message = "";
        if(!answered) {
            popUpTitle = getString(R.string.popTimeOut);
            message = getString(R.string.popTimeOut_message);
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
                            onStart();
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

        popUp.show();
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

    /**
     * Este método lee el archivo JSON
     *
     */
    private String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getApplicationContext().getAssets().open("questionRepository.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}