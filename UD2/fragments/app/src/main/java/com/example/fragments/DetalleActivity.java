package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class DetalleActivity extends AppCompatActivity {

    public static final String EXTRA_TEXTO = "";

    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        DetalleFragment detalle = (DetalleFragment)
                getSupportFragmentManager().findFragmentById(R.id.frgDetalle);

        btnVolver = (Button) findViewById(R.id.btnVolver);

        detalle.mostrarDetalle(getIntent().getStringExtra(EXTRA_TEXTO));
        btnVolver.setOnClickListener( e -> finish());
    }
}