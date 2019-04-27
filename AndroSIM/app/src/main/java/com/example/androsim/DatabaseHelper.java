package com.example.androsim;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, DatabaseOptions.DB_NAME, null, DatabaseOptions.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table
        db.execSQL(DatabaseOptions.CREATE_USERS_TABLE_);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseOptions.USERS_TABLE);
        // Create tables again
        onCreate(db);
    }

    public User queryUser(String ndc, String password/*, String pdv, String mana*/) {

        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        Cursor cursor = db.query(DatabaseOptions.USERS_TABLE, new String[]{DatabaseOptions.ID,
                        DatabaseOptions.NOMDECOMPTE, DatabaseOptions.PASSWORD/*, DatabaseOptions.POINTDEVIE, DatabaseOptions.MANA*/},
                DatabaseOptions.NOMDECOMPTE + "=? and " + DatabaseOptions.PASSWORD + "=?"/* and"+ DatabaseOptions.POINTDEVIE+
                        "=? and "+DatabaseOptions.MANA + "=? "*/,
                new String[]{ndc, password/*,pdv,mana*/}, null, null, null, "1");
        if (cursor != null)
            cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            user = new User(cursor.getString(1), cursor.getString(2)/*,cursor.getInt(3),cursor.getInt(4)*/);
        }
        // return user
        return user;
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseOptions.NOMDECOMPTE, user.getNDC());
        values.put(DatabaseOptions.PASSWORD, user.getPassword());
        //values.put(DatabaseOptions.POINTDEVIE,user.getPDV());
        //values.put(DatabaseOptions.MANA,user.getMana());

        // Inserting Row
        db.insert(DatabaseOptions.USERS_TABLE, null, values);
        //db.close(); // Closing database connection


        //db.execSQL("INSERT INTO user values (20,'steven','steven',800,200); ");
    }

    public Cursor alldata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DatabaseOptions.USERS_TABLE, null);
        return cursor;

    }

    public String selectPDV(String where) {
        String test;
        //int test=0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select *from " + DatabaseOptions.USERS_TABLE +
                " where " + DatabaseOptions.NOMDECOMPTE + "='" + where + "';", null);
        cursor.moveToFirst();
        test= cursor.getString((3));
        Log.i("BDSD",test + " NDC + "+ where);
        return test;

    }
    public String selectMana(String where) {
        String test;
        //int test=0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select *from " + DatabaseOptions.USERS_TABLE +
                " where " + DatabaseOptions.NOMDECOMPTE + "='" + where + "';", null);
        cursor.moveToFirst();
        test= cursor.getString((4));
        return test;

    }

    public void updatePDV(int pdv,String ndc){
        String[] whereArgs= new String[]{
                ndc
        };
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("BDSD","REQUETE " + "UPDATE "+ DatabaseOptions.USERS_TABLE+ " SET "+DatabaseOptions.POINTDEVIE+" = "+pdv +  " WHERE "+
                DatabaseOptions.NOMDECOMPTE + "=?"+whereArgs[0] );
        Cursor cursor = db.rawQuery("UPDATE "+ DatabaseOptions.USERS_TABLE+ " SET "+DatabaseOptions.POINTDEVIE+" = "+ pdv  +  " WHERE "+
                DatabaseOptions.NOMDECOMPTE + "=?",whereArgs );

        cursor.moveToFirst();
    }

    public void updateMana(int mana,String ndc){
        String[] whereArgs= new String[]{
                ndc
        };
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("BDSD","REQUETE " + "UPDATE "+ DatabaseOptions.USERS_TABLE+ " SET "+DatabaseOptions.MANA+" = "+mana +  " WHERE "+
                DatabaseOptions.NOMDECOMPTE + "=?"+whereArgs[0] );
        Cursor cursor = db.rawQuery("UPDATE "+ DatabaseOptions.USERS_TABLE+ " SET "+DatabaseOptions.MANA+" = "+ mana  +  " WHERE "+
                DatabaseOptions.NOMDECOMPTE + "=?",whereArgs );

        cursor.moveToFirst();
    }
    public void safeDonneesPersonnage(int pdv, int mana,String ndc){
        updateMana(mana,ndc);
        updatePDV(pdv,ndc);

    }
    /*public Cursor getPDV(String condition) {
        SQLiteDatabase db = this.getWritableDatabase();
        String from[] = {DatabaseOptions.CREATE_USERS_TABLE_};
        String where = DatabaseOptions.NOMDECOMPTE + "=?";
        String[] whereArg = new String[]{condition + ""};
        Cursor cursor = db.query(true, DatabaseOptions.POINTDEVIE, from, where, whereArg, null, null, null, null);
        return cursor;
    }*/
}