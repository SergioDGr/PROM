package com.example.controles_basicosii_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class VerFormularioActivity extends AppCompatActivity {

    private TextView txtNombre, txtApellidos, txtSexo, txtAficiones;
    private Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_formulario);

        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtApellidos = (TextView) findViewById(R.id.txtApellidos);
        txtSexo = (TextView) findViewById(R.id.txtSexo);
        txtAficiones = (TextView) findViewById(R.id.txtAficiones);
        btnRegresar = (Button) findViewById(R.id.btnRegresar);

        String nombre = getIntent().getStringExtra("nombre");
        txtNombre.setText(nombre);
        String apellidos = getIntent().getStringExtra("apellidos");
        txtApellidos.setText(apellidos);
        String sexo = getIntent().getStringExtra("sexo");
        txtSexo.setText(sexo);
        String aficiones = getIntent().getStringExtra("aficiones");
        txtAficiones.setText(aficiones);

        btnRegresar.setOnClickListener(e -> finish());
    }
}