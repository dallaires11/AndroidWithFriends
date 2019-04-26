package com.example.androsim;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class menu_principale extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principale);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.main_menu_theme);
        mp.seekTo(16000);
        mp.start();

    }

    public void StartGame(View view) {
        Log.i("Menu", "btnPlay");
        mp.release();
        Intent startIntent = new Intent(getApplicationContext(), Connexion.class);
        startActivity(startIntent);
    }

    public void StartCredit(View view){
        Log.i("Menu", "btnCredit");
        mp.release();
        Intent startIntent = new Intent(getApplicationContext(), Credit.class);
        startActivity(startIntent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.release();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mp = MediaPlayer.create(getApplicationContext(), R.raw.main_menu_theme);
        mp.seekTo(16000);
        mp.start();
    }
}