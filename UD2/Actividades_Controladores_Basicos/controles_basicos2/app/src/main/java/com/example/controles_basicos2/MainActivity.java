package com.example.controles_basicos2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btnEncender, btnApagar;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        btnEncender  = (Button)findViewById(R.id.btnEncender);
        btnApagar = (Button)findViewById(R.id.btnApagar);

        btnEncender.setOnClickListener(e -> encender());
        btnApagar.setOnClickListener(e -> apagar());
    }

    private void apagar(){
        linearLayout.setBackgroundColor(Color.BLACK);
    }

    private void encender(){
        linearLayout.setBackgroundColor(Color.WHITE);
    }


}