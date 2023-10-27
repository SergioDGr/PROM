package com.example.controles_seleccion_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spOpciones;
    private TextView msgPais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spOpciones = (Spinner) findViewById(R.id.spElementos);
        msgPais = (TextView) findViewById(R.id.msgPais);

        //Creamos el Array
        final String[] paises = new String[]{
                "España", "Colombia", "China", "Egipto", "Australia", "Portugal", "Canada",
                "Japon"
        };

        //Creamos el adaptador para el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, paises);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spOpciones.setAdapter(adapter);

        //Eventos para el spinner
        spOpciones.setOnItemSelectedListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        String pais = parent.getAdapter().getItem(position).toString();
        String cant_habitantes = "";
        String superficie = "";

        switch (pais){
            case "España":
                cant_habitantes = "47,42 millones";
                superficie = "506.030";
                break;
            case "Colombia":
                cant_habitantes = "51,52 millones";
                superficie = "1,142 millones";
        }

        msgPais.setText("El pais " + pais + " tiene una superficie de " + superficie +
                " km2 y poblacion de " + cant_habitantes + " habitantes.");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}