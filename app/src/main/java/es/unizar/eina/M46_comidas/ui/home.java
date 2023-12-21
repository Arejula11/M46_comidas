package es.unizar.eina.M46_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import es.unizar.eina.M46_comidas.R;

public class home extends AppCompatActivity {
      Button buttonPlatos;
      Button buttonPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        buttonPlatos = findViewById(R.id.buttonPlatos);

        buttonPlatos.setOnClickListener(view -> {

              Intent intent = new Intent(this, plates_page.class);
              intent.putExtra("operacion", "getAllPlatos"); // Puedes cambiar "getAllPlatos" según tus necesidades
              intent.putExtra("origen", "plates_page"); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });

        buttonPedidos = findViewById(R.id.buttonPedidos);

        buttonPedidos.setOnClickListener(view -> {

            Intent intent = new Intent(this, orders_page.class);
            intent.putExtra("operacion", "getAllPedidos"); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });


    }
}