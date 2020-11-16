package com.code.fastquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Clase inicial de la aplicación
 *
 * @author Carlos González, Óscar Rivas
 */
public class MainActivity extends AppCompatActivity {

    private  RadioGroup question_group;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch have_images, switch1;
    private boolean isNightModeEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darkTheme);
        }else{
            setTheme(R.style.FastQuizTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.button_start_game);
        question_group = findViewById(R.id.radioGroup_question);
        have_images = findViewById(R.id.switch_images);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_fastquiz);
        TextView textView8 = findViewById(R.id.textViewprueba);
        textView8.setText("prueba");

        switch1=(Switch)findViewById(R.id.switch1);
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            switch1.setChecked(true);
        }
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                restart();
            }
        });

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

    public void restart(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    public void checkTheme(){


        //código que debe ir en ajustes
        SharedPreferences prefs = getSharedPreferences("NIGHT_MODE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("NIGHT_MODE", !this.isNightModeEnabled);
        editor.commit();
        //finish();
        checkTheme();

        /*
        SharedPreferences prefs = getSharedPreferences("NIGHT_MODE", Context.MODE_PRIVATE);

        this.isNightModeEnabled = prefs.getBoolean("NIGHT_MODE", false);

*/
/*
        if (!this.isNightModeEnabled) {
            Toast.makeText(getApplicationContext(), "LIGHT", Toast.LENGTH_SHORT).show();
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else {
            Toast.makeText(getApplicationContext(), "DARK", Toast.LENGTH_SHORT).show();
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }*/

        /*
        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
                Toast.makeText(getApplicationContext(),"SYSTEM", Toast.LENGTH_SHORT).show();
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                Toast.makeText(getApplicationContext(),"DARK", Toast.LENGTH_SHORT).show();
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case AppCompatDelegate.MODE_NIGHT_NO:
                Toast.makeText(getApplicationContext(),"LIGHT", Toast.LENGTH_SHORT).show();
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }
*/
    }

    public void initRankingActivity(View view){
        Intent activity = new Intent(MainActivity.this, Ranking.class);
        startActivity(activity);
    }

}