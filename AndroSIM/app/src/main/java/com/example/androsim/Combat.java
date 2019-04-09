package com.example.androsim;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.androsim.Model.Monstre;
import com.example.androsim.Model.Player;

public class Combat extends AppCompatActivity {

    DrawerLayout drawerLayout;


    private static int REQUEST_GET_DAMAGE = 0;

    Player player;
    Monstre monstre;
    ProgressBar viePlayer,manaPlayer,vieMonstre;

    MenuItem spell1;
    String values;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);
        //Intent intent = getIntent();
        //int test = getIntent().getIntExtra("ID",0);
       // Toast.makeText(Combat.this, "test " + test, Toast.LENGTH_SHORT).show();
        Bundle extras = getIntent().getExtras();

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

        Toast.makeText(Combat.this, "test1234 " + salut, Toast.LENGTH_SHORT).show();

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
            String temp =db.selectPDV(values);
            int currentPDV= Integer.parseInt((temp));
            player.setVie(currentPDV);
            viePlayer.setProgress(currentPDV);
            temp = db.selectMana(values);
            int currentMana = Integer.parseInt((temp));
            player.setMAna(currentMana);
            manaPlayer.setProgress(currentMana);
            db.close();
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
        return;
    }

    public void openLeftDrawer(View view) {
        Log.i("Combat", "openLeftDrawer");

        drawerLayout.openDrawer(GravityCompat.START);
        return;

        //Intent startIntent = new Intent(getApplicationContext(), mini_game_test.class);
        //startActivityForResult(startIntent, REQUEST_GET_DAMAGE);

    }

    public void FaireBaseAttack(View view) {
        Log.i("Combat", "FaireBaseAttack");
        player.mangerDegat(monstre.attaque());
        viePlayer.setProgress(player.getVie(),true);
        monstre.mangerDegat(player.attaque());
        vieMonstre.setProgress(monstre.getVie(),true);
        DatabaseHelper dbHelper= new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pdv",player.getVie());
        //db.update("users",values,"ndc= ? ",new String[]{"steven"});
        dbHelper.updatePDV(player.getVie(),"steven");
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
        spell1 = (MenuItem) findViewById(R.id.spell1);
        /*spell1.setOnMenuItemClickListener(a -> {
            Intent startIntent = new Intent(getApplicationContext(), mini_game_test.class);
            startActivityForResult(startIntent, REQUEST_GET_DAMAGE);
            return true;
        });*/

        NavigationView navigationViewLeft = findViewById(R.id.nav_view_left);
        navigationViewLeft.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        //menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        switch (menuItem.getItemId()) {
                            case R.id.spell1:
                                Intent startIntent = new Intent(getApplicationContext(), mini_game_test.class);
                                startActivityForResult(startIntent, REQUEST_GET_DAMAGE);
                                break;
                        }

                        return true;
                    }
                });

        NavigationView navigationViewRight = findViewById(R.id.nav_view_right);
        navigationViewRight.setNavigationItemSelectedListener(
                menuItem -> {
                    // set item as selected to persist highlight
                    //menuItem.setChecked(true);
                    // close drawer when item is tapped
                    drawerLayout.closeDrawers();

                    /*switch (menuItem.getItemId()) {
                        case R.id.spell1:
                            Intent startIntent = new Intent(getApplicationContext(), mini_game_test.class);
                            startActivityForResult(startIntent, REQUEST_GET_DAMAGE);
                            break;
                    }*/
                    /*spell1.setOnMenuItemClickListener(a -> {
                        //Intent startIntent = new Intent(getApplicationContext(), mini_game_test.class);
                        //startActivityForResult(startIntent, REQUEST_GET_DAMAGE);
                        return true;
                    });*/


                    // Add code here to update the UI based on the item selected
                    // For example, swap UI fragments here

                    return true;
                });
    }
}