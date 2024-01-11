package com.example.ficheros3;

public class Web {
    private int id;
    private String nombre;
    private String enlace;
    private int logo;

    public Web(int id, String nombre, String enlace, int logo) {
        this.id = id;
        this.nombre = nombre;
        this.enlace = enlace;
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEnlace() {
        return enlace;
    }

    public int getLogo() {
        return logo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
