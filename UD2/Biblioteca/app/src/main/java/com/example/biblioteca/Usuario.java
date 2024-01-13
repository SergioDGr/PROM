package com.example.biblioteca;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

    private int id;
    private String usuario;
    private String pass;
    private int libro_prestado;

    public Usuario(String usuario, String pass) {
        this.usuario = usuario;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getLibro_prestado() {
        return libro_prestado;
    }

    public void setLibro_prestado(int libro_prestado) {
        this.libro_prestado = libro_prestado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(usuario, usuario1.usuario) && Objects.equals(pass, usuario1.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, pass);
    }
}
