package com.code.fastquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        Toolbar toolb =findViewById(R.id.app_bar);
        setSupportActionBar(toolb);
        toolb.setNavigationIcon(R.mipmap.ic_fastquiz);
    }
}