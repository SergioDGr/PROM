package com.example.controles_seleccion_2;

import android.media.Image;
import android.widget.ImageView;

public class Web {
    private int id;
    private String nombre;
    private String url;
    private int img ;

    public Web(int id, String nombre, String url, int img) {
        this.id = id;
        this.nombre = nombre;
        this.url = url;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl() {
        return url;
    }

    public int getImg() {
        return img;
    }
}
