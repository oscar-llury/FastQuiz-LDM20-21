package com.code.fastquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;


public class Ranking extends AppCompatActivity {

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
        setContentView(R.layout.activity_ranking);
        ListView rankingListView = findViewById(R.id.rankingListView);
        Toolbar toolb =findViewById(R.id.app_bar);
        setSupportActionBar(toolb);
        toolb.setNavigationIcon(R.mipmap.ic_fastquiz);
        AdminSQLiteOpenHelper dataBase_helper = new AdminSQLiteOpenHelper(this, "fastQuiz_bbdd", null, 1);
        SQLiteDatabase dataBase = dataBase_helper.getWritableDatabase();
        Cursor cursor = dataBase.rawQuery("select * from ranking  order by score DESC",null);

        ArrayList<Player> ranking = new ArrayList<>();
        Player player;

        while(cursor.moveToNext()){
            player = new Player();
            player.setName(cursor.getString(0));
            player.setScore(Integer.parseInt(cursor.getString(1)));

            ranking.add(player);
        }
        dataBase.close();

        rankingListView.setAdapter(new ranking_item_layout(this, ranking));
    }

    public void restartGame(View view){
        Intent activity = new Intent(Ranking.this, MainActivity.class);
        startActivity(activity);
    }
}