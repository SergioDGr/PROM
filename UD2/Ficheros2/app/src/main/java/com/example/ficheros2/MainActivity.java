package com.example.ficheros2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spProvincias;
    private TextView tvSelected;
    private List<String> lstProvincias = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spProvincias = findViewById(R.id.spProvincias);
        tvSelected = findViewById(R.id.tvSelected);
        cargarSpinner();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lstProvincias);

        spProvincias.setAdapter(adapter);
        spProvincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String provincia = (String) parent.getItemAtPosition(position);
                tvSelected.setText(provincia);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void cargarSpinner(){
        try {
            InputStream fraw =
                    getResources().openRawResource(R.raw.provincias);
            BufferedReader brin =
                    new BufferedReader(new InputStreamReader(fraw));
            String linea = brin.readLine();
            while (linea != null) {
                lstProvincias.add(linea);
                Log.i("Ficheros", linea);
                linea = brin.readLine();
            }
            fraw.close();
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde recurso raw");
        }
    }

}