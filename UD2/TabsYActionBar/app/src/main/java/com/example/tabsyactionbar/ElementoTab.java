package com.example.tabsyactionbar;

public class ElementoTab {

    private String persona;
    private String numero;
    private int img;

    public ElementoTab(String persona, String numero, int img) {
        this.persona = persona;
        this.numero = numero;
        this.img = img;
    }

    public String getPersona() {
        return persona;
    }

    public String getNumero() {
        return numero;
    }

    public int getImg() {
        return img;
    }
}
