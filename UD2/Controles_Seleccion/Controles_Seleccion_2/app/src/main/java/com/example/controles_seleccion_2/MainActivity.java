package com.example.controles_seleccion_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.transition.Hold;

public class MainActivity extends AppCompatActivity {

    private Web[] datosTitular = new Web[] {
            new Web(1, "Youtube", "https://www.youtube.com/?gl=ES&hl=es",
                    R.drawable.youtube),
            new Web(2, "Anilist", "https://anilist.co", R.drawable.anilist_logo),
            new Web(3, "Lista de manga", "https://www.listadomanga.es/",
                    R.drawable.listado_manga),
            new Web(4, "AMQ", "https://animemusicquiz.com/", R.drawable.amq)};
    private ListView lvWebs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvWebs = (ListView) findViewById(R.id.lvWebs);


        ArrayAdapter<Web> adaptadorListView = new AdaptadorWeb(this, datosTitular);
        lvWebs.setAdapter(adaptadorListView);
    }

    class AdaptadorWeb extends ArrayAdapter<Web> {

        public AdaptadorWeb(@NonNull Context context, Web[] datos) {
            super(context, R.layout.listitem_web, datos);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


            View item = convertView;
            ViewHolder holder;

            if(item == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                item = inflater.inflate(R.layout.listitem_web, null);

                holder = new ViewHolder();
                holder.id = (TextView)item.findViewById(R.id.txtId);
                holder.nombre = (TextView)item.findViewById(R.id.txtNombre);
                holder.url = (TextView) item.findViewById(R.id.txtUrl);
                holder.img = (ImageView) item.findViewById(R.id.img);

                item.setTag(holder);
            }else
                holder = (ViewHolder) item.getTag();

            holder.id.setText(datosTitular[position].getId() + "");
            holder.nombre.setText(datosTitular[position].getNombre());
            holder.url.setText(datosTitular[position].getUrl());
            holder.img.setImageResource(datosTitular[position].getImg());

            return (item);
        }

    }

    static class ViewHolder {
        TextView id;
        TextView nombre;
        TextView url;
        ImageView img;
    }
}
