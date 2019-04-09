package com.example.androsim;

public class DatabaseOptions {

    public static final String DB_NAME = "local.db";
    public static final int DB_VERSION = 3;

    public static final String USERS_TABLE = "users";

    public static final String ID = "id";
    public static final String NOMDECOMPTE = "ndc";
    public static final String PASSWORD = "password";
    public static final String POINTDEVIE = "pdv";
    public static final String MANA = "mana";

    public static final String CREATE_USERS_TABLE_ =
            "CREATE TABLE  " + USERS_TABLE + "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NOMDECOMPTE + " TEXT ," +
                    PASSWORD + " TEXT ,"+
                    POINTDEVIE +" INTEGER DEFAULT 400 ,"+
                    MANA +" INTEGER DEFAULT 100 ," +
                    "UNIQUE("+ NOMDECOMPTE+"));";

}