package com.code.fastquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private  RadioGroup question_group;
    private Switch have_images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.button_start_game);
        question_group = (RadioGroup) findViewById(R.id.radioGroup_question);
        have_images = (Switch) findViewById(R.id.switch_images);

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

                Intent activity = new Intent(MainActivity.this,Game.class);
                activity.putExtra("mode", mode);
                activity.putExtra("images", images);
                startActivity(activity);
            }
        });


    }

}