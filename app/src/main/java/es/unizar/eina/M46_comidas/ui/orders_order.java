package es.unizar.eina.M46_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import es.unizar.eina.M46_comidas.R;

import android.widget.Button;

public class orders_order extends AppCompatActivity {


    Button buttonTelefono;
    Button buttonCliente;
    Button buttonFecha;
    Button buttonVolver;
    Button buttonHome;

    String orden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_order);

        buttonTelefono = findViewById(R.id.buttonTelefono);
        buttonCliente = findViewById(R.id.buttonCliente);
        buttonFecha = findViewById(R.id.buttonFecha);
        buttonVolver = findViewById(R.id.buttonVolver);
        buttonHome = findViewById(R.id.buttonHome);

        Intent intentaux = getIntent();



        buttonTelefono.setOnClickListener(view -> {

            Intent intent = new Intent(this, orders_page.class);
            intent.putExtra("operacion", "getAllPedidosNumTlfn"); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });
        buttonCliente.setOnClickListener(view -> {

            Intent intent = new Intent(this, orders_page.class);
            intent.putExtra("operacion", "getAllPedidosNombreCliente"); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });
        buttonFecha.setOnClickListener(view -> {

            Intent intent = new Intent(this, orders_page.class);
            intent.putExtra("operacion", "getAllPedidosFecha"); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });


        buttonVolver.setOnClickListener(view -> {

            Intent intent = new Intent(this, orders_page.class);
            intent.putExtra("operacion", intentaux.getStringExtra("operacion")); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });buttonHome.setOnClickListener(view -> {

            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        });
    }
}