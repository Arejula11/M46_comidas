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
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.List;

import es.unizar.eina.M46_comidas.R;
import es.unizar.eina.M46_comidas.database.Pedido;
import es.unizar.eina.M46_comidas.database.Plato;
import es.unizar.eina.M46_comidas.database.Racion;

public class edit_order extends AppCompatActivity implements View.OnClickListener{

    RacionesAddPedido racionesSingleton;
    Pedido pedido;
    List<Racion> raciones;
    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    PedidoViewModel mPedidoViewModel;
    RacionViewModel mRacionViewModel;
    RacionListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);

        Spinner spinner = findViewById(R.id.spinnerCategorias);

        String[] opciones = {"Solicitado", "Preparado", "Recogido"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        Intent intentaux = getIntent();

        racionesSingleton = RacionesAddPedido.getInstance(pedido);
        raciones = racionesSingleton.getRaciones();

        pedido = (Pedido) intentaux.getSerializableExtra("Objeto");

        EditText editTextNombreCliente = findViewById(R.id.editTextNombreClienteEdit);
        EditText editTextTelefono = findViewById(R.id.editTextTelefonoEdit);
        EditText editTextDate = findViewById(R.id.in_date);
        EditText editTextTime = findViewById(R.id.in_time);
        EditText editTextPrecio = findViewById(R.id.editTextPrecioEdit);
        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);


        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);
        mRacionViewModel = new ViewModelProvider(this).get(RacionViewModel.class);

        RecyclerView mRecyclerView;
        mRecyclerView = findViewById(R.id.recyclerViewPlates);
        mAdapter = new RacionListAdapter(new RacionListAdapter.RacionDiff(), getIntent());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRacionViewModel.getAllRaciones(pedido.getId()).observe(this, raciones -> {
            // Update the cached copy of the notes in the adapter.
            mAdapter.submitList(raciones);
        });


        editTextNombreCliente.setText(pedido.getNombrecliente().toString());
        editTextPrecio.setText(String.valueOf(pedido.getPrecio()));
        editTextTelefono.setText(pedido.getTel().toString());
        editTextDate.setText(pedido.getFecha().toString().substring(0,4)+'-'+
                pedido.getFecha().toString().substring(4,6)+'-'+pedido.getFecha().toString().substring(6,8));
        editTextTime.setText(pedido.getFecha().toString().substring(8,10)+':'+
                pedido.getFecha().toString().substring(10,12));

        mRecyclerView = findViewById(R.id.recyclerViewPlates);
        mAdapter = new RacionListAdapter(new RacionListAdapter.RacionDiff(), getIntent());
        mAdapter.setOnItemClickListener(position -> this.onItemClick(position));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        Button buttonAtras = findViewById(R.id.buttonAtras);
        buttonAtras.setOnClickListener(view -> {
            racionesSingleton.reset();
            Intent intent = new Intent(this, orders_description.class);
            intent.putExtra("Objeto", pedido); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });
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
        mAdapter.notifyItemRemoved(position);
    }
}