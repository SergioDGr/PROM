package com.example.ficheros3;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;


public class WebAdapter extends ArrayAdapter<Web> {

    public WebAdapter(Context context, List<Web> webs) {
        super(context, R.layout.web_item, webs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Web web = getItem(position);

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.web_item, parent,
                    false);
        }

        TextView tvNombre = view.findViewById(R.id.tvNombre);
        TextView tvEnlace = view.findViewById(R.id.tvEnlace);
        ImageView ivImagen = view.findViewById(R.id.ivLogo);

        tvNombre.setText(web.getNombre());
        tvEnlace.setText(web.getEnlace());
        ivImagen.setImageResource(web.getLogo());

        return view;
    }
}
