package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LibroListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LibroFragment fragmentListado = (LibroFragment)
                getSupportFragmentManager().findFragmentById(R.id.frgLibro);
        fragmentListado.setCorreoListener(this);

    }


    @Override
    public void onCorreoSeleccionado(Libro c) {
        boolean hayDetalle = (getSupportFragmentManager().
                findFragmentById(R.id.frgDetalle) != null);
        if (hayDetalle) {
            ((DetalleFragment) getSupportFragmentManager().
                    findFragmentById(R.id.frgDetalle)).
                    mostrarDetalle(c.getSinopsis());
        } else {
           Intent i = new Intent(this, DetalleActivity.class);
           i.putExtra(DetalleActivity.EXTRA_TEXTO, c.getSinopsis());
           startActivity(i);
        }
    }
}
