package com.example.xml;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public String url = "https://www.europapress.es/rss/rss.aspx";
    private ListView lvNoticias;
    private List<Noticia> noticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCargarNoticias;

        lvNoticias = findViewById(R.id.lvNoticias);
        btnCargarNoticias = findViewById(R.id.btnCargarNoticias);

        lvNoticias.setOnItemClickListener((parent, view, position, id) -> {
           Noticia n = noticias.get(position);
           Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(n.getLink()));
           startActivity(browserIntent);
        });
        btnCargarNoticias.setOnClickListener(e -> cargarXMLConSAX());
    }

    //Con la propiedad onClick en los botones
    public void cargarXMLConSAX (){
        CargarXmlTask tarea = new CargarXmlTask(MainActivity.this);
        tarea.execute(url);
    }

    private class CargarXmlTask extends AsyncTask<String, Integer, Boolean> {
        Context context;
        CargarXmlTask(Context context){
            this.context = context;
        }

        protected Boolean doInBackground(String... params) {
            RssParserSAX saxparser = new RssParserSAX(params[0]);
            noticias = saxparser.parse();
            return true;
        }

        @SuppressLint("SetTextI18n")
        protected void onPostExecute(Boolean result) {
            if (noticias != null) {
                List<String> lstNoticias = new ArrayList<>();
                for (int i = 0; i < noticias.size(); i++) {
                    lstNoticias.add(noticias.get(i).getTitulo());
                }
                lvNoticias.setAdapter(new ArrayAdapter<>(context,
                        android.R.layout.simple_list_item_1, lstNoticias));
            }
        }

    }


}