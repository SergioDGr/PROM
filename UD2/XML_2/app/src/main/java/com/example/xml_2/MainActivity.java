package com.example.xml_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String url = "https://www.aemet.es/xml/municipios/localidad_01059.xml";
    private ListView lvTiempo;
    private List<Tiempo> tiempos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTiempo = findViewById(R.id.lvTiempo);

        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute(url);
    }

    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {
        protected Boolean doInBackground(String... params) {
            ParserXMLPull xmlPullParser = new ParserXMLPull(params[0]);
            tiempos = xmlPullParser.parse();
            return true;
        }
        protected void onPostExecute(Boolean result) {
            if (tiempos != null) {
                lvTiempo.setAdapter(new TiempoAdapter(getApplicationContext(), tiempos));
            }
        }
    }
}