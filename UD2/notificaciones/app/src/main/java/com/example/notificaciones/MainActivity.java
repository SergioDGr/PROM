package com.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    static final String CANAL_ID = "mi_canal";
    static final int NOTIFICACION_ID = 1;

    private float resultado;
    private int operacionCorrectas = 0;

    private TextView txtOperacion, txtResultado, txtCantCorrectos;
    private EditText edResultOperacion;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtOperacion = (TextView) findViewById(R.id.txtOperacion);
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        txtCantCorrectos = (TextView) findViewById(R.id.txtCantCorrectos);
        edResultOperacion = (EditText) findViewById(R.id.edResultOperacion);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        txtOperacion.setText(generarOperacion());
        btnEnviar.setOnClickListener( e -> comprobarResultado());
    }

    private String generarOperacion(){

        int num = (int) (Math.random() * 100) + 1;
        int num2 = (int) (Math.random() * 100) + 1;

        int operacion = (int) (Math.random() * 4) + 1;
        String tipoOperacion = "";
        switch (operacion){
            case 1:
                tipoOperacion = "+";
                resultado = num + num2;
                break;
            case 2:
                tipoOperacion = "-";
                resultado = num - num2;
                break;
            case 3:
                tipoOperacion = "*";
                resultado = num * num2;
                break;
            case 4:
                tipoOperacion = "/";
                while (num2 == 0)
                    num2 = (int) (Math.random() * 100) + 1;
                resultado = num / num2;
                break;
        }

        return num + " " + tipoOperacion + " " + num2 + " = ";
    }

    private void comprobarResultado(){
        try {
            float resultado = Float.parseFloat(edResultOperacion.getText().toString());
            if(resultado == this.resultado){
                operacionCorrectas += 1;
                txtCantCorrectos.setText(operacionCorrectas + "");
                txtResultado.setText("El numero es correcto.");
                txtResultado.setTextColor(Color.GREEN);
            }else{
                txtResultado.setText("El numero no es correcto.");
                txtResultado.setTextColor(Color.RED);
            }
            txtOperacion.setText(generarOperacion());
            edResultOperacion.setText("");
        }catch (Exception e){
            txtResultado.setText("No se a pasado ningun numero.");
            txtResultado.setTextColor(Color.RED);
        }
        if(operacionCorrectas == 10){
            //Creamos notificacion
            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            //Creamos el canal. SOLO puede hacerse en dispositivos con ver. 8 o más.
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                NotificationChannel notificationChannel = new NotificationChannel(CANAL_ID,
                        "Notificacion", NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.setDescription("Muestra la respuesta de la actividad " +
                        "notificaciones");
                notificationManager.createNotificationChannel(notificationChannel);
            }
            NotificationCompat.Builder notificacion = new NotificationCompat.Builder(
                    MainActivity.this, CANAL_ID)
                    .setSmallIcon(R.drawable.ic_chats_foreground)
                    .setContentTitle("¡10 Respuestas correctas!")
                    .setContentText("Se a conseguido 10 respuestas correctas.")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setOnlyAlertOnce(true)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            notificationManager.notify(NOTIFICACION_ID, notificacion.build());
        }
    }

}