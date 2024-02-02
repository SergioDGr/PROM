package com.example.examenev2_sergio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdministrationActivity extends AppCompatActivity {

    private EditText etNombre, etSimbolo, etNumAtomico, etEstado;
    private Button btnInsertar, btnModificar, btnBorrar, btnVolver;
    private AlertDialog.Builder dialogBuilder;
    private DatabaseHelper databaseHelper;

    private boolean visualizar = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);

        databaseHelper = new DatabaseHelper(this);

        etNombre = findViewById(R.id.etNombre);
        etSimbolo = findViewById(R.id.etSimbolo);
        etNumAtomico = findViewById(R.id.etNumAtomico);
        etEstado = findViewById(R.id.etEstado);

        btnInsertar = findViewById(R.id.btnInsertar);
        btnModificar = findViewById(R.id.btnModificar);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnVolver = findViewById(R.id.btnVolver);

        btnInsertar.setOnClickListener(e -> click_insertar());
        btnModificar.setOnClickListener(e -> click_modificar());
        btnBorrar.setOnClickListener(e -> click_eliminar());
        btnVolver.setOnClickListener(e -> {
            setResult(1);
            finish();
        });
    }

    private void click_insertar(){
        Elemento e;
        try {
            String nombre = etNombre.getText().toString().toUpperCase();
            if(nombre.isEmpty())
                throw new Exception();
            String simbolo = etSimbolo.getText().toString();
            if(simbolo.isEmpty())
                throw new Exception();
            if(simbolo.length() > 2)
                throw new Exception();
            int num_atomico = Integer.parseInt(etNumAtomico.getText().toString());
            String estado = etEstado.getText().toString().toUpperCase();
            if(!estado.equals("GAS") && !estado.equals("SOLIDO") && !estado.equals("LIQUIDO"))
                throw new Exception();

            e = new Elemento(0, nombre, simbolo, num_atomico, estado);
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Alguno de los campos no es valido",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Elemento existe = databaseHelper.buscarElemento(e.getNombre());
        if(existe != null){
            //Creamos el creador de dialog
            dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setMessage("Ya existe ese elemento.");
            //Se le definine los eventos a los botones
            dialogBuilder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
            //Visualiza el dialogo
            AlertDialog dialog = dialogBuilder.create();
            dialog.show();
        }else{
            databaseHelper.insertarElemento(e);
            limpiar_campos();
            Toast.makeText(getApplicationContext(), "Se ha insertado el elemento",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void click_modificar(){
        Elemento e = databaseHelper.buscarElemento(etNombre.getText().toString().toUpperCase());
        if(e == null){
            //Creamos el creador de dialog
            dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setMessage("El elemento no existe.");
            //Se le definine los eventos a los botones
            dialogBuilder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
            //Visualiza el dialogo
            AlertDialog dialog = dialogBuilder.create();
            dialog.show();
        }else{
            if(visualizar){
                etNombre.setText(e.getNombre());
                etEstado.setText(e.getEstado());
                etSimbolo.setText(e.getSimbolo());
                etNumAtomico.setText(String.valueOf(e.getNum_atomico()));
                visualizar = false;
            }else{
                e.setEstado(etEstado.getText().toString());
                e.setNum_atomico(Integer.parseInt(etNumAtomico.getText().toString()));
                e.setSimbolo(etSimbolo.getText().toString());
                databaseHelper.modificarElemento(e);
                limpiar_campos();
                Toast.makeText(getApplicationContext(), "Se ha modificado el elemento",
                        Toast.LENGTH_SHORT).show();
                visualizar = true;
            }
        }

    }

    private void click_eliminar(){
        Elemento e = databaseHelper.buscarElemento(etNombre.getText().toString().toUpperCase());
        if(e == null){
            //Creamos el creador de dialog
            dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setMessage("El elemento no existe.");
            //Se le definine los eventos a los botones
            dialogBuilder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
            //Visualiza el dialogo
            AlertDialog dialog = dialogBuilder.create();
            dialog.show();
        }else{
            databaseHelper.borrarElemento(e.getNombre());
            limpiar_campos();
            Toast.makeText(getApplicationContext(), "Se ha eliminado el elemento",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiar_campos(){
        etNombre.setText("");
        etEstado.setText("");
        etSimbolo.setText("");
        etNumAtomico.setText("");
    }

}