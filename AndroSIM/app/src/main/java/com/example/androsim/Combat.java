package com.example.androsim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.androsim.Model.Monstre;
import com.example.androsim.Model.Player;

public class Combat extends AppCompatActivity {

    Player player;
    Monstre monstre;
    ProgressBar viePlayer,manaPlayer,vieMonstre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);

        if(savedInstanceState == null){
            player = new Player();

            viePlayer = (ProgressBar) findViewById(R.id.progBarHP);
            manaPlayer = (ProgressBar) findViewById(R.id.progBarMana);
            viePlayer.setMax(player.getVieMax());
            viePlayer.setMin(0);
            manaPlayer.setMax(player.getManaMax());
            manaPlayer.setMin(0);
            viePlayer.setProgress(player.getVieMax());
            manaPlayer.setProgress(player.getManaMax());
        }
        else{
            loadPlayer(savedInstanceState);
        }

        monstre = new Monstre();

        vieMonstre = (ProgressBar) findViewById(R.id.progBarHpMonstre);
        vieMonstre.setMax(monstre.getVieMax());
        vieMonstre.setMin(0);
        vieMonstre.setProgress(monstre.getVieMax());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        loadPlayer(savedInstanceState);
        loadMonstre(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("viePlayer",player.getVie());
        outState.putInt("manaPlayer",player.getMana());
        outState.putInt("vieMonstre",monstre.getVie());
    }

    public void openRightDrawer(View view) {
        Log.i("Combat", "openRightDrawer");
    }

    public void openLeftDrawer(View view) {
        Log.i("Combat", "openLeftDrawer");
    }

    public void FaireBaseAttack(View view) {
        Log.i("Combat", "FaireBaseAttack");
        player.mangerDegat(monstre.attaque());
        viePlayer.setProgress(player.getVie(),true);
        monstre.mangerDegat(player.attaque());
        vieMonstre.setProgress(monstre.getVie(),true);

    }

    public void SeReposer(View view) {
        Log.i("Combat", "SeReposer");
        viePlayer = (ProgressBar) findViewById(R.id.progBarHP);
        player.repos();
        player.mangerDegat(monstre.attaque());
        viePlayer.setProgress(player.getVie(),true);

    }

    private void loadPlayer(Bundle savedInstanceState){
        player = new Player(savedInstanceState.getInt("viePlayer"),savedInstanceState.getInt("manaPlayer"));
        viePlayer = (ProgressBar) findViewById(R.id.progBarHP);
        viePlayer.setMax(player.getVieMax());
        viePlayer.setMin(0);
        viePlayer.setProgress(savedInstanceState.getInt("viePlayer"));
        manaPlayer = (ProgressBar) findViewById(R.id.progBarMana);
        manaPlayer.setMax(player.getManaMax());
        manaPlayer.setMin(0);
        manaPlayer.setProgress(savedInstanceState.getInt("manaPlayer"));

    }

    private void loadMonstre(Bundle savedInstanceState){
        monstre = new Monstre(savedInstanceState.getInt("vieMonstre"));
        vieMonstre = (ProgressBar) findViewById(R.id.progBarHpMonstre);
        vieMonstre.setMax(monstre.getVieMax());
        vieMonstre.setMin(0);
        vieMonstre.setProgress(savedInstanceState.getInt("viePlayer"));

    }
}