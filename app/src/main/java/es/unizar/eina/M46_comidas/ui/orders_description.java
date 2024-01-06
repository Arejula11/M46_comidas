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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.unizar.eina.M46_comidas.R;
import es.unizar.eina.M46_comidas.database.Pedido;
import es.unizar.eina.M46_comidas.database.Racion;
import es.unizar.eina.send.SendAbstraction;
import es.unizar.eina.send.SendAbstractionImpl;

public class orders_description extends AppCompatActivity {
    Button buttonAtras;
    Button buttonEditar;
    Button buttonEliminar;
    Button buttonEnviarSMS;
    Button buttonEnviarWhatsApp;
    RacionListAdapter mAdapter;

    private PedidoViewModel mPedidoViewModel;
    private RacionViewModel mRacionViewModel;
    private PlatoViewModel mPlatoViewModel;

    TextView textViewNombreCliente;
    TextView textViewPrecio;
    TextView textViewTelefono;
    TextView textViewFecha;
    TextView textViewEstado;
    Pedido pedido;
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_description);

        textViewNombreCliente = findViewById(R.id.textViewNombreClienteFill);
        textViewPrecio = findViewById(R.id.textViewPrecioFill);
        textViewTelefono = findViewById(R.id.textViewTelefonoFill);
        textViewFecha = findViewById(R.id.textViewFechaFill);
        textViewEstado = findViewById(R.id.textViewEstadoFill);

        Intent intentaux = getIntent();
        pedido = (Pedido) intentaux.getSerializableExtra("Pedido");
        intentaux.putExtra("invisible", true);

        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);
        mRacionViewModel = new ViewModelProvider(this).get(RacionViewModel.class);
        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);

        mRecyclerView = findViewById(R.id.recyclerViewPlates);
        mAdapter = new RacionListAdapter(new RacionListAdapter.RacionDiff(), getIntent());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        
        mostrarInformacion();

        buttonAtras = findViewById(R.id.buttonAtras);
        buttonEditar = findViewById(R.id.buttonEditarPedido);
        buttonEliminar = findViewById(R.id.buttonEliminarPedido);
        buttonEnviarSMS = findViewById(R.id.buttonEnviarPedidoSMS);
        buttonEnviarWhatsApp = findViewById(R.id.buttonEnviarPedidoWhatsapp);
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
            Toast.makeText(
                    getApplicationContext(),
                    "Eliminando el pedido  de " + pedido.getNombrecliente(),
                    Toast.LENGTH_LONG).show();
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

        buttonEnviarSMS.setOnClickListener(view ->{
            // Obtener el pedido seleccionado (aquí suponemos el primero de la lista)
            //Pedido selectedPedido = pedido; // Cambia esto para obtener el pedido adecuado

            // Crear una instancia de SendAbstractionImpl en onCreate o donde sea apropiado
            SendAbstraction sendAbstraction = new SendAbstractionImpl(this, "SMS");

            // Enviar el pedido utilizando la instancia de SendAbstractionImpl

            sendAbstraction.send(pedido.getTel().toString(),"Cliente: "+pedido.getNombrecliente().toString()+"\nPrecio: "+   String.valueOf(pedido.getPrecio())+"\nFecha de recogida:"+ pedido.getFecha().toString().substring(0,4) + '-'
                    + pedido.getFecha().toString().substring(4,6) + '-' + pedido.getFecha().toString().substring(6,8)
                    + ' ' + pedido.getFecha().toString().substring(8,10) + ':' + pedido.getFecha().toString().substring(10));

        });
        buttonEnviarWhatsApp.setOnClickListener(view ->{
            // Obtener el pedido seleccionado (aquí suponemos el primero de la lista)
            //Pedido selectedPedido = pedido; // Cambia esto para obtener el pedido adecuado

            // Crear una instancia de SendAbstractionImpl en onCreate o donde sea apropiado
            SendAbstraction sendAbstraction = new SendAbstractionImpl(this, "WhatsApp");

            // Enviar el pedido utilizando la instancia de SendAbstractionImpl

            sendAbstraction.send(pedido.getTel().toString(),"Cliente: "+pedido.getNombrecliente().toString()+"\nPrecio: "+   String.valueOf(pedido.getPrecio())+"\nFecha de recogida:"+ pedido.getFecha().toString().substring(0,4) + '-'
                    + pedido.getFecha().toString().substring(4,6) + '-' + pedido.getFecha().toString().substring(6,8)
                    + ' ' + pedido.getFecha().toString().substring(8,10) + ':' + pedido.getFecha().toString().substring(10));

        });


    }
    private void mostrarInformacion(){
        mRacionViewModel.getAllRaciones(pedido.getId()).observe(this, raciones -> {

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
            
        });

        textViewNombreCliente.setText(pedido.getNombrecliente().toString());
        textViewPrecio.setText(String.valueOf(pedido.getPrecio()));
        textViewTelefono.setText(pedido.getTel().toString());
        textViewEstado.setText(pedido.getEstado().toString());
        textViewFecha.setText("Fecha: " + pedido.getFecha().toString().substring(0,4) + '-'
                + pedido.getFecha().toString().substring(4,6) + '-' + pedido.getFecha().toString().substring(6,8)
                + ' ' + pedido.getFecha().toString().substring(8,10) + ':' + pedido.getFecha().toString().substring(10));
    }

}
