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
import androidx.lifecycle.ViewModelProvider;
import android.widget.Toast;



public class add_plate extends AppCompatActivity {

    private PlatoViewModel mPlatoViewModel;
    Button buttonAtras;
    Button buttonAdd;

    EditText editTextNombre;
    EditText editTextIngrediente;
    EditText editTextPrecio;
    Spinner spinnerCategoria;

    private String nombre;
    private String ingredientes;
    private Double precio;

    Spinner spinner;

    String[] opciones = {"PRIMERO", "SEGUNDO", "POSTRE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plate);

        spinner = findViewById(R.id.spinnerCategorias);

      

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);
        Intent intentaux = getIntent();
        buttonAtras = findViewById(R.id.buttonAtras);
        buttonAdd = findViewById(R.id.buttonAdd);


        buttonAtras.setOnClickListener(view -> {

            Intent intent = new Intent(this, plates_page.class);
            intent.putExtra("operacion", intentaux.getStringExtra("operacion")); // Puedes cambiar "getAllPlatos" según tus necesidades
            intent.putExtra("origen", "plates_page"); // Puedes cambiar "getAllPlatos" según tus necesidades

            startActivity(intent);
        });

        buttonAdd. setOnClickListener(view -> {
            editTextNombre = findViewById(R.id.editTextNombre);
            editTextIngrediente = findViewById(R.id.editTextIngrediente);
            editTextPrecio = findViewById(R.id.editTextPrecio);
            spinnerCategoria = findViewById(R.id.spinnerCategorias);

            nombre = editTextNombre.getText().toString();
            ingredientes = editTextIngrediente.getText().toString();

            try{
                 precio = Double.parseDouble(editTextPrecio.getText().toString());
            }catch(NumberFormatException e){
                 precio = -1.0;
            }

            String categoriaSeleccionada = spinnerCategoria.getSelectedItem().toString();

            if (nombre.isEmpty() || ingredientes.isEmpty()|| precio == -1.0){
                Toast.makeText(getApplicationContext(), "Error: campos sin rellenar", Toast.LENGTH_LONG).show();

            }else {
                Plato plato = new Plato(nombre,ingredientes,categoriaSeleccionada,precio);
                mPlatoViewModel.insert(plato);
                Intent intent = new Intent(this, plates_page.class);
                intent.putExtra("operacion", intentaux.getStringExtra("operacion"));
                intent.putExtra("origen", "plates_page");

                startActivity(intent);
            }

        });


    }
}
