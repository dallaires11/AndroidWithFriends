package com.example.androsim;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.androsim.Model.Monstre;
import com.example.androsim.Model.Player;

public class Combat extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Player player;
    Monstre monstre;
    ProgressBar viePlayer,manaPlayer,vieMonstre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);
        setDrawerCombat();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

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
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        setDrawerCombat();
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
        drawerLayout.openDrawer(GravityCompat.END);
        return;
    }

    public void openLeftDrawer(View view) {
        Log.i("Combat", "openLeftDrawer");
        drawerLayout.openDrawer(GravityCompat.START);
        return;
    }

    public void FaireBaseAttack(View view) {
        Log.i("Combat", "FaireBaseAttack");
        player.mangerDegat(monstre.attaque());
        viePlayer.setProgress(player.getVie(),true);
        monstre.mangerDegat(player.attaque());
        vieMonstre.setProgress(monstre.getVie(),true);

    }

    public void castSpell(int degat) {
        Log.i("Combat", "caster spell");
        player.mangerDegat(monstre.attaque());
        viePlayer.setProgress(player.getVie(),true);
        monstre.mangerDegat(degat);
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

    private void setDrawerCombat(){
        NavigationView navigationViewLeft = findViewById(R.id.nav_view_left);
        navigationViewLeft.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        NavigationView navigationViewRight = findViewById(R.id.nav_view_right);
        navigationViewRight.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
    }
}