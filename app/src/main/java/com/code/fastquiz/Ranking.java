package com.code.fastquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class Ranking extends AppCompatActivity {

    private AdminSQLiteOpenHelper dataBase_helper;
    private SQLiteDatabase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        ListView rankingListView = findViewById(R.id.rankingListView);

        dataBase_helper = new AdminSQLiteOpenHelper(this, "fastQuiz_bbdd", null, 1);
        dataBase = dataBase_helper.getWritableDatabase();
        Cursor cursor = dataBase.rawQuery("select * from ranking  order by score DESC",null);

        ArrayList<Player> ranking = new ArrayList<Player>();
        Player player = null;

        while(cursor.moveToNext()){
            player = new Player();
            player.setName(cursor.getString(0));
            player.setScore(Integer.parseInt(cursor.getString(1)));

            ranking.add(player);
        }
        dataBase.close();
/*
        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listPlayers);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.ranking_item_layout, listPlayers);
        rankingListView.setAdapter(adapter);
*/
        rankingListView.setAdapter(new ranking_item_layout(this, ranking));
    }

    public void restartGame(View view){
        Intent activity = new Intent(Ranking.this, MainActivity.class);
        startActivity(activity);
    }
}