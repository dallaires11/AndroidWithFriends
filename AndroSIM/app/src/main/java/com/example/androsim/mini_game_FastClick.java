package com.example.androsim;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class mini_game_FastClick extends AppCompatActivity {

    private TextView txtTimer;
    private Button btnPress;
    private int count = 0;

    private CountDownTimer timer;
    private long timeleftmilli = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_game__fast_click);

        txtTimer = findViewById(R.id.txtTimer);
        btnPress = findViewById(R.id.btnPress);

        startTimer();
    }

    private void startTimer() {

        timer = new CountDownTimer(timeleftmilli, 1000) {
            @Override
            public void onTick(long l) {
                timeleftmilli = l / 1000;
                txtTimer.setText(Long.toString(timeleftmilli + 1));
            }

            @Override
            public void onFinish() {
                setResult(Activity.RESULT_OK,new Intent().putExtra("score", count));
                finish();
            }
        }.start();
    }


    public void UpdateCount(View view) {
        count += 5;
    }
}
