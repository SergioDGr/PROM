package com.example.agenda;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.io.Serializable;

public class Database extends SQLiteOpenHelper implements Serializable {

    private static final String DATABASE_NAME="agenda.db";
    private static final int DATABASE_VERSION = 1;

    String sqlCreate = "CREATE TABLE Contactos (idContacto INTEGER PRIMARY KEY," +
            " nombre TEXT, apellidos TEXT, email TEXT, telefono INTEGER)";


    public Database( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Contactos");
        //Se crea la nueva version de la tabla
        db.execSQL(sqlCreate);
    }
}
