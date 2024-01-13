package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AniadirLibroActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText etTitulo, etDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_libro);

        databaseHelper = new DatabaseHelper(this);

        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);

        Button btnAniadir, btnCancelar;
        btnAniadir = findViewById(R.id.btnAniadir);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnAniadir.setOnClickListener(e -> aniadir());
        btnCancelar.setOnClickListener(e -> finish());
    }

    private void aniadir(){
        Libro l = new Libro(etTitulo.getText().toString(), etDescripcion.getText().toString());
        databaseHelper.insertarLibro(l);
        Toast.makeText(getApplicationContext(), "Libro creado", Toast.LENGTH_SHORT)
                .show();
        Intent intent = new Intent();
        intent.putExtra("Libro", l);
        setResult(1, intent);
        finish();
    }

}