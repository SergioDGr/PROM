package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class AniadirContactoActivity extends AppCompatActivity {

    private EditText etNombre, etApellidos, etCorreo, etTelefono ;
    private Button btnAniadir, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_contacto);

        etApellidos = findViewById(R.id.etApellido);
        etTelefono = findViewById(R.id.etTelefono);
        etCorreo = findViewById(R.id.etTelefono);
        etNombre = findViewById(R.id.etNombre);

        btnAniadir = findViewById(R.id.btnAniadir);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnCancelar.setOnClickListener( e -> {
            setResult(0);
            finish();
        });
        btnAniadir.setOnClickListener( e -> guardar());

    }

    private void guardar(){
        try {
            String nombre = etNombre.getText().toString();
            String apellidos = etApellidos.getText().toString();
            String email = etCorreo.getText().toString();
            int tel = Integer.parseInt(etTelefono.getText().toString());
            Intent intent = new Intent();
            intent.putExtra("nombre", nombre);
            intent.putExtra("apellidos", apellidos);
            intent.putExtra("email", email);
            intent.putExtra("tel", tel);
            setResult(1, intent);
            finish();

        }catch (Exception e){
            Log.e("Exception", e.getMessage());
        }
    }

}