package com.example.examenev2_sergio;

public class Elemento {

    private int id;
    private String nombre;
    private String simbolo;
    private int num_atomico;
    private String estado;

    public Elemento(){}

    public Elemento(int id, String nombre, String simbolo, int num_atomico, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.num_atomico = num_atomico;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public int getNum_atomico() {
        return num_atomico;
    }

    public void setNum_atomico(int num_atomico) {
        this.num_atomico = num_atomico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
