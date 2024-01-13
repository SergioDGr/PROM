package com.example.biblioteca;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private Menu menu;
    private ListView lvLibros;
    private ArrayAdapter<Libro> adapter;
    private List<Libro> lstLibro;
    private Usuario usuario;
    private int indexLibro = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolBar = findViewById(R.id.toolbar);
        lvLibros = findViewById(R.id.lvLibros);
        setSupportActionBar(toolBar);

        databaseHelper = new DatabaseHelper(this);
        lstLibro = databaseHelper.getLibros();
        adapter = new LibroAdapter(this, lstLibro);
        lvLibros.setAdapter(adapter);

        lvLibros.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this,
                    ModificarLibroActivity.class);

            Libro l = (Libro) parent.getItemAtPosition(position);
            indexLibro = position;

            intent.putExtra("Libro", l);
            intent.putExtra("Usuario", usuario);
            startActivityForResult(intent, 2);
        });

        crearVentanaLogin();
    }

    private boolean crearVentanaAniadirLibro(){
        Intent intent = new Intent(MainActivity.this, AniadirLibroActivity.class);
        startActivityForResult(intent, 1);
        return true;
    }

    private void crearVentanaLogin(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivityForResult(intent, 4);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch (resultCode) {
            case 1:
                assert data != null;
                Libro l = (Libro) data.getSerializableExtra("Libro");
                lstLibro.add(l);
                adapter.notifyDataSetChanged();
                break;
            case 2:
                assert data != null;
                usuario = (Usuario) data.getSerializableExtra("Usuario");
                lstLibro.set(indexLibro, (Libro) data.getSerializableExtra("Libro"));
                adapter.notifyDataSetChanged();
                break;
            case 3:
                assert data != null;
                usuario = (Usuario) data.getSerializableExtra("Usuario");
                lstLibro.remove(indexLibro);
                adapter.notifyDataSetChanged();

                break;
            case 4:
                assert data != null;
                usuario = (Usuario) data.getSerializableExtra("Usuario");
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        this.menu = menu;

        this.menu.getItem(0).setOnMenuItemClickListener( e -> crearVentanaAniadirLibro());

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_logOut) {
            usuario = null;
            crearVentanaLogin();
        }
        return true;
    }
}