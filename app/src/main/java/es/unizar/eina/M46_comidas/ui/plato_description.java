package es.unizar.eina.M46_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import es.unizar.eina.M46_comidas.R;
import es.unizar.eina.M46_comidas.database.Plato;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class plato_description extends AppCompatActivity {

    Button buttonAtras;
    Button buttonEditar;
    Button buttonEliminar;
    private PlatoViewModel mPlatoViewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato_description);

        TextView textViewNombre = findViewById(R.id.textViewNombre2);
        TextView textViewIngredientes = findViewById(R.id.textViewIngrediente2);
        TextView textViewPrecio = findViewById(R.id.textViewPrecio2);
        TextView textViewCategoria = findViewById(R.id.textViewCategoria2);

        Intent intentaux = getIntent();
        Plato plato = (Plato) intentaux.getSerializableExtra("Objeto");

        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);


        textViewNombre.setText(plato.getNombre().toString());
        textViewIngredientes.setText(plato.getIngredientes().toString());
        textViewPrecio.setText(String.valueOf(plato.getPrecio()));
        textViewCategoria.setText(plato.getCategoria().toString());


        buttonAtras = findViewById(R.id.buttonAtras);
        buttonEditar = findViewById(R.id.buttonEditar);
        buttonEliminar = findViewById(R.id.buttonEliminar);
        buttonAtras.setOnClickListener(view -> {
            Intent intent = new Intent(this, plates_page.class);
            intent.putExtra("operacion", intentaux.getStringExtra("operacion")); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });
        buttonEditar.setOnClickListener(view -> {
            Intent intent = new Intent(this, edit_plate.class);
            intent.putExtra("Objeto", plato); // Puedes cambiar "getAllPlatos" según tus necesidades
            intent.putExtra("operacion", intentaux.getStringExtra("operacion")); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });
        buttonEliminar.setOnClickListener(view -> {
            Toast.makeText(
                    getApplicationContext(),
                    "Deleting " + plato.getNombre(),
                    Toast.LENGTH_LONG).show();
            mPlatoViewModel.delete(plato);
            Intent intent = new Intent(this, plates_page.class);
            intent.putExtra("operacion", intentaux.getStringExtra("operacion")); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });


    }
}
