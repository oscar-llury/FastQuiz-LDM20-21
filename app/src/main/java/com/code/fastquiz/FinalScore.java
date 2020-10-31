package com.code.fastquiz;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalScore extends AppCompatActivity {
    private TextView score;
    private Player player;
    private Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);
        score = (TextView) findViewById(R.id.score);
        Intent mIntent = getIntent();
        int playerScore = mIntent.getIntExtra("score", 0);
        if(playerScore<=0)
            score.setText("0");
        else score.setText(Integer.toString(playerScore));

        menu = (Button) findViewById(R.id.button_start_game);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalScore.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}