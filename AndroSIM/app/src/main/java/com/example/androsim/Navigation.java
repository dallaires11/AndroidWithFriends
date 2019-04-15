package com.example.androsim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class Navigation extends AppCompatActivity {

    Button gauche,droite,centre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        gauche = (Button) findViewById(R.id.buttonNavLeft);
        centre = (Button) findViewById(R.id.buttonNavCenter);
        droite = (Button) findViewById(R.id.buttonNavRight);

        gauche.setOnClickListener(event -> {
            navigation();
            Log.i("Nav", "btnGauche");
        });
        centre.setOnClickListener(event -> {
            navigation();
            Log.i("Nav", "btnCentre");
        });
        droite.setOnClickListener(event -> {
            navigation();
            Log.i("Nav", "btnDroite");
        });
    }

    private void navigation(){

        resetBoutons();

        int boutonNonAcif = (int)(Math.random()*5)+1;
        switch (boutonNonAcif){
            case 1:
                gauche.setEnabled(false);
                break;
            case 2:
                centre.setEnabled(false);
                break;
            case 3:
                droite.setEnabled(false);
                break;
            case 4:
                rencontreEnemie();
                break;
            default:
                break; //Genere une salle a 3 voie
        }
    }

    void resetBoutons(){
        gauche.setEnabled(true);
        centre.setEnabled(true);
        droite.setEnabled(true);
    }

    void rencontreEnemie(){
        Log.i("Nav", "Entrer en ombat");
        Intent startIntent = new Intent(getApplicationContext(), Combat.class);
        startActivity(startIntent);
    }
}