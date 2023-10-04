package com.example.controles_basicos3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnValidarDNI;
    EditText etNumsDNI, etLetraDNI;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResultado = (TextView) findViewById(R.id.tvResultado);
        etLetraDNI = (EditText) findViewById(R.id.etLetraDNI);
        etNumsDNI = (EditText) findViewById(R.id.etNumsDNI);
        btnValidarDNI = (Button) findViewById(R.id.btnValidarDNI);

        btnValidarDNI.setOnClickListener(e -> validarDNI());
    }

    private void validarDNI(){
        try {
            String numsDNI = etNumsDNI.getText().toString();
            String letraMayuscula = etLetraDNI.getText().toString().toUpperCase();


            // Aquí excluimos cadenas distintas a 9 caracteres que debe tener un dni y también si el último caracter no es una letra
            if(numsDNI.length() != 8 || letraMayuscula.length() != 1 || Character.isLetter(letraMayuscula.charAt(0)) == false ) {
                tvResultado.setText("El DNI no es valido");
                tvResultado.setTextColor(Color.RED);
                return;
            }

            // Por último validamos que sólo tengo 8 dígitos entre los 8 primeros caracteres y que la letra introducida es igual a la de la ecuación
            // Llamamos a los métodos privados de la clase soloNumeros() y letraDNI()
            if(soloNumeros(numsDNI) == true && letraDNI(numsDNI).equals(letraMayuscula)) {
                tvResultado.setText("El DNI es valido");
                tvResultado.setTextColor(Color.GREEN);
            }
            else {
                tvResultado.setText("El DNI no es valido");
                tvResultado.setTextColor(Color.RED);
            }
        }catch (Exception e){
            tvResultado.setText("El DNI no es valido");
            tvResultado.setTextColor(Color.RED);
        }
    }

    private boolean soloNumeros(String dni) {

        int i, j = 0;
        String numero = ""; // Es el número que se comprueba uno a uno por si hay alguna letra entre los 8 primeros dígitos
        String miDNI = ""; // Guardamos en una cadena los números para después calcular la letra
        String[] unoNueve = {"0","1","2","3","4","5","6","7","8","9"};

        for(i = 0; i < dni.length(); i++) {
            numero = dni.substring(i, i+1);

            for(j = 0; j < unoNueve.length; j++) {
                if(numero.equals(unoNueve[j])) {
                    miDNI += unoNueve[j];
                }
            }
        }

        if(miDNI.length() != 8) {
            return false;
        }
        else {
            return true;
        }
    }

    private String letraDNI(String numsDNI) {
        // El método es privado porque lo voy a usar internamente en esta clase, no se necesita fuera de ella

        // pasar miNumero a integer
        int miDNI = Integer.parseInt(numsDNI);
        int resto = 0;
        String miLetra = "";
        String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

        resto = miDNI % 23;

        miLetra = asignacionLetra[resto];

        return miLetra;
    }

}