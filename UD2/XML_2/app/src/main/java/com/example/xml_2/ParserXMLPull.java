package com.example.xml_2;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ParserXMLPull {
    private URL rssUrl;

    public ParserXMLPull(String url) {
        try {
            this.rssUrl = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tiempo> parse() {
        List<Tiempo> tiempos = null;
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(this.getInputStream(), null);
            int evento = parser.getEventType();
            Tiempo tiempoActual = null;
            String maxTemperatura = null;
            String minTemperatura = null;
            String maxTermica = null;
            String minTermica = null;
            String maxHumedad = null;
            String minHumedad = null;
            while (evento != XmlPullParser.END_DOCUMENT) {
                String etiqueta = parser.getName();
                switch (evento) {
                    case XmlPullParser.START_DOCUMENT:
                        tiempos = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:

                        if (etiqueta.equals("dia")) {
                            tiempoActual = new Tiempo();
                            tiempoActual.setFecha(parser.getAttributeValue(null,
                                    "fecha"));
                        }else if (etiqueta.equals("maxima") && maxTemperatura == null){
                            evento = parser.next();
                            maxTemperatura = parser.getText();
                        }else if(etiqueta.equals("minima") && minTemperatura == null){
                            evento = parser.next();
                            minTemperatura = parser.getText();
                        }else if (etiqueta.equals("maxima") && maxTermica == null){
                            evento = parser.next();
                            maxTermica = parser.getText();
                        }else if(etiqueta.equals("minima") && minTermica == null){
                            evento = parser.next();
                            minTermica = parser.getText();
                        }else if (etiqueta.equals("maxima") && maxHumedad == null){
                            evento = parser.next();
                            maxHumedad = parser.getText();
                        }else if(etiqueta.equals("minima") && minHumedad == null){
                            evento = parser.next();
                            minHumedad = parser.getText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (etiqueta.equals("dia") && tiempoActual != null) {
                            assert tiempos != null;
                            tiempoActual.setMin_temperatura(minTemperatura);
                            tiempoActual.setMax_temperatura(maxTemperatura);
                            tiempoActual.setMin_sens_termica(minTermica);
                            tiempoActual.setMax_sens_termica(maxTermica);
                            tiempoActual.setMin_humedad_relativa(minHumedad);
                            tiempoActual.setMax_humedad_relativa(maxHumedad);
                            tiempos.add(tiempoActual);
                            maxTemperatura = null;
                            minTemperatura = null;
                            maxTermica = null;
                            minTermica = null;
                            maxHumedad = null;
                            minHumedad = null;
                        }
                        break;
                }
                evento = parser.next();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return tiempos;
    }

    private InputStream getInputStream() {
        try {
            return rssUrl.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}