package com.example.control_basicosii_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioButton rdbPrg1_1, rdbPrg1_2, rdbPrg1_3;
    private RadioButton rdbPrg2_1, rdbPrg2_2, rdbPrg2_3;
    private RadioButton rdbPrg3_1, rdbPrg3_2, rdbPrg3_3;
    private RadioButton rdbPrg4_1, rdbPrg4_2, rdbPrg4_3;
    private Button btnEnviar;
    private TextView txtError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rdbPrg1_1 = (RadioButton) findViewById(R.id.rdbPrg1_1);
        rdbPrg1_2 = (RadioButton) findViewById(R.id.rdbPrg1_2);
        rdbPrg1_3 = (RadioButton) findViewById(R.id.rdbPrg1_3);

        rdbPrg2_1 = (RadioButton) findViewById(R.id.rdbPrg2_1);
        rdbPrg2_2 = (RadioButton) findViewById(R.id.rdbPrg2_2);
        rdbPrg2_3 = (RadioButton) findViewById(R.id.rdbPrg2_3);

        rdbPrg3_1 = (RadioButton) findViewById(R.id.rdbPrg3_1);
        rdbPrg3_2 = (RadioButton) findViewById(R.id.rdbPrg3_2);
        rdbPrg3_3 = (RadioButton) findViewById(R.id.rdbPrg3_3);

        rdbPrg4_1 = (RadioButton) findViewById(R.id.rdbPrg1_1);
        rdbPrg4_2 = (RadioButton) findViewById(R.id.rdbPrg4_2);
        rdbPrg4_3 = (RadioButton) findViewById(R.id.rdbPrg4_3);

        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        txtError = (TextView) findViewById(R.id.txtError);

        txtError.setTextColor(Color.RED);

        btnEnviar.setOnClickListener(e -> enviar_resultados());
    }

    private void enviar_resultados(){
        String msgError = "";
        if(!rdbPrg1_1.isChecked() && !rdbPrg1_2.isChecked() && !rdbPrg1_3.isChecked() ){
            msgError += "No se a seleccionado ninguna respuesta a la pregunta 1";
        }
        if(!rdbPrg2_1.isChecked() && !rdbPrg2_2.isChecked() && !rdbPrg2_3.isChecked() ){
            msgError += "No se a seleccionado ninguna respuesta a la pregunta 2";
        }
        if(!rdbPrg3_1.isChecked() && !rdbPrg3_2.isChecked() && !rdbPrg3_3.isChecked() ){
            msgError += "No se a seleccionado ninguna respuesta a la pregunta 3";
        }
        if(!rdbPrg4_1.isChecked() && !rdbPrg4_2.isChecked() && !rdbPrg4_3.isChecked() ){
            msgError += "No se a seleccionado ninguna respuesta a la pregunta 4";
        }
        if(msgError.isEmpty()){
            Intent intent = new Intent(MainActivity.this, ComprobarResultadoActivity.class);
            intent.putExtra("respuesta_1", rdbPrg1_1.isChecked()? 1 : rdbPrg1_2.isChecked()?
                    2: 3);
            intent.putExtra("respuesta_2", rdbPrg2_1.isChecked()? 1 : rdbPrg2_2.isChecked()?
                    2: 3);
            intent.putExtra("respuesta_3", rdbPrg3_1.isChecked()? 1 : rdbPrg3_2.isChecked()?
                    2: 3);
            intent.putExtra("respuesta_4", rdbPrg4_1.isChecked()? 1 : rdbPrg4_2.isChecked()?
                    2: 3);
            startActivityForResult(intent,1);
        }else
            txtError.setText(msgError);
    }


}