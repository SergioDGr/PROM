package com.example.biblioteca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LibroAdapter extends ArrayAdapter<Libro> {

    public LibroAdapter(Context context, List<Libro> libros){
        super(context, 0, libros);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Libro l = getItem(position);

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.libro_item, parent,
                    false);
        }

        TextView tvTitulo = view.findViewById(R.id.tvTitulo);
        TextView tvDescripcion = view.findViewById(R.id.tvDescripcion);
        TextView tvReservado = view.findViewById(R.id.tvReservado);

        assert l != null;
        tvTitulo.setText(l.getTitulo());
        tvDescripcion.setText(l.getDescripcion());
        tvReservado.setText(l.isPrestado()? "Prestado": "No prestado");

        return view;
    }
}
