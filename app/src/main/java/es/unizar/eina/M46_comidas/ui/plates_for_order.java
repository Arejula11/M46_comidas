package es.unizar.eina.M46_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import es.unizar.eina.M46_comidas.R;
import es.unizar.eina.M46_comidas.database.Plato;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

public class plates_for_order extends AppCompatActivity {


    private PlatoViewModel mPlatoViewModel;
    RecyclerView mRecyclerView;

    PlatoListAdapter mAdapter;



    Button buttonAtras;
    Button buttonOrdenar;
    String operacion;
    Intent intentRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plates_for_order);
        mRecyclerView = findViewById(R.id.recyclerView);
        intentRecycler = getIntent();
        mAdapter = new PlatoListAdapter(new PlatoListAdapter.PlatoDiff(), getIntent());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);

        Intent intentAnterior = getIntent();
        if (intentAnterior != null && intentAnterior.hasExtra("operacion")) {
            operacion = intentAnterior.getStringExtra("operacion");
            if ("getAllPlatos".equals(operacion)) {
                // Realizar la operación getAllPlatos

                mPlatoViewModel.getAllPlatos().observe(this, platos -> {
                    // Update the cached copy of the notes in the adapter.
                    mAdapter.submitList(platos);
                });
            } else if ("getAllPlatosNombre".equals(operacion)) {
                // Realizar la operación getAllPlatos
                mPlatoViewModel.getAllPlatosNombre().observe(this, platos -> {
                    // Update the cached copy of the notes in the adapter.
                    mAdapter.submitList(platos);
                });
            }else if ("getAllPlatosCategoria".equals(operacion)) {
                // Realizar la operación getAllPlatos
                mPlatoViewModel.getAllPlatosCategoria().observe(this, platos -> {
                    // Update the cached copy of the notes in the adapter.
                    mAdapter.submitList(platos);
                });
            }else if ("getAllPlatosNombreCategoria".equals(operacion)) {
                // Realizar la operación getAllPlatos
                mPlatoViewModel.getAllPlatosNombreCategoria().observe(this, platos -> {
                    // Update the cached copy of the notes in the adapter.
                    mAdapter.submitList(platos);
                });
            }
        }
        buttonAtras = findViewById(R.id.buttonAtras);
        buttonOrdenar = findViewById(R.id.buttonOrdenar);


        buttonAtras.setOnClickListener(view -> {

            if(intentRecycler.getStringExtra("origen") == "plates_for_orderEdit"){
                Intent intent = new Intent(this, edit_order.class);
                intent.putExtra("Pedido", intentRecycler.getSerializableExtra("Pedido"));
                startActivity(intent);
            }else if(intentRecycler.getStringExtra("origen") == "plates_for_orderAdd") {
                Intent intent = new Intent(this, add_order.class);
                intent.putExtra("Pedido", intentRecycler.getSerializableExtra("Pedido"));
                startActivity(intent);
            }
        });
        buttonOrdenar.setOnClickListener(view -> {
            Intent intent = new Intent(this, plates_order.class);
            intent.putExtra("operacion", operacion);
            intent.putExtra("origen", intentRecycler.getStringExtra("origen"));
            startActivity(intent);
        });
    }
}





