package es.unizar.eina.M46_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
//import es.unizar.eina.M46_comidas.;
import es.unizar.eina.M46_comidas.*;

import es.unizar.eina.M46_comidas.R;

public class home extends AppCompatActivity {
    Button buttonPlatos;
    Button buttonPedidos;
    Button buttonUnitarios;
    Button buttonSobrecarga;
    Button buttonVolumen;

    Button buttonEliminar;



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


        buttonSobrecarga = findViewById(R.id.buttonSobrecarga);
        buttonUnitarios = findViewById(R.id.buttonUnitario);
        buttonVolumen = findViewById(R.id.buttonVolumen);
        buttonEliminar = findViewById(R.id.buttonEliminarTodo);

        buttonUnitarios.setOnClickListener(view->{
            Toast.makeText(
                    getApplicationContext(),
                    "Realizando pruebas unitarias",
                    Toast.LENGTH_LONG).show();
            UnitTest unitTest = new UnitTest(this.getApplication());
            unitTest.pruebas_unitarias();
        });

        buttonVolumen.setOnClickListener(view -> {
            Toast.makeText(
                    getApplicationContext(),
                    "Realizando prueba de volumen",
                    Toast.LENGTH_LONG).show();
            UnitTest unitTest = new UnitTest(this.getApplication());
            unitTest.prueba_volumen();
        });
        buttonSobrecarga.setOnClickListener(view -> {
            Toast.makeText(
                    getApplicationContext(),
                    "Realizando prueba de sobrecarga",
                    Toast.LENGTH_LONG).show();
            UnitTest unitTest = new UnitTest(this.getApplication());
            unitTest.prueba_sobrecarga();
        });

        buttonEliminar.setOnClickListener(view -> {
            Toast.makeText(
                    getApplicationContext(),
                    "Eliminando todos los platos y pedidos",
                    Toast.LENGTH_LONG).show();
            UnitTest unitTest = new UnitTest(this.getApplication());
            unitTest.eliminar_datos();
        });
    }
}
        
