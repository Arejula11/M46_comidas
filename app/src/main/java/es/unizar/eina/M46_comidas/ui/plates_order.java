package es.unizar.eina.M46_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import es.unizar.eina.M46_comidas.R;

import android.widget.Button;
public class plates_order extends AppCompatActivity {

    Button buttonNombre;
    Button buttonCategoria;
    Button buttonAmbas;
    Button buttonVolver;
    Button buttonHome;

    String orden;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plates_order);
        buttonNombre = findViewById(R.id.buttonNombre);
        buttonCategoria = findViewById(R.id.buttonCategoria);
        buttonAmbas = findViewById(R.id.buttonAmbas);
        buttonVolver = findViewById(R.id.buttonVolver);
        buttonHome = findViewById(R.id.buttonHome);

        Intent intentaux = getIntent();

        buttonNombre.setOnClickListener(view -> {

            Intent intent = new Intent(this, plates_page.class);
            intent.putExtra("operacion", "getAllPlatosNombre"); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });
        buttonCategoria.setOnClickListener(view -> {

            Intent intent = new Intent(this, plates_page.class);
            intent.putExtra("operacion", "getAllPlatosCategoria"); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });
        buttonAmbas.setOnClickListener(view -> {

            Intent intent = new Intent(this, plates_page.class);
            intent.putExtra("operacion", "getAllPlatosNombreCategoria"); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });
        buttonVolver.setOnClickListener(view -> {

            Intent intent = new Intent(this, plates_page.class);
            intent.putExtra("operacion", intentaux.getStringExtra("operacion")); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });buttonHome.setOnClickListener(view -> {

            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        });

    }
}