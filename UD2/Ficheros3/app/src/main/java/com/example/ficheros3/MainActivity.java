package com.example.ficheros3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvWebs;

    private List<Web> lstWeb = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvWebs = findViewById(R.id.lvWebs);
        cargarLista();

        ArrayAdapter<Web> adapter = new WebAdapter(this, lstWeb);

        lvWebs.setAdapter(adapter);
        lvWebs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Web web = (Web) parent.getItemAtPosition(position);
                abrirLink(web.getEnlace());
            }
        });
    }

    private void abrirLink(String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    private void cargarLista(){
        try {
            InputStream fraw =
                    getResources().openRawResource(R.raw.webs_favoritas);
            BufferedReader brin =
                    new BufferedReader(new InputStreamReader(fraw));
            String linea = brin.readLine();
            while (linea != null) {
                String[] campos = linea.split(";");
                int imagen = 0;
                switch (campos[2].trim()){
                    case "yahoo":
                        imagen = R.drawable.yahoo;
                        break;
                    case "google":
                        imagen = R.drawable.google;
                        break;
                    case "bing":
                        imagen = R.drawable.bing;
                        break;
                }

                Web web = new Web(Integer.parseInt(campos[3].trim()),campos[0].trim(),
                        campos[1].trim(), imagen);
                lstWeb.add(web);
                Log.i("Ficheros", linea);
                linea = brin.readLine();
            }
            fraw.close();
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde recurso raw");
        }
    }

}