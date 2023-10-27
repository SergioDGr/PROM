package com.example.control_basicosii_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ComprobarResultadoActivity extends AppCompatActivity {

    private TextView resulPregunta1, resulPregunta2, resulPregunta3, resulPregunta4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprobar_resultado);

        resulPregunta1 = (TextView) findViewById(R.id.resulPregunta1);
        resulPregunta2 = (TextView) findViewById(R.id.resulPregunta2);
        resulPregunta3 = (TextView) findViewById(R.id.resulPregunta3);
        resulPregunta4 = (TextView) findViewById(R.id.resulPregunta4);

        int resp_1 =  getIntent().getIntExtra("respuesta_1", -1);
        int resp_2 =  getIntent().getIntExtra("respuesta_2", -1);
        int resp_3 =  getIntent().getIntExtra("respuesta_3", -1);
        int resp_4 =  getIntent().getIntExtra("respuesta_4", -1);

        if (resp_1 == 3)
            resulPregunta1.setText("Respuesta correcta");
        else
            resulPregunta1.setText("Respuesta incorrecta");

        if (resp_2 == 2)
            resulPregunta2.setText("Respuesta correcta");
        else
            resulPregunta2.setText("Respuesta incorrecta");
        if (resp_3 == 3)
            resulPregunta3.setText("Respuesta correcta");
        else
            resulPregunta3.setText("Respuesta incorrecta");

        if (resp_4 == 1)
            resulPregunta4.setText("Respuesta correcta");
        else
            resulPregunta4.setText("Respuesta incorrecta");
    }
}