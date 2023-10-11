package com.example.controles_basicosii_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class AceptarCondicionesMain extends AppCompatActivity {

    private Button btnAceptar, btnRechazar;
    private TextView txtMensaje;

    private Boolean aceptasCondiciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceptar_condiciones_main);

        txtMensaje = (TextView) findViewById(R.id.txtMensaje);
        btnAceptar = (Button) findViewById(R.id.btnAceptar);
        btnRechazar = (Button) findViewById(R.id.btnRechazar);

        btnRechazar.setOnClickListener(e -> click_rechazar());
        btnAceptar.setOnClickListener(e -> click_aceptar());

        Bundle nombre_completo = getIntent().getExtras();

        String nombre = nombre_completo.getString("nombre");
        String apellidos = nombre_completo.getString("apellidos");
        txtMensaje.setText("Hola "+ nombre.toUpperCase() + " " + apellidos.toUpperCase() +
                "¿Aceptas las condiciones?");
    }

    private void click_aceptar(){
        aceptasCondiciones = true;
        Intent intent = new Intent();
        intent.putExtra("resultado", aceptasCondiciones );
        setResult(RESULT_OK, intent);
        finish();
    }

    private void click_rechazar(){
        aceptasCondiciones = false;
        Intent intent = new Intent();
        intent.putExtra("resultado", aceptasCondiciones );
        setResult(RESULT_OK, intent);
        finish();
    }

}