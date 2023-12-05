package com.example.dialogos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnIniciar, btnSalir;

    private EditText etUser, etPass;

    private String user = "usuario1", password = "123456";

    private AlertDialog.Builder dialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Conseguimos los elementos de la ventana
        btnIniciar = findViewById(R.id.btnIniciar);
        btnSalir = findViewById(R.id.btnSalir);

        //Añadimos los eventos a los botones
        btnIniciar.setOnClickListener(e ->{iniciarDialogo();});
        btnSalir.setOnClickListener(e ->{salirDialogo();});
    }

    private void iniciarDialogo() {
        //Creamos el creador de dialog
        dialogBuilder = new AlertDialog.Builder(this);
        //Le añadimos un titulo y un mensaje
        dialogBuilder.setTitle("Inicio Sesion");
        dialogBuilder.setMessage("Introduzca el usuario y la contraseña");
        //Conseguimos la ventana del dialogo
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_login, null);
        dialogBuilder.setView(dialogView);
        //Conseguimos los elementos del dialogo
        etUser = dialogView.findViewById(R.id.etUser);
        etPass = dialogView.findViewById(R.id.etPass);

        //Se le definine los eventos a los botones
        dialogBuilder.setPositiveButton("Iniciar", (dialog, which) -> {
            validarInicioSesion();
        });
        dialogBuilder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        //Visualiza el dialogo
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    private void validarInicioSesion(){
        String usuario = etUser.getText().toString();
        String pass = etPass.getText().toString();
        //En caso que el usuario y la contraseña estan bien
        if (user.equals(usuario) && password.equals(pass))
            Toast.makeText(MainActivity.this, "Sesion Iniciada",
                    Toast.LENGTH_SHORT).show();
        else //En caso de que sean incorrectas
            Toast.makeText(MainActivity.this, "Error al logearse",
                    Toast.LENGTH_SHORT).show();
    }

    private void salirDialogo() {
        //Se instancia el creado de dialogos
        dialogBuilder = new AlertDialog.Builder(this);
        //Se pone el titulo y el mensaje
        dialogBuilder.setTitle("Salir");
        dialogBuilder.setMessage("¿Estas seguro que realmente quiere salir?");

        //se le define los eventos de los botones
        dialogBuilder.setPositiveButton("Salir", (dialog, which) -> finish());
        dialogBuilder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        //Visualiza el dialogo
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
}