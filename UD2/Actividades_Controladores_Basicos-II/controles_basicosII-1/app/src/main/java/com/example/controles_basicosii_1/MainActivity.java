package com.example.controles_basicosii_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnVerificar;
    private EditText etNombre, etApellidos;

    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnVerificar = (Button) findViewById(R.id.btnVerificar);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        txtResultado = (TextView) findViewById(R.id.txtResultado);

        btnVerificar.setOnClickListener(e -> click_verificar());
    }

    private void click_verificar(){
        Intent intent = new Intent(MainActivity.this,
                AceptarCondicionesMain.class);
        intent.putExtra("nombre", etNombre.getText().toString());
        intent.putExtra("apellidos", etApellidos.getText().toString());
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK){
            Boolean resp = data.getExtras().getBoolean("resultado");
            if (resp)
                txtResultado.setText("ACEPTADO");
            else
                txtResultado.setText("RECHAZADO");
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}