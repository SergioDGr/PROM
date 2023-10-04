package com.example.controles_basicos4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnYahoo, btnGoogle, btnBing;
    ImageView ivNavegador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGoogle = (Button) findViewById(R.id.btnGoogle);
        btnBing = (Button) findViewById(R.id.btnBing);
        btnYahoo = (Button) findViewById(R.id.btnYahoo);
        ivNavegador = (ImageView) findViewById(R.id.ivNavegador);

        btnYahoo.setOnClickListener(e -> cambiarImagenYahoo());
        btnBing.setOnClickListener(e -> cambiarImagenBing());
        btnGoogle.setOnClickListener(e -> cambiarImagenGoogle());
    }

    private void cambiarImagenYahoo(){
        ivNavegador.setImageResource(R.drawable.yahoo);
    }

    private void cambiarImagenBing(){
        ivNavegador.setImageResource(R.drawable.bing);
    }

    private void cambiarImagenGoogle(){
        ivNavegador.setImageResource(R.drawable.google);
    }



}