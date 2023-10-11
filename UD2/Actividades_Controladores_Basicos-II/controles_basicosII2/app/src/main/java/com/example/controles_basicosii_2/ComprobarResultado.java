package com.example.controles_basicosii_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ComprobarResultado extends AppCompatActivity {

    private TextView txtResultado;
    private Button btnVolver;

    private boolean resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprobar_resultado);

        txtResultado = (TextView) findViewById(R.id.txtResultado);
        btnVolver = (Button) findViewById(R.id.btnVolver);

        int num1 = getIntent().getIntExtra("num1",-1);
        int num2 = getIntent().getIntExtra("num2",-1);
        int resultado = getIntent().getIntExtra("numResultado", -1);

        this.resultado = num1 + num2 == resultado ;
        txtResultado.setText(this.resultado ? "CORRECTA": "INCORRECTA");

        btnVolver.setOnClickListener(e -> volver());
    }

    private void volver(){
        Intent intent = new Intent();
        intent.putExtra("correcta", this.resultado );
        setResult(RESULT_OK, intent);
        finish();
    }

}