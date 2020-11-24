package com.code.fastquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

/**
 * Clase inicial de la aplicación
 *
 * @author Carlos González, Óscar Rivas
 */
public class MainActivity extends AppCompatActivity {

    private  RadioGroup question_group;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private SwitchCompat have_images;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Este método despliega el menú de opciones del action bar y redirige a la actividad deseada
     *
     */
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

}