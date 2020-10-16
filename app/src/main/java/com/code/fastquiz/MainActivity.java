package com.code.fastquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.button_start_game);
        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent activity = new Intent(getApplicationContext(),Game.class);
                startActivity(activity);
            }
        });


    }

}