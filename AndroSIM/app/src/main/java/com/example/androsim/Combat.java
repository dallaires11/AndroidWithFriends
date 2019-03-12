package com.example.androsim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Combat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);
    }

    public void openRightDrawer(View view) {
        Log.i("Combat", "openRightDrawer");
    }

    public void openLeftDrawer(View view) {
        Log.i("Combat", "openLeftDrawer");
    }

    public void FaireBaseAttack(View view) {
        Log.i("Combat", "FaireBaseAttack");
    }

    public void SeReposer(View view) {
        Log.i("Combat", "SeReposer");
    }
}
