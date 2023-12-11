package es.unizar.eina.M46_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import es.unizar.eina.M46_comidas.R;

public class home extends AppCompatActivity {
      Button buttonPlatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        buttonPlatos = findViewById(R.id.buttonPlatos);

        buttonPlatos.setOnClickListener(view -> {

        });


    }
}