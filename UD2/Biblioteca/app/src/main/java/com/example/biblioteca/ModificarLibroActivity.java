package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ModificarLibroActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText etTitulo, etDescripcion;
    private TextView tvInfo;
    private RadioButton rbSi;
    private Libro libro;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_libro);
        Button btnEditar, btnEliminar, btnCancelar;
        RadioButton rbNo;

        databaseHelper = new DatabaseHelper(this);
        libro = (Libro) getIntent().getSerializableExtra("Libro");
        usuario = (Usuario) getIntent().getSerializableExtra("Usuario");

        tvInfo = findViewById(R.id.tvInfo);
        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        rbSi = findViewById(R.id.rbSi);
        rbNo = findViewById(R.id.rbNo);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnCancelar = findViewById(R.id.btnCancelar);

        assert libro != null;
        etTitulo.setText(libro.getTitulo());
        etDescripcion.setText(libro.getDescripcion());
        rbSi.setChecked(libro.isPrestado());
        rbNo.setChecked(!libro.isPrestado());

        btnEditar.setOnClickListener(e -> editar());
        btnEliminar.setOnClickListener(e -> eliminar());
        btnCancelar.setOnClickListener(e -> finish());
    }

    private void editar(){
        libro.setTitulo(etTitulo.getText().toString());
        libro.setDescripcion(etDescripcion.getText().toString());
        libro.setPrestado(rbSi.isChecked());
        try {
            databaseHelper.editarLibro(libro, usuario);
            Toast.makeText(getApplicationContext(), "Libro editado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("Libro", libro);
            intent.putExtra("Usuario", usuario);
            setResult(2, intent);
            finish();
        }catch (Exception e){
            tvInfo.setText(e.getMessage());
        }
    }

    private void eliminar(){
        try {
            databaseHelper.eliminarLibro(libro);
            Toast.makeText(getApplicationContext(), "Libro eliminado",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("Usuario", usuario);
            setResult(3, intent);
            finish();
        }catch (Exception e){
            tvInfo.setText(e.getMessage());
        }

    }

}