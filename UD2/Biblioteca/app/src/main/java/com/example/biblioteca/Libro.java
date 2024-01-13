package com.example.biblioteca;

import java.io.Serializable;

public class Libro implements Serializable {

    private int id;
    private String titulo;
    private String descripcion;
    private boolean prestado;

    public Libro(int id, String titulo, String descripcion, boolean prestado){
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prestado = prestado;
    }

    public Libro(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }
}
