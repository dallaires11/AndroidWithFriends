package com.example.androsim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class menu_principale extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principale);

    }

    public void StartGame(View view) {
        Log.i("Menu", "btnPlay");
        Intent startIntent = new Intent(getApplicationContext(), Combat.class);
        startActivity(startIntent);
    }

    public void StartCredit(View view){
        Log.i("Menu", "btnCredit");
        Intent startIntent = new Intent(getApplicationContext(), credit.class);
        startActivity(startIntent);
    }
}

