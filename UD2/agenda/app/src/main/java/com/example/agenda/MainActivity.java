package com.example.agenda;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Menu menu;
    private Database db;
    private ListView lvContacos;
    private List<Contacto> lstContactos = new ArrayList<>();
    private ArrayAdapter<Contacto> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new Database(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        lvContacos = findViewById(R.id.lvContacos);

        setSupportActionBar(toolbar);

        adapter = new ContactoAdapter(this, lstContactos);
        lvContacos.setAdapter(adapter);

        conseguirContactos();
        lvContacos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,
                        EditarEliminarContactoActivity.class);
                Contacto c = (Contacto) parent.getItemAtPosition(position);
                intent.putExtra("Contacto", c);

                startActivityForResult(intent, 2);
            }
        });
    }

    private void crearVentana(){
        Intent intent = new Intent(MainActivity.this, AniadirContactoActivity.class);
        startActivityForResult(intent, 1);
    }

    private void aniadirContacto(String nombre, String apellidos, String correo, int telefono){
        SQLiteDatabase sqlDb = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellidos", apellidos);
        values.put("email", correo);
        values.put("telefono", telefono);
        sqlDb.insert("Contactos", null, values);
        conseguirContactos();
    }

    private void editarContacto(int id, String nombre, String apellidos, String correo, int telefono){
        SQLiteDatabase sqlDb = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellidos", apellidos);
        values.put("email", correo);
        values.put("telefono", telefono);
        sqlDb.update("Contactos",  values, "idContacto = ?",
                new String[]{String.valueOf(id)});
        conseguirContactos();
    }

    private void eliminarContacto(int id){
        SQLiteDatabase sqlDb = db.getWritableDatabase();
        sqlDb.delete("Contactos", "idContacto = ?",
                new String[]{String.valueOf(id)});
        Toast.makeText(getApplicationContext(), "contacto eliminado",
                Toast.LENGTH_SHORT).show();
        conseguirContactos();
    }

    private void conseguirContactos(){
        lstContactos.clear();
        SQLiteDatabase sqlDB = db.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery("SELECT * FROM Contactos", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("idContacto"));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                String apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellidos"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                int telefono = cursor.getInt(cursor.getColumnIndexOrThrow("telefono"));

                Contacto c = new Contacto(id, nombre, apellido, email, telefono);
                lstContactos.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        int id = 0;
        String nombre = "";
        String apellidos = "";
        String correo = "";
        int tel = 0;
        switch(resultCode){
            case 1:
                nombre = data.getStringExtra("nombre");
                apellidos = data.getStringExtra("apellidos");
                correo = data.getStringExtra("email");
                tel = data.getIntExtra("tel", 0);
                aniadirContacto(nombre, apellidos, correo, tel);
                Toast.makeText(MainActivity.this, "Añadido", Toast.LENGTH_SHORT)
                        .show();
                 break;
            case 2:
                id = data.getIntExtra("id", 0);
                nombre = data.getStringExtra("nombre");
                apellidos = data.getStringExtra("apellidos");
                correo = data.getStringExtra("email");
                tel = data.getIntExtra("tel", 0);
                editarContacto(id, nombre, apellidos, correo, tel);
                Toast.makeText(MainActivity.this, "Actualizado", Toast.LENGTH_SHORT)
                        .show();
                break;
            case 3:
                id = data.getIntExtra("id", 0);
                eliminarContacto(id);
                Toast.makeText(MainActivity.this, "Eliminado", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        this.menu = menu;

        this.menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                crearVentana();
                return false;
            }
        });

        return true;
    }

}