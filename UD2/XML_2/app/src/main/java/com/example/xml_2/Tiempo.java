package com.example.xml_2;

public class Tiempo {

    private String fecha;
    private String max_temperatura, min_temperatura;
    private String max_sens_termica, min_sens_termica;
    private String max_humedad_relativa, min_humedad_relativa;

    public Tiempo(){

    }

    public Tiempo(String fecha, String max_temperatura, String min_temperatura,
                  String max_sens_termica, String min_sens_termica, String max_humedad_relativa,
                  String min_humedad_relativa) {
        this.fecha = fecha;
        this.max_temperatura = max_temperatura;
        this.min_temperatura = min_temperatura;
        this.max_sens_termica = max_sens_termica;
        this.min_sens_termica = min_sens_termica;
        this.max_humedad_relativa = max_humedad_relativa;
        this.min_humedad_relativa = min_humedad_relativa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMax_temperatura() {
        return max_temperatura;
    }

    public void setMax_temperatura(String max_temperatura) {
        this.max_temperatura = max_temperatura;
    }

    public String getMin_temperatura() {
        return min_temperatura;
    }

    public void setMin_temperatura(String min_temperatura) {
        this.min_temperatura = min_temperatura;
    }

    public String getMax_sens_termica() {
        return max_sens_termica;
    }

    public void setMax_sens_termica(String max_sens_termica) {
        this.max_sens_termica = max_sens_termica;
    }

    public String getMin_sens_termica() {
        return min_sens_termica;
    }

    public void setMin_sens_termica(String min_sens_termica) {
        this.min_sens_termica = min_sens_termica;
    }

    public String getMax_humedad_relativa() {
        return max_humedad_relativa;
    }

    public void setMax_humedad_relativa(String max_humedad_relativa) {
        this.max_humedad_relativa = max_humedad_relativa;
    }

    public String getMin_humedad_relativa() {
        return min_humedad_relativa;
    }

    public void setMin_humedad_relativa(String min_humedad_relativa) {
        this.min_humedad_relativa = min_humedad_relativa;
    }
}
