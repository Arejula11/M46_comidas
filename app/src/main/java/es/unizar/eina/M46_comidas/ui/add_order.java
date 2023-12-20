package es.unizar.eina.M46_comidas.ui;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import es.unizar.eina.M46_comidas.R;
import es.unizar.eina.M46_comidas.database.Pedido;

import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import androidx.lifecycle.ViewModelProvider;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;


public class add_order extends AppCompatActivity implements View.OnClickListener{

    private PedidoViewModel mPedidoViewModel;
    Button buttonAtras;
    Button buttonAdd;
    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        Spinner spinner = findViewById(R.id.spinnerCategorias);

        String[] opciones = {"SOLICITADO", "PREPARADO", "RECOGIDO"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);


        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);
        Intent intentaux = getIntent();
        buttonAdd = findViewById(R.id.buttonAdd);
        // buttonAdd. setOnClickListener(view -> {
        //     EditText editTextNombre = findViewById(R.id.editTextNombreClienteAdd);
        //     EditText editTextPrecio = findViewById(R.id.editTextPrecioAdd);
        //     EditText textViewTelefono = findViewById(R.id.textViewTelefonoPedidoAdd);
        //     Spinner spinnerCategoria = findViewById(R.id.spinnerCategorias);

        //     String nombre = editTextNombre.getText().toString();
        //     String ingredientes = editTextIngrediente.getText().toString();
        //     int precio = Integer.parseInt(editTextPrecio.getText().toString());
        //     String categoriaSeleccionada = spinnerCategoria.getSelectedItem().toString();


        //     Pedido pedido = new Pedido(nombre,ingredientes,categoriaSeleccionada,precio);
        //     mPedidoViewModel.insert(plato);
        //     Intent intent = new Intent(this, orders_page_page.class);
        //     intent.putExtra("operacion", intentaux.getStringExtra("operacion")); // Puedes cambiar "getAllPlatos" según tus necesidades
        //     startActivity(intent);
        // });

        buttonAtras = findViewById(R.id.buttonAtras);
        buttonAtras.setOnClickListener(view -> {

            Intent intent = new Intent(this, orders_page.class);
            intent.putExtra("operacion", intentaux.getStringExtra("operacion")); // Puedes cambiar "getAllPlatos" según tus necesidades
            startActivity(intent);
        });
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
}