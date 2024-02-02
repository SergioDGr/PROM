package com.example.examenev2_sergio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private static final String USUARIO = "admin";
    private static final String PASSWORD = "admin";

    private ImageView ivError;
    private TextView tvError;
    private EditText etNombre, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ivError = findViewById(R.id.ivError);
        tvError = findViewById(R.id.tvError);

        etNombre = findViewById(R.id.etNombre);
        etPassword = findViewById(R.id.etPassword);

        Button btnCancelar = findViewById(R.id.btnCancelar);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnCancelar.setOnClickListener(e -> finish());
        btnLogin.setOnClickListener(e -> click_iniciar_sesion());
    }

    private void click_iniciar_sesion(){
        String user = etNombre.getText().toString();
        String pass = etPassword.getText().toString();

        if(user.equals(USUARIO) && pass.equals(PASSWORD)){
            tvError.setVisibility(View.GONE);
            ivError.setVisibility(View.GONE);
            Intent intent = new Intent(LoginActivity.this,
                    AdministrationActivity.class);
            startActivityForResult(intent, 1);
        }else{
            tvError.setVisibility(View.VISIBLE);
            ivError.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == 1) {
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}