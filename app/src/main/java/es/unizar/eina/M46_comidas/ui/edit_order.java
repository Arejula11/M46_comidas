package es.unizar.eina.M46_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import es.unizar.eina.M46_comidas.R;
import es.unizar.eina.M46_comidas.database.Pedido;
import es.unizar.eina.M46_comidas.database.Plato;
import es.unizar.eina.M46_comidas.database.Racion;

public class edit_order extends AppCompatActivity implements View.OnClickListener{

    RacionesAddPedido racionesSingleton;
    Pedido pedido;
    List<Racion> raciones;
    List<RacionVisual> racionesVis;

    Button btnDatePicker, btnTimePicker, buttonAddRacion, buttonAddPedido;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    PedidoViewModel mPedidoViewModel;
    PlatoViewModel mPlatoViewModel;
    RacionViewModel mRacionViewModel;
    RacionListAdapter mAdapter;
    Double precioTotal;
    EditText editTextNombreCliente, editTextTelefono, editTextDate, editTextTime;
    TextView editTextPrecio;
    RecyclerView mRecyclerView;

    String[] opciones = {"Solicitado", "Preparado", "Recogido"};
    Spinner spinner;
    Intent intentaux;

    int id;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);

        spinner = findViewById(R.id.spinnerCategorias);

        

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        intentaux = getIntent();

        racionesSingleton = RacionesAddPedido.getInstance(pedido);
        racionesVis = racionesSingleton.getRaciones();

        pedido = (Pedido) intentaux.getSerializableExtra("Pedido");
        precioTotal = 0.0;
        id = pedido.getId();
        editTextNombreCliente = findViewById(R.id.editTextNombreClienteEdit);
        editTextTelefono = findViewById(R.id.editTextTelefonoEdit);
        editTextDate = findViewById(R.id.in_date);
        editTextTime = findViewById(R.id.in_time);
        editTextPrecio = findViewById(R.id.editTextPrecioEdit);
        buttonAddRacion = findViewById(R.id.buttonAddRacion);
        buttonAddPedido = findViewById(R.id.buttonGuardarPedido);
        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        int categoriaIndex = Arrays.asList(opciones).indexOf(pedido.getEstado());
        spinner.setSelection(categoriaIndex);

        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);
        mRacionViewModel = new ViewModelProvider(this).get(RacionViewModel.class);
        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);

        mRecyclerView = findViewById(R.id.recyclerViewPlates);
        mAdapter = new RacionListAdapter(new RacionListAdapter.RacionDiff(), getIntent());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mRecyclerView = findViewById(R.id.recyclerViewPlates);
        mAdapter = new RacionListAdapter(new RacionListAdapter.RacionDiff(), getIntent());
        mAdapter.setOnItemClickListener(position -> this.onItemClick(position));
        mAdapter.setTextChangedListener((position, text) -> this.onTextChanged(position, text));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mostrarInformacion();
        buttonAddRacion = findViewById(R.id.buttonAddRacion);
        buttonAddRacion.setOnClickListener(view -> {
            int telefono = 0;
            String tel = editTextTelefono.getText().toString();
            if(!tel.isEmpty()){
                telefono = Integer.parseInt(tel);
            }
            racionesSingleton.setNombre(editTextNombreCliente.getText().toString());
            racionesSingleton.setTelefono(telefono);
            racionesSingleton.setDate(editTextDate.getText().toString());
            racionesSingleton.setTime(editTextTime.getText().toString());

            Intent intent = new Intent(this, plates_for_order.class);
            intent.putExtra("origen", "plates_for_orderEdit");
            intent.putExtra("Pedido", pedido);
            intent.putExtra("operacion", "getAllPlatos"); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });

        buttonAddPedido = findViewById(R.id.buttonGuardarPedido);
        buttonAddPedido.setOnClickListener(view -> {

            String nombreCliente2 = editTextNombreCliente.getText().toString();
            int telefono = 0;
            String tel2 = editTextTelefono.getText().toString();
            if(!tel2.isEmpty()){
                telefono = Integer.parseInt(tel2);
            }
            long fechaYhora = 0;
            String date = editTextDate.getText().toString();
            SimpleDateFormat input = new SimpleDateFormat("dd-MM-yyyy");
            int dia = 2;
            try {
                Date aux = input.parse(date);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(aux);
                dia = calendar.get(Calendar.DAY_OF_WEEK);

                SimpleDateFormat output = new SimpleDateFormat("yyyyMMdd");
                date = output.format(aux);
            } catch (ParseException e) {
                date ="";
            }
            String time = editTextTime.getText().toString();
            SimpleDateFormat input2 = new SimpleDateFormat("HH:mm");
            int hourOfDay = 0;
            int minute = 0;
            try {
                Date aux = input2.parse(time);
                SimpleDateFormat output = new SimpleDateFormat("HHmm");
                time = output.format(aux);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(aux);

                hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);

            } catch (ParseException e) {
                time = "";
            }


            List<RacionVisual> raciones = mAdapter.getCurrentList();
            int i=0;
            precioTotal = 0.0;
            for(RacionVisual aux : raciones){

                precioTotal += aux.getPrecioVisual() * aux.racion.getCantidad();
                i++;

            }
            boolean pasada = false;
            SimpleDateFormat input3 = new SimpleDateFormat("yyyyMMddHHmm");
            Date fechafinal = null;
            try{
                fechafinal = input3.parse(date+time);
                Calendar calendario = Calendar.getInstance();
                calendario.setTime(fechafinal);
                Calendar actual = Calendar.getInstance();
                if(!actual.before(calendario)){
                    pasada = true;
                }
            }catch (ParseException e){
                pasada = true;
            }

            String categoriaSeleccionada = spinner.getSelectedItem().toString();
            //comprobar que no esten vacios
            if (nombreCliente2.isEmpty() || tel2.isEmpty() || precioTotal.equals(0.0) || time.isEmpty()||date.isEmpty()){
                Toast.makeText(getApplicationContext(), "Error: campos sin rellenar", Toast.LENGTH_LONG).show();

            }else if(pasada || dia == 2 || hourOfDay < 19 || (hourOfDay == 19 && minute < 30) || hourOfDay > 23 || (hourOfDay == 23 && minute > 0)){
                Toast.makeText(getApplicationContext(), "Error: fecha de recogida inválida", Toast.LENGTH_LONG).show();
            }else {
                racionesSingleton.reset();
                mRacionViewModel.deleteAll(id);
                Intent intent = new Intent(this, orders_page.class);
                pedido = new Pedido(nombreCliente2, telefono, Long.valueOf(date+time), categoriaSeleccionada, precioTotal);
                pedido.setId(id);
                long insertedId = mPedidoViewModel.update(pedido);

                if(insertedId != -1){
                    for (RacionVisual racion : racionesSingleton.getRaciones()) {
                        mRacionViewModel.insert(racion.racion);
                    }
                    intent.putExtra("operacion", "getAllPedidos"); // Puedes cambiar "getAllPlatos" según tus necesidades
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Error: compruebe los campos", Toast.LENGTH_LONG).show();

                }

            }

        });
        Button buttonAtras = findViewById(R.id.buttonAtras);
        buttonAtras.setOnClickListener(view -> {
            racionesSingleton.reset();
            Intent intent = new Intent(this, orders_description.class);
            intent.putExtra("Pedido", pedido); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });
        añadirRacionALista();

    }
    private void añadirRacionALista(){
        if(intentaux.hasExtra("Objeto")){
            Plato plato = (Plato) intentaux.getSerializableExtra("Objeto");
            boolean repetido = false;
            for(RacionVisual aux : racionesSingleton.getRaciones()){
                if(plato.getId() == aux.racion.getPlatoId()){
                    repetido = true;
                }
            }
            if(!repetido){
                Racion racion = new Racion(plato.getId(), id, 1);
                RacionVisual racionv = new RacionVisual(plato.getNombre(), racion, plato.getPrecio());
                racionesSingleton.agregarRacion(racionv);
                racionesVis = racionesSingleton.getRaciones();
                mAdapter.submitList(racionesVis);
            }


        }
    }
    private void mostrarInformacion(){
        mRacionViewModel.getAllRaciones(pedido.getId()).observe(this, raciones -> {
            // Update the cached copy of the notes in the adapter.
            if(racionesSingleton.getPrimeraVez()) {
                for (Racion racion : raciones) {
                        mPlatoViewModel.getPlatoId(racion.getPlatoId()).observe(this, plato->{
                            //precioTotal += precio;
                            RacionVisual aux = new RacionVisual(plato.getNombre(), racion, plato.getPrecio());
                            racionesSingleton.agregarRacion(aux);
                            precioTotal += aux.getPrecioVisual() * aux.racion.getCantidad();
                            editTextPrecio.setText(String.valueOf(precioTotal));
                            mAdapter.submitList(racionesSingleton.getRaciones());

                        });

                }
                racionesSingleton.setPrimeraVez(false);
            }else{
                for(RacionVisual aux : racionesSingleton.getRaciones()){
                    precioTotal += aux.getPrecioVisual() * aux.racion.getCantidad();
                }
                editTextPrecio.setText(String.valueOf(precioTotal));
                mAdapter.submitList(racionesSingleton.getRaciones());
            }


        });
        racionesVis = racionesSingleton.getRaciones();



        editTextNombreCliente.setText(pedido.getNombrecliente().toString());
        //editTextPrecio.setText(String.valueOf(pedido.getPrecio()));
        editTextTelefono.setText(pedido.getTel().toString());
        editTextDate.setText(pedido.getFecha().toString().substring(6,8)+'-'+
                pedido.getFecha().toString().substring(4,6)+'-'+pedido.getFecha().toString().substring(0,4));
        editTextTime.setText(pedido.getFecha().toString().substring(8,10)+':'+
                pedido.getFecha().toString().substring(10,12));
    }


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
        precioTotal = 0.0;
        for(RacionVisual aux : racionesSingleton.getRaciones()){
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