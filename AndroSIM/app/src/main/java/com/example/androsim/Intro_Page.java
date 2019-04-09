package com.example.androsim;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Intro_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro__page);
    }

    public void StartGame(View view) {
        Log.i("Intro", "btnImage");
        Intent startIntent = new Intent(getApplicationContext(), menu_principale.class);
        startActivity(startIntent);
    }


}
