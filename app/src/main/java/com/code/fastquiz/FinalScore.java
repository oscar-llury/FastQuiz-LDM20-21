package com.code.fastquiz;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Clase que muestra la puntuación final
 *
 * @author Carlos González, Óscar Rivas
 */
public class FinalScore extends AppCompatActivity {

    private AdminSQLiteOpenHelper dataBase_helper;
    private SQLiteDatabase dataBase;
    private EditText playerName;
    private boolean registered;
    private int playerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("NIGHT_MODE", Context.MODE_PRIVATE);
        boolean isNightModeEnabled = prefs.getBoolean("NIGHT_MODE", false);
        if(isNightModeEnabled){
            setTheme(R.style.darkTheme);
        }else{
            setTheme(R.style.FastQuizTheme);
        }
        super.onCreate(savedInstanceState);

        dataBase_helper = new AdminSQLiteOpenHelper(this, "fastQuiz_bbdd", null, 1);

        setContentView(R.layout.activity_final_score);
        TextView score = findViewById(R.id.score);
        registered = false;

        Intent mIntent = getIntent();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_fastquiz_round);
        int playerScore = mIntent.getIntExtra("score", 0);
        if(playerScore<=0){
            score.setText("0");
            playerScore = 0;
        }else{
            score.setText(Integer.toString(playerScore));
        }
    }

    public void registerScore(View view){
        //abrir la base de datos modo escritura y lectura
        dataBase = dataBase_helper.getWritableDatabase();
        playerName = findViewById(R.id.playerName);
        String nombre = playerName.getText().toString();

        if (!nombre.isEmpty() & !registered){
            //permite almacenar las columnas del registro en pares clave-valor
            ContentValues registro = new ContentValues();
            //Añade los pares
            registro.put("name", nombre);
            registro.put("score", playerScore);

            dataBase.insert("ranking", null, registro);
            dataBase.close();

            playerName.setText("");
            registered=true;

        }else{
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Debes rellenar el nombre", Toast.LENGTH_SHORT).show();
            }
            if (registered){
                Toast.makeText(this, "Ya te has registrado", Toast.LENGTH_SHORT).show();
            }
        }
        dataBase.close();
    }

    public void restartGame(View view){
        Intent activity = new Intent(FinalScore.this, MainActivity.class);
        startActivity(activity);
    }

    public void initRankingActivity(View view){
        Intent activity = new Intent(FinalScore.this, Ranking.class);
        startActivity(activity);
    }

}