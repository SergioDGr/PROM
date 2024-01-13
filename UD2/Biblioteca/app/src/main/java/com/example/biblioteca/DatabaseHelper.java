package com.example.biblioteca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Biblioteca.db";
    private static final int DATABASE_VERSION = 1;
    private String sqlCreateUser = "CREATE TABLE Usuarios (idUsuario INTEGER PRIMARY KEY," +
            "usuario TEXT, password TEXT, libro_prestado INTEGER)" ;
    private String sqlCreateLibros = "CREATE TABLE Libros (idLibros INTEGER PRIMARY KEY," +
            "titulo TEXT, descripcion TEXT, prestado INTEGER)";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateLibros);
        db.execSQL(sqlCreateUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Libros");
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        onCreate(db);
    }

    public void insertarUsuario(Usuario usuario){
        if(validarUsuario(usuario).equals("Existe el usuario"))
            return;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usuario", usuario.getUsuario());
        values.put("password", usuario.getPass());
        db.insert("Usuarios", null, values);
    }

    public String validarUsuario(Usuario usuario){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Usuarios", null);
        if (cursor.moveToFirst()) {
            do {
                Usuario user = new Usuario(
                        cursor.getString(cursor.getColumnIndexOrThrow("usuario")),
                        cursor.getString(cursor.getColumnIndexOrThrow("password"))
                );
                if(usuario.equals(user)) {
                    usuario.setId(cursor.getInt(cursor.getColumnIndexOrThrow("idUsuario")));
                    usuario.setLibro_prestado(cursor.getInt(
                            cursor.getColumnIndexOrThrow("libro_prestado")));
                    cursor.close();
                    return "Las credenciales son correctas";
                }
                if(usuario.getUsuario().equals(user.getUsuario())) {
                    cursor.close();
                    return "Existe el usuario";
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        return "No existe el usuario";
    }

    public List<Libro> getLibros(){
        List<Libro> lstLibros = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Libros", null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("idLibros"));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
                String descripcion = cursor.getString(
                        cursor.getColumnIndexOrThrow("descripcion"));
                int prestado = cursor.getInt(cursor.getColumnIndexOrThrow("prestado"));

                Libro l = new Libro(id, titulo, descripcion, prestado == 1);
                lstLibros.add(l);
            }while (cursor.moveToNext());
        }
        cursor.close();

        return lstLibros;
    }

    public Libro getLibro(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Libros WHERE idLibros = ?",
                new String[]{String.valueOf(id)});
        if(cursor.moveToFirst()){
            if(cursor.getInt(cursor.getColumnIndexOrThrow("idLibros")) == id){
                return new Libro(id,
                        cursor.getString(cursor.getColumnIndexOrThrow("titulo")),
                        cursor.getString(cursor.getColumnIndexOrThrow("descripcion")),
                        cursor.getInt(cursor.getColumnIndexOrThrow(
                                "prestado")) == 1);
            }
        }
        cursor.close();
        return null;
    }

    public void insertarLibro(Libro libro){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", libro.getTitulo());
        values.put("descripcion", libro.getDescripcion());
        values.put("prestado", 0);
        db.insert("Libros", null, values);
    }

    public void editarLibro(Libro libro, Usuario usuario) throws Exception {
        Libro libro_original = getLibro(libro.getId());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", libro.getTitulo());
        values.put("descripcion", libro.getDescripcion());

        if(libro.isPrestado() != libro_original.isPrestado()) {
            values.put("prestado", libro.isPrestado());
            ContentValues values2 = new ContentValues();
            if(libro_original.isPrestado()){
                if(usuario.getLibro_prestado() == libro.getId()){
                    usuario.setLibro_prestado(0);
                    values2.put("libro_prestado", 0);
                }else
                    throw new Exception("El libro esta reservado.");
            }else{
                if(usuario.getLibro_prestado() == 0){
                    usuario.setLibro_prestado(libro.getId());
                    values2.put("libro_prestado", libro.getId());
                }else
                    throw new Exception("Tienes un libro reservado.");
            }
            db.update("Usuarios", values2, "idUsuario = ?", new String[]{
                    String.valueOf(usuario.getId())
            });
        }

        db.update("Libros", values, " idLibros = ?", new String[]{
                String.valueOf(libro.getId())
        });

    }

    private boolean posibleEliminarLibro(Libro libro){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Usuarios", null);
        if(cursor.moveToFirst()){
            do{
                int libro_prestado = cursor.getInt(
                        cursor.getColumnIndexOrThrow("libro_prestado"));
                if(libro_prestado == libro.getId())
                    return false;
            }while (cursor.moveToNext());
        }
        cursor.close();
        return true;
    }

    public void eliminarLibro(Libro libro) throws Exception {
        if(posibleEliminarLibro(libro)) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("Libros", "idLibros = ?", new String[]{
                    String.valueOf(libro.getId())
            });
        }else
            throw new Exception("No se puede eliminar el libro, alguien lo tiene.");

    }

}
