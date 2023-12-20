package es.unizar.eina.M46_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import es.unizar.eina.M46_comidas.R;
import es.unizar.eina.M46_comidas.database.Plato;

import android.widget.Button;
import android.widget.TextView;


public class plato_description extends AppCompatActivity {

    Button buttonAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato_description);

        TextView textViewNombre = findViewById(R.id.textViewNombre2);
        Intent intentaux = getIntent();
        Plato plato = (Plato) intentaux.getSerializableExtra("Objeto");
        String nombre = plato.getNombre();
        String ingredientes = plato.getIngredientes();
        int precio = plato.getPrecio();
        String categoria = plato.getCategoria();


        buttonAtras = findViewById(R.id.buttonAtras);
        buttonAtras.setOnClickListener(view -> {
            Intent intent = new Intent(this, plates_page.class);
            intent.putExtra("operacion", "getAllPlatosNombre"); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });


    }
}