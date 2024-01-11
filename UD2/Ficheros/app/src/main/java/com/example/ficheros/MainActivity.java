package com.example.ficheros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE = 1;
    private Button btnAddFicheroInterno, btnLeerFicheroInterno, btnDelFicheroInterno;
    private Button btnAddFicheroExterno, btnLeerFicheroExterno, btnDelFicheroExterno;
    private Button btnLeerRecurso;
    private EditText etFileContent;
    private TextView tvVisualizarContenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddFicheroExterno = findViewById(R.id.btnAddFicheroExterno);
        btnAddFicheroInterno = findViewById(R.id.btnAddFicheroInterno);
        btnDelFicheroExterno = findViewById(R.id.btnDelFicheroExterno);
        btnDelFicheroInterno = findViewById(R.id.btnDelFicheroInterno);
        btnLeerFicheroExterno = findViewById(R.id.btnLeerFicheroExterno);
        btnLeerFicheroInterno = findViewById(R.id.btnLeerFicheroInterno);
        btnLeerRecurso = findViewById(R.id.btnLeerRecurso);
        etFileContent = findViewById(R.id.etFileContent);
        tvVisualizarContenido = findViewById(R.id.tvVisualizarContenido);

        btnAddFicheroInterno.setOnClickListener(e -> escribirFicheroInterno());
        btnAddFicheroExterno.setOnClickListener(e -> escribirFicheroExterno());
        btnLeerFicheroInterno.setOnClickListener(e -> leerFicheroInterno());
        btnLeerFicheroExterno.setOnClickListener(e -> leerDeSD());
        btnLeerRecurso.setOnClickListener(e -> leerRecurso());
        btnDelFicheroInterno.setOnClickListener(e -> eliminarFicheroInterno());
        btnDelFicheroExterno.setOnClickListener(e -> eliminarFicheroExterno());
    }

    private void escribirFicheroInterno() {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput(
                    "fichero_interno.txt", Context.MODE_PRIVATE));
            osw.write(etFileContent.getText().toString() + "\n");
            Log.i("Ficheros", "Escribiendo en fichero de memoria interna");
            osw.close();
        } catch (Exception e) {
            Log.e("Ficheros", "ERROR!! al escribir fichero a memoria interna");
        }
    }

    private void escribirFicheroExterno() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            checkPermissions();
            // La tarjeta SD está disponible y se puede escribir en ella
            escribirEnSD();
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // La tarjeta SD está disponible pero solo se puede leer, no escribir
            Toast.makeText(getApplicationContext(),"La tarjeta SD esta disponible, Solo lectura",
                    Toast.LENGTH_SHORT).show();
        } else {
            // La tarjeta SD no está disponible
            Toast.makeText(getApplicationContext(),"La tarjeta SD no esta disponible",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void leerFicheroInterno() {
        tvVisualizarContenido.setText("");
        try {
            BufferedReader fin = new BufferedReader(
                    new InputStreamReader(openFileInput("fichero_interno.txt")));
            String texto = "";
            String linea = fin.readLine();
            while (linea != null) {
                texto = texto + linea + "\n";
                Log.i("Ficheros", linea);
                linea = fin.readLine();
            }
            fin.close();
            tvVisualizarContenido.setText(texto);
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }
    }

    private void leerRecurso() {
        try {
            InputStream fraw =
                    getResources().openRawResource(R.raw.fichero_raw);
            BufferedReader brin =
                    new BufferedReader(new InputStreamReader(fraw));
            String texto = "";
            String linea = brin.readLine();
            while (linea != null) {
                texto = texto + linea + "\n";
                Log.i("Ficheros", linea);
                linea = brin.readLine();
            }
            fraw.close();
            tvVisualizarContenido.setText(texto);
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde recurso raw");
        }
    }

    private void escribirEnSD() {
        File file = new File(getExternalFilesDir(null), "fichero_externo.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(etFileContent.getText().toString().getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eliminarFicheroExterno(){
        File file = new File(getExternalFilesDir(null), "fichero_externo.txt");
        if(file.exists()){
            file.delete();
            Toast.makeText(this, "Se a eliminado el fichero", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "No existe el fichero", Toast.LENGTH_SHORT).show();
    }

    private void eliminarFicheroInterno(){
        File dir = getFilesDir();
        File f = new File(dir, "fichero_interno.txt");

        if(f.exists()) {
            f.delete();
            Toast.makeText(this, "Se a eliminado el fichero", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "No se a eliminado el fichero", Toast.LENGTH_SHORT).show();
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE);
        } else {
            // Permiso ya concedido, puedes continuar con la escritura en la tarjeta SD
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, puedes continuar con la escritura en la tarjeta SD
            } else {
                // Permiso denegado, no puedes escribir en la tarjeta SD
                Toast.makeText(this, "No se concedió el permiso para escribir en la tarjeta SD", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void leerDeSD() {
        tvVisualizarContenido.setText("");
        try {
            File ruta_sd = Environment.getExternalStorageDirectory();
            File f = new File(ruta_sd.getAbsolutePath(), "fichero_externo.txt");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(f)));
            String texto = "";
            String linea = br.readLine();
            while (linea != null) {
                texto = texto + linea + "\n";
                linea = br.readLine();
            }
            br.close();
            Log.i("Ficheros", texto);
            tvVisualizarContenido.setText(texto);
        } catch (Exception ex) {
            Log.e("Ficheros", "ERROR!! en la lectura del fichero en SD");
        }
    }

}
