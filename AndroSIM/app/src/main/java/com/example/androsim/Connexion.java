package com.example.androsim;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Connexion extends AppCompatActivity implements View.OnClickListener {

    private Button button_connexion;
    private Button button_ajout_compte;
    private EditText edtNDC;
    private EditText edtPassword;
    DatabaseHelper db;
    SQLiteDatabase test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        db = new DatabaseHelper(this);
        //db.onUpgrade(test,1,2);
        Cursor cursor = db.alldata();
        while (cursor.moveToNext()){
            Toast.makeText(getApplicationContext(),"ID : "+cursor.getString(1),Toast.LENGTH_SHORT).show();
        }

        button_connexion = findViewById(R.id.button_connexion);
        button_ajout_compte = findViewById(R.id.button_ajout_compte );

        edtNDC = findViewById(R.id.nomdecompte);
        edtPassword = findViewById(R.id.motdepasse);

        final DatabaseHelper dbHelper = new DatabaseHelper(this);


        button_ajout_compte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyValidation()) {
                    dbHelper.addUser(new User(edtNDC.getText().toString(), edtPassword.getText().toString()/*,800,200)*/));
                    Toast.makeText(Connexion.this, "Ajout réussi", Toast.LENGTH_SHORT).show();
                    edtNDC.setText("");
                    edtPassword.setText("");
                }else{
                    Toast.makeText(Connexion.this, "Champs vide", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyValidation()) {
                    User user = dbHelper.queryUser(edtNDC.getText().toString(), edtPassword.getText().toString());
                    if (user != null) {
                        Bundle mBundle = new Bundle();
                        //mBundle.putInt("user",user.getId());
                        Intent intent = new Intent(Connexion.this, Combat.class);
                        //int ID = user.getId();
                        intent.putExtra("NDC",user.getNDC());
                        //Toast.makeText(Connexion.this, "ndc " + user.getNDC(), Toast.LENGTH_SHORT).show();

                        //intent.putExtra("ID",ID);
                        startActivity(intent);
                       // Toast.makeText(Connexion.this, "Bienvenue " + user.getNDC(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Connexion.this, "Identifiant non valide", Toast.LENGTH_SHORT).show();
                        edtPassword.setText("");
                    }
                }else{
                    Toast.makeText(Connexion.this, "Champs vide", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View v){

    }
    private boolean emptyValidation() {
        if (TextUtils.isEmpty(edtNDC.getText().toString()) || TextUtils.isEmpty(edtPassword.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }
    /*public void menuPrincipale(View view) {
        Log.i("MenuPrincipale", "button_connexion");
        Intent startIntent = new Intent(getApplicationContext(), Combat.class);
        startActivity(startIntent);


    }*/

}
