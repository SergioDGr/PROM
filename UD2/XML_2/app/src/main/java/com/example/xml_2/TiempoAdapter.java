package com.example.xml_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TiempoAdapter extends ArrayAdapter<Tiempo> {

    public TiempoAdapter(Context context, List<Tiempo> tiempos){
        super(context, R.layout.tiempo_item, tiempos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Tiempo tiempo = getItem(position);

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.tiempo_item, parent,
                    false);
        }

        TextView tvFecha = view.findViewById(R.id.tvFecha);
        TextView tvMinTemperatura = view.findViewById(R.id.tvMin_temperatura);
        TextView tvMaxTemperatura = view.findViewById(R.id.tvMax_temperatura);
        TextView tvMinSensacion = view.findViewById(R.id.tvMin_sens_termica);
        TextView tvMaxSensacion = view.findViewById(R.id.tvMax_sens_termica);
        TextView tvMinHumedad = view.findViewById(R.id.tvMin_humedad_relativa);
        TextView tvMaxHumedad = view.findViewById(R.id.tvMax_humedad_relativa);

        assert tiempo != null;
        tvFecha.setText(tiempo.getFecha());
        tvMaxHumedad.setText(tiempo.getMax_humedad_relativa());
        tvMinHumedad.setText(tiempo.getMin_humedad_relativa());
        tvMaxSensacion.setText(tiempo.getMax_sens_termica());
        tvMinSensacion.setText(tiempo.getMin_sens_termica());
        tvMaxTemperatura.setText(tiempo.getMax_temperatura());
        tvMinTemperatura.setText(tiempo.getMin_temperatura());

        return view;
    }
}
