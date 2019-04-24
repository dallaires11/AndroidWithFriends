package com.example.androsim;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.androsim.Model.Monstre;
import com.example.androsim.Model.Player;

public class Combat extends AppCompatActivity {

    private static final int REQUEST_GET_DAMAGE = 0;
    private static final int REQUEST_CODE = 1;
    private static final int SNEAK_ATTACK_REQUEST = 2;

    Player player;
    Monstre monstre;
    ProgressBar viePlayer,manaPlayer,vieMonstre;
    DrawerLayout drawerLayout;
    MenuItem spell1;
    String values;
    DatabaseHelper db;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.sound_file_1);
        //Intent intent = getIntent();
        //int test = getIntent().getIntExtra("ID",0);
       // Toast.makeText(Combat.this, "test " + test, Toast.LENGTH_SHORT).show();
        /*Bundle extras = getIntent().getExtras();

        if(extras !=null){
            values = extras.getString("NDC");

        }
        db = new DatabaseHelper(this);
        String salut= db.selectPDV(values);
        /*Cursor cursor = db.selectPDV(values);
        while (cursor.moveToNext()){
            Toast.makeText(getApplicationContext(),"PDV : "+cursor.getString(2),Toast.LENGTH_SHORT).show();
        }*/
       // String test = cursor.getString(1);
       // Toast.makeText(Combat.this, "test123 " + test, Toast.LENGTH_SHORT).show();
/*
        Toast.makeText(Combat.this, "test1234 " + salut, Toast.LENGTH_SHORT).show();
        */
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
            viePlayer.setProgress(player.getVie());
            manaPlayer.setProgress(player.getMana());
            //String temp =db.selectPDV(values);
            //int currentPDV= Integer.parseInt((temp));
            //player.setVie(currentPDV);
            //viePlayer.setProgress(currentPDV);
            //temp = db.selectMana(values);
            //int currentMana = Integer.parseInt((temp));
            //player.setMAna(currentMana);
            //manaPlayer.setProgress(currentMana);
            //db.close();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_GET_DAMAGE && resultCode == Activity.RESULT_OK){
            int damage = data.getIntExtra("damage",0);
            Toast.makeText(this, "Damage : " + damage, Toast.LENGTH_SHORT).show();
            if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
                CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                String cameraId = null; // Usually back camera is at 0 position.
                for(int x = 0; x<9; x++) {
                    try {
                        cameraId = camManager.getCameraIdList()[0];
                        camManager.setTorchMode(cameraId, true);   //Turn ON
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }

                    try {
                        camManager.setTorchMode(cameraId, false); //Turn OFF
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            mp.seekTo(0);
            mp.start();
            castSpell(damage,10);
        }
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            int score = data.getIntExtra("score", 0);
            Toast.makeText(this,"Damage : " + score, Toast.LENGTH_SHORT).show();
            castSpell(score, 10);
        }
        if(requestCode == SNEAK_ATTACK_REQUEST && resultCode == Activity.RESULT_OK) {
            int damage = data.getIntExtra("damage", 0);
            Toast.makeText(this, "Damage : " + damage, Toast.LENGTH_SHORT).show();
            castSpell(damage,10);
        }

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
    }

    public void openLeftDrawer(View view) {
        Log.i("Combat", "openLeftDrawer");
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void FaireBaseAttack(View view) {
        Log.i("Combat", "FaireBaseAttack");
        player.mangerDegat(monstre.attaque());
        viePlayer.setProgress(player.getVie(),true);
        monstre.mangerDegat(player.attaque());
        vieMonstre.setProgress(monstre.getVie(),true);
        /*DatabaseHelper dbHelper= new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pdv",player.getVie());
        //db.update("users",values,"ndc= ? ",new String[]{"steven"});
        dbHelper.updatePDV(player.getVie(),"steven");*/
    }

    public void castSpell(int degat,int mana) {
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
                menuItem -> {
                    drawerLayout.closeDrawers();

                    switch (menuItem.getItemId()) {
                        case R.id.spell1:
                            Intent Intent1 = new Intent(getApplicationContext(), mini_game_test.class);
                            startActivityForResult(Intent1, REQUEST_GET_DAMAGE);
                            break;

                        case R.id.spell2:
                            Intent Intent2 = new Intent(getApplicationContext(), mini_game_FastClick.class);
                            startActivityForResult(Intent2,REQUEST_CODE);
                            break;
                        case R.id.spell3:
                            Intent Intent3 = new Intent(getApplicationContext(), SneakAttack.class);
                            startActivityForResult(Intent3,SNEAK_ATTACK_REQUEST);
                            break;
                    }

                    return true;
                });

        NavigationView navigationViewRight = findViewById(R.id.nav_view_right);
        navigationViewRight.setNavigationItemSelectedListener(
                menuItem -> {
                    drawerLayout.closeDrawers();
                    return true;
                });
    }
}