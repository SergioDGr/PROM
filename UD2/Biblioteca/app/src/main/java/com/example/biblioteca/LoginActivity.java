package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText etUsuario, etPass;
    private Button btnIniciarSesion, btnCrearUsuario;

    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        etUsuario = findViewById(R.id.etUsuario);
        etPass = findViewById(R.id.etpass);
        tvInfo = findViewById(R.id.tvInfo);
        btnCrearUsuario = findViewById(R.id.btnCrearUsuario);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);

        btnIniciarSesion.setOnClickListener( e -> iniciar_sesion());
        btnCrearUsuario.setOnClickListener( e -> crear_usuario());
    }

    private void iniciar_sesion(){
        String str_usuario = etUsuario.getText().toString();
        String pass = etPass.getText().toString();
        Usuario usuario = new Usuario(str_usuario, pass);
        String str_validado = databaseHelper.validarUsuario(usuario);
        if(str_validado.equals("Las credenciales son correctas")){
            Toast.makeText(getApplicationContext(), "Usuario Logeado", Toast.LENGTH_SHORT)
                    .show();
            Intent intent = new Intent();
            intent.putExtra("Usuario", usuario);
            setResult(4, intent);
            finish();
        }else{
            tvInfo.setText(R.string.error_iniciar_sesion);
        }
    }

    private void crear_usuario(){
        String usuario = etUsuario.getText().toString();
        String pass = etPass.getText().toString();
        databaseHelper.insertarUsuario(new Usuario(usuario, pass));
        tvInfo.setText(R.string.usuario_creado);
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }
}