package com.example.controles_basicosii_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre, etApellidos;
    private RadioButton rbtnMasculino, rbtnFemenino;
    private CheckBox chkMusica, chkLectura, chkDeportes, chkViajar;
    private Button btnEnviar;
    private TextView txtError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        rbtnFemenino = (RadioButton) findViewById(R.id.rbtnFemenino);
        rbtnMasculino = (RadioButton) findViewById(R.id.rbtnMasculino);
        chkMusica = (CheckBox) findViewById(R.id.chkMusica);
        chkDeportes = (CheckBox) findViewById(R.id.chkDeportes);
        chkLectura = (CheckBox) findViewById(R.id.chkLectura);
        chkViajar = (CheckBox) findViewById(R.id.chkViajar);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        txtError = (TextView) findViewById(R.id.txtError);

        txtError.setTextColor(Color.RED);

        btnEnviar.setOnClickListener(e -> validarYenviar());

    }

    private void validarYenviar(){
        String msgError = "";
        if(etNombre.getText().toString().isEmpty())
            msgError += "El nombre esta vacio.\n";
        if(etApellidos.getText().toString().isEmpty())
            msgError += "Los apellidos esta vacio.\n";
        if (!rbtnMasculino.isChecked() && !rbtnFemenino.isChecked())
            msgError += "No se a selecionado ningun sexo.\n";

        if(msgError.isEmpty()){
            Intent intent = new Intent(MainActivity.this, VerFormularioActivity.class);
            intent.putExtra("nombre", etNombre.getText().toString());
            intent.putExtra("apellidos", etApellidos.getText().toString());
            intent.putExtra("sexo", rbtnFemenino.isChecked() ? rbtnFemenino.getText() :
                    rbtnMasculino.getText());
            String msgAficiones = "";
            if(chkMusica.isChecked())
                msgAficiones += chkMusica.getText().toString() + " ";
            if(chkLectura.isChecked())
                msgAficiones += chkLectura.getText().toString() + " ";
            if(chkDeportes.isChecked())
                msgAficiones += chkDeportes.getText().toString() + " ";;
            if(chkViajar.isChecked())
                msgAficiones += chkViajar.getText().toString()+ " ";;
            if(msgAficiones.isEmpty())
                msgAficiones = "No hay aficiones";
            intent.putExtra("aficiones", msgAficiones);
            startActivityForResult(intent,1);
        }else
            txtError.setText(msgError);

    }

}