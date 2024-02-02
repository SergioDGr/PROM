package com.example.examenev2_sergio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ExamenEV2.db";
    private static final int DATABASE_VERSION = 1;
    private String sqlCreateElementos = "CREATE TABLE Elementos (id INTEGER PRIMARY KEY," +
            "nombre TEXT, simbolo TEXT, num_atomico INTEGER, estado TEXT, libro_prestado INTEGER)" ;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateElementos);
        Elemento e1 = new Elemento(0, "HELIO", "He", 2, "GAS");
        Elemento e2 = new Elemento(0, "HIERRO", "Fe", 26,
                "SOLIDO");
        Elemento e3 = new Elemento(0, "MERCURIO", "Hg", 80,
                "LIQUIDO");
        insertarElemento(e1, db);
        insertarElemento(e2, db);
        insertarElemento(e3, db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Elementos");
        onCreate(db);
    }

    public void insertarElemento(Elemento e){
         insertarElemento(e, null);
    }

    public void insertarElemento(Elemento e, SQLiteDatabase db){
        if(db == null)
            db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", e.getNombre());
        values.put("simbolo", e.getSimbolo());
        values.put("num_atomico", e.getNum_atomico());
        values.put("estado", e.getEstado());
        db.insert("Elementos", null, values);
    }

    public void modificarElemento(Elemento e){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("simbolo", e.getSimbolo());
        values.put("num_atomico", e.getNum_atomico());
        values.put("estado", e.getEstado());
        db.update("Elementos", values, "nombre = ?", new String[]{e.getNombre()});
    }

    public void borrarElemento(String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Elementos", "nombre = ?", new String[]{
                String.valueOf(nombre)
        });
    }

    public Elemento buscarElemento(String nombre_elemento){
        Elemento e = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Elementos WHERE nombre = ?",
                new String[]{nombre_elemento});

        if(cursor.moveToFirst()){
            e = new Elemento();
            e.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            e.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
            e.setSimbolo(cursor.getString(cursor.getColumnIndexOrThrow("simbolo")));
            e.setNum_atomico(cursor.getInt(cursor.getColumnIndexOrThrow("num_atomico")));
            e.setEstado(cursor.getString(cursor.getColumnIndexOrThrow("estado")));
        }

        cursor.close();

        return e;
    }



}
