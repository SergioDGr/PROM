package com.example.fragments;

public class Libro {

    private String titulo;
    private String sinopsis;
    private String isbn;
    private String autor;

    public Libro(String titulo, String sinopsis, String isbn, String autor) {
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.isbn = isbn;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSinopsis() {return sinopsis;}

    public String getIsbn() {
        return isbn;
    }

    public String getAutor() {
        return autor;
    }
}
