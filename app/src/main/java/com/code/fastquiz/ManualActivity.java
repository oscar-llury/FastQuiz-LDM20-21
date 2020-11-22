package com.code.fastquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

public class ManualActivity extends AppCompatActivity {
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
        SharedPreferences prefs = this.getSharedPreferences("FASTQUIZ_CONFIG", Context.MODE_PRIVATE);
        boolean isNightModeEnabled = prefs.getBoolean("NIGHT_MODE", false);

        if(isNightModeEnabled){
            setTheme(R.style.darkTheme);
        }else{
            setTheme(R.style.FastQuizTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        Toolbar toolb =findViewById(R.id.app_bar);
        setSupportActionBar(toolb);
        toolb.setNavigationIcon(R.mipmap.ic_fastquiz);
        ImageView image1 = findViewById(R.id.imageView_manual1);
        ImageView image2 = findViewById(R.id.imageView_manual2);
        ImageView image3 = findViewById(R.id.imageView_manual3);
        ImageView image4 = findViewById(R.id.imageView_manual4);
        ImageView image5 = findViewById(R.id.imageView_manual5);
        if(isNightModeEnabled){
            image1.setImageResource(R.drawable.manual1_dark);
            image2.setImageResource(R.drawable.manual2_dark);
            image3.setImageResource(R.drawable.manual3_dark);
            image4.setImageResource(R.drawable.manual4_dark);
            image5.setImageResource(R.drawable.manual5_dark);
        }
    }
}