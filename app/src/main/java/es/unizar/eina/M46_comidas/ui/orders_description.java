package es.unizar.eina.M46_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.LiveData;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.unizar.eina.M46_comidas.R;
import es.unizar.eina.M46_comidas.database.Pedido;
import es.unizar.eina.M46_comidas.database.Racion;

public class orders_description extends AppCompatActivity {
    Button buttonAtras;
    Button buttonEditar;
    Button buttonEliminar;
    RacionListAdapter mAdapter;

    private PedidoViewModel mPedidoViewModel;
    private RacionViewModel mRacionViewModel;
    private PlatoViewModel mPlatoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_description);

        TextView textViewNombreCliente = findViewById(R.id.textViewNombreClienteFill);
        TextView textViewPrecio = findViewById(R.id.textViewPrecioFill);
        TextView textViewTelefono = findViewById(R.id.textViewTelefonoFill);
        TextView textViewFecha = findViewById(R.id.textViewFechaFill);

        Intent intentaux = getIntent();
        Pedido pedido = (Pedido) intentaux.getSerializableExtra("Pedido");
        intentaux.putExtra("invisible", true);

        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);
        mRacionViewModel = new ViewModelProvider(this).get(RacionViewModel.class);
        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);

        RecyclerView mRecyclerView;
        mRecyclerView = findViewById(R.id.recyclerViewPlates);
        mAdapter = new RacionListAdapter(new RacionListAdapter.RacionDiff(), getIntent());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRacionViewModel.getAllRaciones(pedido.getId()).observe(this, raciones -> {
            // Update the cached copy of the notes in the adapter.
            //mAdapter.submitList(raciones);

            //rellena una lista
            List<RacionVisual> racionesVisualesAux= new ArrayList<>();

            //llamada getNombre
            for(Racion racion : raciones){
                mPlatoViewModel.getPlatoId(racion.getPlatoId()).observe(this, plato -> {
                    RacionVisual racionVisual  = new RacionVisual(plato.getNombre(),racion, plato.getPrecio());
                    racionesVisualesAux.add(racionVisual);
                    mAdapter.submitList(racionesVisualesAux);

                });
            }
            //
        });

        // mAdapter.submitList(raciones);
        // mAdapter.getCurrentList();

        //rellenar una lista con todos las raciones

        // se rellena el racionesVisual con esa lista se hace una llamada a la bd



        textViewNombreCliente.setText(pedido.getNombrecliente().toString());
        textViewPrecio.setText(String.valueOf(pedido.getPrecio()));
        textViewTelefono.setText(pedido.getTel().toString());
        textViewFecha.setText("Fecha: " + pedido.getFecha().toString().substring(0,4) + '-'
                + pedido.getFecha().toString().substring(4,6) + '-' + pedido.getFecha().toString().substring(6,8)
                + ' ' + pedido.getFecha().toString().substring(8,10) + ':' + pedido.getFecha().toString().substring(10));


        buttonAtras = findViewById(R.id.buttonAtras);
        buttonEditar = findViewById(R.id.buttonEditarPedido);
        buttonEliminar = findViewById(R.id.buttonEliminarPedido);
        buttonAtras.setOnClickListener(view -> {
            Intent intent = new Intent(this, orders_page.class);
            intent.putExtra("operacion", "getAllPedidos"); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });
        buttonEditar.setOnClickListener(view -> {
            Intent intent = new Intent(this, edit_order.class);
            intent.putExtra("Pedido", pedido); // Puedes cambiar "getAllPlatos" según tus necesidades
            intent.putExtra("operacion", intentaux.getStringExtra("operacion")); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });
        buttonEliminar.setOnClickListener(view -> {
            mRacionViewModel.getAllRaciones(pedido.getId()).observe(this, raciones -> {
                // Update the cached copy of the notes in the adapter.
                for(Racion racion : raciones){
                    mRacionViewModel.delete(racion);
                }
            });
            mPedidoViewModel.delete(pedido);
            Intent intent = new Intent(this, orders_page.class);
            intent.putExtra("operacion", "getAllPedidos"); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });


    }

}