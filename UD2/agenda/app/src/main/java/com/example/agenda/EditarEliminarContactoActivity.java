package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class EditarEliminarContactoActivity extends AppCompatActivity {

    private EditText etNombre, etApellidos, etCorreo, etTelefono ;
    private Button btnEditar, btnEliminar , btnCancelar;

    private Contacto c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_eliminar_contacto);

        etApellidos = findViewById(R.id.etApellido);
        etTelefono = findViewById(R.id.etTelefono);
        etCorreo = findViewById(R.id.etCorreo);
        etNombre = findViewById(R.id.etNombre);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnCancelar.setOnClickListener( e -> {
                setResult(0);
                finish();
        });
        btnEditar.setOnClickListener( e -> editar());
        btnEliminar.setOnClickListener( e -> eliminar());

        c = (Contacto) getIntent().getSerializableExtra("Contacto");
        etNombre.setText(c.getNombre());
        etApellidos.setText(c.getApellido());
        etCorreo.setText(c.getCorreo_electronico());
        etTelefono.setText(String.valueOf(c.getTelefono()));
    }

    private void editar(){
        try {
            String nombre = etNombre.getText().toString();
            String apellidos = etApellidos.getText().toString();
            String email = etCorreo.getText().toString();
            int tel = Integer.parseInt(etTelefono.getText().toString());
            Intent intent = new Intent();
            intent.putExtra("id", c.getId());
            intent.putExtra("nombre", nombre);
            intent.putExtra("apellidos", apellidos);
            intent.putExtra("email", email);
            intent.putExtra("tel", tel);
            setResult(2, intent);
            finish();

        }catch (Exception e){
            Log.e("Exception", e.getMessage());
        }
    }

    private void eliminar(){
        Intent intent = new Intent();
        intent.putExtra("id", c.getId());
        setResult(3, intent);
        finish();
    }

}