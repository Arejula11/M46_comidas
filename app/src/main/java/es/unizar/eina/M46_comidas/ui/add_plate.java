package es.unizar.eina.M46_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import es.unizar.eina.M46_comidas.R;
import es.unizar.eina.M46_comidas.database.Plato;

import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;


public class add_plate extends AppCompatActivity {

    private PlatoViewModel mPlatoViewModel;
    Button buttonAtras;
    Button buttonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plate);

        Spinner spinner = findViewById(R.id.spinnerCategorias);

        String[] opciones = {"PRIMERO", "SEGUNDO", "POSTRE"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        buttonAtras = findViewById(R.id.buttonAtras);
        buttonAdd = findViewById(R.id.buttonAdd);


        buttonAtras.setOnClickListener(view -> {

            Intent intent = new Intent(this, plates_page.class);
            startActivity(intent);
        });

        buttonAdd. setOnClickListener(view -> {
            EditText editTextNombre = findViewById(R.id.editTextNombre);
            EditText editTextIngrediente = findViewById(R.id.editTextIngrediente);
            EditText editTextPrecio = findViewById(R.id.editTextPrecio);
            Spinner spinnerCategoria = findViewById(R.id.spinnerCategorias);

            String nombre = editTextNombre.getText().toString();
            String ingredientes = editTextIngrediente.getText().toString();
            int precio = Integer.parseInt(editTextPrecio.getText().toString());
            String categoriaSeleccionada = spinnerCategoria.getSelectedItem().toString();


            Plato plato = new Plato(nombre,ingredientes,categoriaSeleccionada,precio);
            mPlatoViewModel.insert(plato);
        });


    }
}