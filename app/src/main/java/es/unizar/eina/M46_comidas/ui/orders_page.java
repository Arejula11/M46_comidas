package es.unizar.eina.M46_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import es.unizar.eina.M46_comidas.R;
import android.widget.Button;
import android.content.Intent;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;



public class orders_page extends AppCompatActivity {

    Button buttonHome;

    private PedidoViewModel mPedidoViewModel;

    String operacion;
    
    PedidoListAdapter mAdapter;
    
    RecyclerView mRecyclerView;



    // private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_page);
        
        mRecyclerView = findViewById(R.id.recyclerView);

        RecyclerView mRecyclerView;
        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);
        
        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new PedidoListAdapter(new PedidoListAdapter.PedidoDiff(), getIntent());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);
        
        Intent intentAnterior = getIntent();
        if (intentAnterior != null && intentAnterior.hasExtra("operacion")) {
            operacion = intentAnterior.getStringExtra("operacion");
            if ("getAllPedidos".equals(operacion)) {
                // Realizar la operación getAllPlatos

                mPedidoViewModel.getAllPedidos().observe(this, pedidos -> {
                    // Update the cached copy of the notes in the adapter.
                    mAdapter.submitList(pedidos);
                });
            }
        }
        buttonHome = findViewById(R.id.buttonHome);
        
        buttonHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, home.class);
            intent.putExtra("operacion", operacion);
            startActivity(intent);
        });
        
    }
}