package es.unizar.eina.M46_comidas.ui;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import es.unizar.eina.M46_comidas.R;
import es.unizar.eina.M46_comidas.database.Pedido;
import es.unizar.eina.M46_comidas.database.Plato;
import es.unizar.eina.M46_comidas.database.Racion;

import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class add_order extends AppCompatActivity implements View.OnClickListener{

    private PedidoViewModel mPedidoViewModel;
    private RacionViewModel mRacionViewModel;

    private PlatoViewModel mPlatoViewModel;
    Button buttonAtras;
    Button buttonAddPedido;
    Button buttonAddRacion;


    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Pedido pedido;

    RacionesAddPedido racionesSingleton;

    List<Racion> raciones;
    List<RacionVisual> racionesVis;

    RacionListAdapter mAdapter;

    Double precioTotal;
    int telefono;
    int id;

    TextView editTextPrecio;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);



        Intent intentaux = getIntent();

        racionesSingleton = RacionesAddPedido.getInstance(pedido);
        racionesVis = racionesSingleton.getRaciones();

        precioTotal = 0.0;
        EditText editTextNombreCliente = findViewById(R.id.editTextNombreClienteAdd);
        EditText editTextTelefono = findViewById(R.id.editTextTelefonoAdd);
        EditText editTextDate = findViewById(R.id.in_date);
        EditText editTextTime = findViewById(R.id.in_time);
        editTextPrecio = findViewById(R.id.editTextPrecioAdd);
        buttonAddRacion = findViewById(R.id.buttonAddRacion);
        buttonAddPedido = findViewById(R.id.buttonGuardarPedido);
        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);


        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);
        mRacionViewModel = new ViewModelProvider(this).get(RacionViewModel.class);
        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);


        RecyclerView mRecyclerView;
        mRecyclerView = findViewById(R.id.recyclerViewPlates);
        mAdapter = new RacionListAdapter(new RacionListAdapter.RacionDiff(), getIntent());
        mAdapter.setOnItemClickListener(position -> this.onItemClick(position));
        mAdapter.setTextChangedListener((position, text) -> this.onTextChanged(position, text));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        racionesVis = racionesSingleton.getRaciones();
        if(intentaux.hasExtra("Objeto") || intentaux.hasExtra("Pedido")){
            editTextNombreCliente.setText(racionesSingleton.getNombre().toString());
            editTextTelefono.setText(String.valueOf(racionesSingleton.getTelefono()));
            editTextDate.setText(racionesSingleton.getDate());
            editTextTime.setText(racionesSingleton.getTime());
            for(RacionVisual aux : racionesSingleton.getRaciones()){
                precioTotal += aux.getPrecioVisual() * aux.racion.getCantidad();
            }
            editTextPrecio.setText(String.valueOf(precioTotal));
        }
        mAdapter.submitList(racionesSingleton.getRaciones());
        precioTotal =0.0;
        for(RacionVisual aux : mAdapter.getCurrentList()){
            precioTotal += aux.getPrecioVisual() * aux.racion.getCantidad();

        }
        editTextPrecio.setText(String.valueOf(precioTotal));




        buttonAddRacion = findViewById(R.id.buttonAddRacion);
        buttonAddRacion.setOnClickListener(view -> {
            String nombreCliente = editTextNombreCliente.getText().toString();
            int telefono = 0;
            Double precioTotal = 0.0;
            String tel = editTextTelefono.getText().toString();
            long fechaYhora = 0;
            if(!tel.isEmpty()){
                telefono = Integer.parseInt(tel);
            }
            String date = editTextDate.getText().toString();


            String time = editTextTime.getText().toString();




            racionesSingleton.setNombre(nombreCliente);
            racionesSingleton.setTelefono(telefono);
            racionesSingleton.setDate(date);
            racionesSingleton.setTime(time);

            Intent intent = new Intent(this, plates_for_order.class);
            intent.putExtra("origen", "plates_for_orderAdd");
            intent.putExtra("Pedido", pedido);
            intent.putExtra("operacion", "getAllPlatos"); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });

        buttonAddPedido = findViewById(R.id.buttonGuardarPedido);
        buttonAddPedido.setOnClickListener(view -> {
            racionesSingleton.reset();


            String nombreCliente2 = editTextNombreCliente.getText().toString();
            int telefono = 0;
            String tel2 = editTextTelefono.getText().toString();
            if(!tel2.isEmpty()){
                telefono = Integer.parseInt(tel2);
            }
            long fechaYhora = 0;
            String date = editTextDate.getText().toString();
            SimpleDateFormat input = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date aux = input.parse(date);
                SimpleDateFormat output = new SimpleDateFormat("yyyyMMdd");
                date = output.format(aux);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            String time = editTextTime.getText().toString();
            SimpleDateFormat input2 = new SimpleDateFormat("HH:mm");
            try {
                Date aux = input2.parse(time);
                SimpleDateFormat output = new SimpleDateFormat("HHmm");
                time = output.format(aux);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            //i++;

            //}

            List<RacionVisual> raciones = mAdapter.getCurrentList();
            int i=0;
            for(RacionVisual aux : raciones){

                precioTotal += aux.getPrecioVisual() * aux.racion.getCantidad();
                i++;

            }

            Intent intent = new Intent(this, orders_page.class);
            pedido = new Pedido(nombreCliente2, telefono, Long.valueOf(date+time), "Solicitado", precioTotal);
            mPedidoViewModel.insert(pedido).observe(this, insertedId -> {
                for(RacionVisual aux : racionesSingleton.getRaciones()){
                    aux.racion.setPedidoId(insertedId.intValue());
                    mRacionViewModel.insert(aux.racion);
                }
            });

            intent.putExtra("operacion", "getAllPedidos"); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });
        Button buttonAtras = findViewById(R.id.buttonAtras);
        buttonAtras.setOnClickListener(view -> {
            racionesSingleton.reset();
            Intent intent = new Intent(this, orders_page.class);
            intent.putExtra("operacion", "getAllPedidos"); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });
        if(intentaux.hasExtra("Objeto")){
            Plato plato = (Plato) intentaux.getSerializableExtra("Objeto");
            boolean repetido = false;
            for(RacionVisual aux : racionesSingleton.getRaciones()){
                if(plato.getId() == aux.racion.getPlatoId()){
                    repetido = true;
                }
            }
            if(!repetido) {
                Racion racion = new Racion(plato.getId(), id, 1);
                RacionVisual racionv = new RacionVisual(plato.getNombre(), racion, plato.getPrecio());
                racionesSingleton.agregarRacion(racionv);
                racionesVis = racionesSingleton.getRaciones();
                mAdapter.submitList(racionesVis);
                precioTotal =0.0;
                for(RacionVisual aux : mAdapter.getCurrentList()){
                    precioTotal += aux.getPrecioVisual() * aux.racion.getCantidad();

                }
                editTextPrecio.setText(String.valueOf(precioTotal));
            }


        }



    }

     @Override
     public void onClick(View v) {

         if (v == btnDatePicker) {

             // Get Current Date
             final Calendar c = Calendar.getInstance();
             mYear = c.get(Calendar.YEAR);
             mMonth = c.get(Calendar.MONTH);
             mDay = c.get(Calendar.DAY_OF_MONTH);


             DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                     new DatePickerDialog.OnDateSetListener() {

                         @Override
                        public void onDateSet(DatePicker view, int year,
                                               int monthOfYear, int dayOfMonth) {

                             txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                         }
                     }, mYear, mMonth, mDay);
             datePickerDialog.show();
         }
         if (v == btnTimePicker) {

             // Get Current Time
             final Calendar c = Calendar.getInstance();
             mHour = c.get(Calendar.HOUR_OF_DAY);
             mMinute = c.get(Calendar.MINUTE);

             // Launch Time Picker Dialog
             TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                     new TimePickerDialog.OnTimeSetListener() {

                         @Override
                         public void onTimeSet(TimePicker view, int hourOfDay,
                                               int minute) {

                             txtTime.setText(hourOfDay + ":" + minute);
                         }
                     }, mHour, mMinute, false);
             timePickerDialog.show();
         }
     }


     public void onItemClick(int position) {
         racionesSingleton.eliminarRacion(position);
         precioTotal =0.0;
         for(RacionVisual aux : mAdapter.getCurrentList()){
             precioTotal += aux.getPrecioVisual() * aux.racion.getCantidad();

         }
         editTextPrecio.setText(String.valueOf(precioTotal));
         mAdapter.notifyItemRemoved(position);
         mAdapter.submitList(racionesSingleton.getRaciones());

     }

    public void onTextChanged(int position, String text){
        precioTotal =0.0;
        for(RacionVisual aux : mAdapter.getCurrentList()){
            precioTotal += aux.getPrecioVisual() * aux.racion.getCantidad();

        }
        editTextPrecio.setText(String.valueOf(precioTotal));

    }

}