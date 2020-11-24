package com.code.fastquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ConfigActivity extends AppCompatActivity {

    private RadioGroup radioGroup_config;
    private SharedPreferences prefs;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_config, menu);
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
            case R.id.goback:
                Intent atras = new Intent(this, MainActivity.class);
                startActivity(atras);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prefs = this.getSharedPreferences("FASTQUIZ_CONFIG", Context.MODE_PRIVATE);
        boolean isNightModeEnabled = prefs.getBoolean("NIGHT_MODE", false);

        if(isNightModeEnabled){
            setTheme(R.style.darkTheme);
        }else{
            setTheme(R.style.FastQuizTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        Toolbar toolb =findViewById(R.id.app_bar);
        setSupportActionBar(toolb);
        toolb.setNavigationIcon(R.mipmap.ic_fastquiz);

        Button button_aply_mode = findViewById(R.id.button_aply_mode);
        radioGroup_config = findViewById(R.id.radioGroup_config);
        RadioButton radioButton_Light = findViewById(R.id.radioButton_Light);
        RadioButton radioButton_Dark = findViewById(R.id.radioButton_Dark);

        if(isNightModeEnabled){
            radioButton_Dark.setChecked(true);
        }else{
            radioButton_Light.setChecked(true);

        }

        button_aply_mode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                int selectedRadioButt = radioGroup_config.getCheckedRadioButtonId();

                if(selectedRadioButt==R.id.radioButton_Light){
                    editor.putBoolean("NIGHT_MODE", false);
                }else if(selectedRadioButt==R.id.radioButton_Dark){
                    editor.putBoolean("NIGHT_MODE", true);
                }
                editor.apply();
                Intent i = new Intent(getApplicationContext(), ConfigActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

}
