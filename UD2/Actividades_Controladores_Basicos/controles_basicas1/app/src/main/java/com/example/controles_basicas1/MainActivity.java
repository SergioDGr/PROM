package com.example.controles_basicas1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtResultado;
    private EditText primerValor;
    private EditText segundoValor;
    private Button btnSumar, btnRestar, btnMultiplicar, btnDividir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Componentes
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        primerValor = (EditText) findViewById(R.id.primerValor);
        segundoValor = (EditText) findViewById(R.id.segundoValor);
        btnRestar = (Button) findViewById(R.id.btnRestar);
        btnSumar = (Button) findViewById(R.id.btnSumar);
        btnMultiplicar = (Button) findViewById(R.id.btnMultiplicar);
        btnDividir = (Button) findViewById(R.id.btnDividir);
        //Eventos
        btnSumar.setOnClickListener(e -> {operacion(btnSumar.getText().toString());});
        btnRestar.setOnClickListener(e -> {operacion(btnRestar.getText().toString());});
        btnMultiplicar.setOnClickListener(e -> {operacion(btnMultiplicar.getText().toString());});
        btnDividir.setOnClickListener(e -> {operacion(btnDividir.getText().toString());});
    }

    private void operacion(String operacion){
        String resul = "";
        try {
            float num1, num2;
            num1 = Float.parseFloat(primerValor.getText().toString());
            num2 = Float.parseFloat(segundoValor.getText().toString());
            switch (operacion){
                case "+":
                    num2 = num1 + num2;
                    break;
                case "-":
                    num2 = num1 - num2;
                    break;
                case "*":
                    num2 = num1 * num2;
                case "/":
                        num2 = num1 / num2;
            }
            resul = num2 + "";
        }catch (ArithmeticException e){
            resul = "Se ha intentado dividir entre cero.";
        }catch (NumberFormatException ex){
            resul = "Los valores no son numeros";
        }

        txtResultado.setText(resul);
    }

}