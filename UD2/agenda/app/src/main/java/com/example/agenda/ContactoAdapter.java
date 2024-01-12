package com.example.agenda;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactoAdapter extends ArrayAdapter<Contacto> {

    public ContactoAdapter(Context context, List<Contacto> contactos) {
        super(context, 0, contactos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Contacto contacto = getItem(position);

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.contacto_item, parent,
                    false);
        }

        TextView tvNombre = view.findViewById(R.id.tvNombre);
        TextView tvApellido = view.findViewById(R.id.tvApellidos);
        TextView tvCorreo = view.findViewById(R.id.tvCorreo);
        TextView tvTelefono = view.findViewById(R.id.tvTelefono);


        assert contacto != null;
        tvNombre.setText(contacto.getNombre());
        tvApellido.setText(contacto.getApellido());
        tvCorreo.setText(contacto.getCorreo_electronico());
        tvTelefono.setText(String.valueOf(contacto.getTelefono()));

        return view;
    }

}
