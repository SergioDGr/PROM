package com.example.tabsyactionbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Menu menu;
    private ListView lvChats, lvLlamadas, lvContactos;

    private ElementoTab[] datosElemento = new ElementoTab[]{
            new ElementoTab("Elba Jinon", "625358292", R.drawable.ic_launcher_background),
            new ElementoTab("Benito Camela", "685054269", R.drawable.ic_launcher_background),
            new ElementoTab("Jon Damian", "685201502", R.drawable.ic_launcher_background),
            new ElementoTab("Pablo Gonzalez", "684209571", R.drawable.ic_launcher_background),
            new ElementoTab("Ander Panera", "621483457", R.drawable.ic_launcher_background),
            new ElementoTab("Daniel Montoya", "605845067", R.drawable.ic_launcher_background),
            new ElementoTab("Edorta Rodriguez", "690740249", R.drawable.ic_launcher_background),
            new ElementoTab("Elena Nito", "625402545", R.drawable.ic_launcher_background),
            new ElementoTab("Elma ricon", "608424853", R.drawable.ic_launcher_background),
            new ElementoTab("Tomás Turbado", "697782023", R.drawable.ic_launcher_background),
    };

    private ElementoTab[] datosElemento2 = new ElementoTab[]{
            new ElementoTab("Elba Jinon", "625358292", R.drawable.ic_launcher_background),
            new ElementoTab("Benito Camela", "685054269", R.drawable.ic_launcher_background),
            new ElementoTab("Jon Damian", "685201502", R.drawable.ic_launcher_background)
    };

    private ElementoTab[] datosElemento3 = new ElementoTab[]{
            new ElementoTab("Jon Damian", "685201502", R.drawable.ic_launcher_background),
            new ElementoTab("Pablo Gonzalez", "684209571", R.drawable.ic_launcher_background),
            new ElementoTab("Ander Panera", "621483457", R.drawable.ic_launcher_background),
            new ElementoTab("Daniel Montoya", "605845067", R.drawable.ic_launcher_background),
            new ElementoTab("Edorta Rodriguez", "690740249", R.drawable.ic_launcher_background),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvChats = (ListView) findViewById(R.id.lvChats);
        lvContactos = (ListView) findViewById(R.id.lvContactos);
        lvLlamadas = (ListView) findViewById(R.id.lvLlamadas);

       ArrayAdapter<ElementoTab> adapter = new AdaptadorTab(this, datosElemento,
               true, false);
        ArrayAdapter<ElementoTab> adapter2 = new AdaptadorTab(this, datosElemento2,
                false, true);
        ArrayAdapter<ElementoTab> adapter3 = new AdaptadorTab(this, datosElemento3,
                false, false);

        lvLlamadas.setAdapter(adapter);
        lvChats.setAdapter(adapter2);
        lvContactos.setAdapter(adapter3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Tabhost
        TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec = tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tabLlamadas);
        spec.setIndicator("Llamadas");
        tabs.addTab(spec);
        spec = tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tabChats);
        spec.setIndicator("Chats");
        tabs.addTab(spec);
        spec = tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tabContactos);
        spec.setIndicator("Contactos");
        tabs.addTab(spec);
        tabs.setCurrentTab(0);
        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                switch (s) {
                    case "mitab1":
                        menu.getItem(1).setIcon(R.drawable.ic_otro);
                        break;
                    case "mitab2":
                        menu.getItem(1).setIcon(R.drawable.ic_otro2);
                        break;
                    case "mitab3":
                        menu.getItem(1).setIcon(R.drawable.ic_otro3);
                        break;
                }

                Log.i("AndroidTabsDemo", "Pulsada pestaña: " + s);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        this.menu = menu;

        return true;
    }

    class AdaptadorTab extends ArrayAdapter<ElementoTab> {

        private ElementoTab[] datos;

        private boolean isLlamada, isChat;
        private boolean bien = false;

        public AdaptadorTab(@NonNull Context context, ElementoTab[] datos, boolean isLlamada,
                            boolean isChat) {
            super(context, R.layout.listitem_elemento_tab, datos);
            this.datos = datos;
            this.isLlamada = isLlamada;
            this.isChat = isChat;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


            View item = convertView;
            ViewHolder holder;

            if (item == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                item = inflater.inflate(R.layout.listitem_elemento_tab, null);

                holder = new ViewHolder();
                holder.nombre = (TextView) item.findViewById(R.id.txtNombre);
                holder.telefono = (TextView) item.findViewById(R.id.txtTelefono);
                holder.img = (ImageView) item.findViewById(R.id.img);
                holder.img2 = (ImageView) item.findViewById(R.id.imgActividad);

                item.setTag(holder);
            } else
                holder = (ViewHolder) item.getTag();

            holder.nombre.setText(datos[position].getPersona());
            holder.telefono.setText(datos[position].getNumero());
            holder.img.setImageResource(datos[position].getImg());
            if(isLlamada ){
                if(bien)
                    holder.img2.setImageResource(R.drawable.phone);
                else
                    holder.img2.setImageResource(R.drawable.phone_callback);
                bien = !bien;
            }

            if(isChat){
                if(bien)
                    holder.img2.setImageResource(R.drawable.circle);
                bien = !bien;
            }

            return (item);
        }
    }
    static class ViewHolder {
        TextView nombre;
        TextView telefono;
        ImageView img;
        ImageView img2;
    }

}