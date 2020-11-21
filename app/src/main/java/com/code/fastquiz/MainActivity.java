package com.code.fastquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Clase inicial de la aplicación
 *
 * @author Carlos González, Óscar Rivas
 */
public class MainActivity extends AppCompatActivity {

    private  RadioGroup question_group;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch have_images;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.help:
                Intent manual = new Intent(this, ManualActivity.class);
                startActivity(manual);
                break;
            case R.id.config:
                    Intent config = new Intent(this, ConfigActivity.class);
                    startActivity(config);
                    break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("FASTQUIZ_CONFIG", Context.MODE_PRIVATE);
        boolean isNightModeEnabled = prefs.getBoolean("NIGHT_MODE", false);
        if(isNightModeEnabled){
            setTheme(R.style.darkTheme);
        }else{
            setTheme(R.style.FastQuizTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.button_start_game);
        question_group = findViewById(R.id.radioGroup_question);
        have_images = findViewById(R.id.switch_images);
        Toolbar toolb =findViewById(R.id.app_bar);
        setSupportActionBar(toolb);
        toolb.setNavigationIcon(R.mipmap.ic_fastquiz);
        TextView textView8 = findViewById(R.id.textViewprueba);

        boolean first_time = prefs.getBoolean("FIRST_TIME", true);
        if(first_time){
            Toast.makeText(this, "first time", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("FIRST_TIME", false);
            editor.apply();
            createFileQuestions();
        }

        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                int selectedRadioButt = question_group.getCheckedRadioButtonId();

                int mode;
                if(selectedRadioButt==R.id.radioButton_5){
                    mode = 1;
                }else if (selectedRadioButt==R.id.radioButton_all){
                    mode = 2;
                }else{
                    mode = 0;
                }

                int images;
                if(have_images.isChecked()){
                    images = 1;
                }else{
                    images = 0;
                }

                Intent activity = new Intent(MainActivity.this, Game.class);
                activity.putExtra("mode", mode);
                activity.putExtra("images", images);
                startActivity(activity);
            }
        });
    }

    public void initRankingActivity(View view){
        Intent activity = new Intent(MainActivity.this, Ranking.class);
        startActivity(activity);
    }

    public void createFileQuestions(){

        try {
            //callFileOutput();
            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            openFileOutput("questions.txt", Context.MODE_PRIVATE));
            fout.write("¿Cuál es la capital de Mongolia?" + "\n");
            fout.write("Estambul" + "\n");
            fout.write("Ulan Bator" + "\n");
            fout.write("Madrid" + "\n");
            fout.write("Tokio" + "\n");
            fout.flush();
            fout.close();
            Toast.makeText(this,"Archivo escrito",Toast.LENGTH_SHORT).show();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            Log.e("Ficheros", "Error al escribir fichero en mem.int");
            Toast.makeText(this,"Error en el archivo",Toast.LENGTH_SHORT).show();
        }
    }

}