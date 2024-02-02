package com.example.examenev2_sergio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvNumConsultas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnConsultar = findViewById(R.id.btnConsultar);
        Button btnQuimico = findViewById(R.id.btnQuimico);
        Button btnSalir = findViewById(R.id.btnSalir);
        tvNumConsultas = findViewById(R.id.tvNumConsultas);

        //Cuando le da al boton salir
        btnSalir.setOnClickListener(e-> {
            Toast.makeText(getApplicationContext(), "Hasta luego", Toast.LENGTH_SHORT).show();
            finish();
        });

        //Cuando le da al boton Consultar
        btnConsultar.setOnClickListener(e -> {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivityForResult(intent, 1);
        });

        //Cuando le da al boton Quimico
        btnQuimico.setOnClickListener(e ->{
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        tvNumConsultas.setText(String.valueOf(0));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == 1) {
            assert data != null;
            int num_consultas = data.getIntExtra("num_consultas", 0);
            tvNumConsultas.setText(String.valueOf(num_consultas));
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}