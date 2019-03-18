package com.example.androsim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Combat extends AppCompatActivity {

    private static int REQUEST_GET_DAMAGE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_GET_DAMAGE && resultCode == Activity.RESULT_OK){
            int damage = data.getIntExtra("damage",0);
            ProgressBar monsterHealth = (ProgressBar)findViewById(R.id.progBarHpMonstre);
            Toast.makeText(this, "Damage : " + damage, Toast.LENGTH_SHORT).show();
            monsterHealth.setProgress(monsterHealth.getProgress() - damage);
        }
    }

    public void openRightDrawer(View view) {
        Log.i("Combat", "openRightDrawer");
    }

    public void openLeftDrawer(View view) {
        Log.i("Combat", "openLeftDrawer");
        Intent startIntent = new Intent(getApplicationContext(), mini_game_test.class);
        startActivityForResult(startIntent, REQUEST_GET_DAMAGE);
    }

    public void FaireBaseAttack(View view) {
        Log.i("Combat", "FaireBaseAttack");
    }

    public void SeReposer(View view) {
        Log.i("Combat", "SeReposer");
    }
}
