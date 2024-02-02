package com.example.examenev2_sergio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    private static int numero_consultas = 0;

    private DatabaseHelper databaseHelper;
    private LinearLayout llResult;
    private Button btnBuscar, btnVolver;
    private EditText etBusqueda;
    private TextView tvID, tvNombre, tvSimbolo, tvNumAtomico, tvEstado, tvError;
    private AlertDialog.Builder dialogBuilder;

    private boolean limpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        databaseHelper = new DatabaseHelper(this);

        llResult = findViewById(R.id.llResult);
        etBusqueda = findViewById(R.id.etBusqueda);

        tvID = findViewById(R.id.tvID);
        tvNombre = findViewById(R.id.tvNombre);
        tvSimbolo = findViewById(R.id.tvSimbolo);
        tvNumAtomico = findViewById(R.id.tvNumAtomico);
        tvEstado = findViewById(R.id.tvEstado);
        tvError = findViewById(R.id.tvError);

        btnBuscar = findViewById(R.id.btnBuscar);
        btnVolver = findViewById(R.id.btnVolver);

        btnBuscar.setOnClickListener(e -> click_buscar());
        btnVolver.setOnClickListener(e -> {
            Intent intent = new Intent();
            intent.putExtra("num_consultas", numero_consultas);
            setResult(1, intent);
            finish();
        });
    }

    private void click_buscar(){
        if(limpiar){
            llResult.setVisibility(View.GONE);
            limpiar = false;
            btnBuscar.setText(R.string.buscar);
            etBusqueda.setText("");
            return;
        }

        String nombre_elemento = etBusqueda.getText().toString().toUpperCase();
        Elemento e = databaseHelper.buscarElemento(nombre_elemento);
        if(e == null){
            //Creamos el creador de dialog
            dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setMessage("No se encontrado el elemento.");
            //Se le definine los eventos a los botones
            dialogBuilder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
            //Visualiza el dialogo
            AlertDialog dialog = dialogBuilder.create();
            dialog.show();
        }else{
            llResult.setVisibility(View.VISIBLE);

            tvID.setText(String.valueOf(e.getId()));
            tvNombre.setText(e.getNombre());
            tvSimbolo.setText(e.getSimbolo());
            tvNumAtomico.setText(String.valueOf(e.getNum_atomico()));
            tvEstado.setText(e.getEstado());

            limpiar = true;

            btnBuscar.setText(R.string.limpiar);

            numero_consultas += 1;
        }

    }


}