package es.unizar.eina.M46_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import es.unizar.eina.M46_comidas.R;
import es.unizar.eina.M46_comidas.database.Plato;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class edit_plate extends AppCompatActivity {

    private PlatoViewModel mPlatoViewModel;

    Button buttonCancelar;
    Button buttonConfirmar;
    String[] opciones = {"PRIMERO", "SEGUNDO", "POSTRE"};
    TextView editTextNombre;
    TextView editTextIngredientes;
    TextView editTextPrecio;
    Spinner spinnerCategoria;
    Plato plato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_plate);
        spinner = findViewById(R.id.spinnerCategorias);

        

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);


        editTextNombre = findViewById(R.id.editTextNombre);
        editTextIngredientes = findViewById(R.id.editTextIngrediente);
        editTextPrecio = findViewById(R.id.editTextPrecio);
        spinnerCategoria = findViewById(R.id.spinnerCategorias);

        Intent intentaux = getIntent();
        plato = (Plato) intentaux.getSerializableExtra("Objeto");


        editTextNombre.setText(plato.getNombre().toString());
        editTextIngredientes.setText(plato.getIngredientes().toString());
        editTextPrecio.setText(String.valueOf(plato.getPrecio()));
        int categoriaIndex = Arrays.asList(opciones).indexOf(plato.getCategoria().toUpperCase());
        spinnerCategoria.setSelection(categoriaIndex);

        buttonCancelar = findViewById(R.id.buttonCancelar);
        buttonConfirmar = findViewById(R.id.buttonConfirmar);

        buttonCancelar.setOnClickListener( view -> {

            Intent intent = new Intent(this, plato_description.class);
            intent.putExtra("Objeto", plato);
            intent.putExtra("operacion", intentaux.getStringExtra("operacion"));
            startActivity(intent);
        });
        buttonConfirmar.setOnClickListener( view -> {
            String nombre = editTextNombre.getText().toString();
            String ingredientes = editTextIngrediente.getText().toString();
            Double precio;
            try{
                precio = Double.parseDouble(editTextPrecio.getText().toString());
            }catch(NumberFormatException e){
                precio = -1.0;
            }
            String categoriaSeleccionada = spinnerCategoria.getSelectedItem().toString();


            if (nombre.isEmpty() || ingredientes.isEmpty()|| precio == -1.0){
                Toast.makeText(getApplicationContext(), "Error: campos sin rellenar", Toast.LENGTH_LONG).show();

            }else {

                Plato platoMod = new Plato(nombre, ingredientes, categoriaSeleccionada, precio);
                platoMod.setId(plato.getId());
                mPlatoViewModel.update(platoMod);
                Intent intent = new Intent(this, plato_description.class);
                intent.putExtra("Objeto", platoMod);
                intent.putExtra("operacion", intentaux.getStringExtra("operacion"));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
