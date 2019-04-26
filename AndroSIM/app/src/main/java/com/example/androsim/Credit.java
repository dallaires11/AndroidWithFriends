package com.example.androsim;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Credit extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.credit_theme);
        mp.seekTo(0);
        mp.start();
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
        mp = MediaPlayer.create(getApplicationContext(), R.raw.credit_theme);
        mp.seekTo(16000);
        mp.start();
    }
}