package com.example.controles_basicosii_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etNum1, etNum2, etNumResultado;
    private Button btnCompResul;
    private TextView txtCorrectos, txtIncorrectos;

    private int correctas, incorrectas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = (EditText) findViewById(R.id.etNum1);
        etNum2 = (EditText) findViewById(R.id.etNum2);
        etNumResultado = (EditText) findViewById(R.id.etNumResultado);
        btnCompResul = (Button) findViewById(R.id.btnCompResul);
        txtCorrectos = (TextView) findViewById(R.id.txtCorrectos);
        txtIncorrectos = (TextView) findViewById(R.id.txtIncorrectos);

        etNum1.setText(generarNumero() + "");
        etNum2.setText(generarNumero() + "");

        btnCompResul.setOnClickListener( e -> comprobarResultado());
    }

    private void comprobarResultado(){
        try {
            Intent intent = new Intent(MainActivity.this, ComprobarResultado.class);
            intent.putExtra("num1", Integer.parseInt(etNum1.getText().toString()));
            intent.putExtra("num2", Integer.parseInt(etNum2.getText().toString()));
            intent.putExtra("numResultado", Integer.parseInt(etNumResultado.getText().toString()));
            startActivityForResult(intent,1);
        }catch (Exception e){

        }
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK){
            Boolean resultadoCorrecto = data.getExtras().getBoolean("correcta");
            if (resultadoCorrecto) {
                correctas += 1;
                etNum1.setText(generarNumero() + "");
                etNum2.setText(generarNumero() + "");
                etNumResultado.setText("");
            }else
                incorrectas += 1;
        }
        txtIncorrectos.setText(incorrectas + "");
        txtCorrectos.setText(correctas + "");

        super.onActivityResult(requestCode, resultCode, data);
    }


    private int generarNumero(){
        return (int) (Math.random() * 101);
    }

}