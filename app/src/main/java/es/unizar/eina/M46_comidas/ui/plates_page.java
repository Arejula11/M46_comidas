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




public class plates_page extends AppCompatActivity {

    private PlatoViewModel mPlatoViewModel;
    RecyclerView mRecyclerView;

    PlatoListAdapter mAdapter;



    Button buttonHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plates_page);
        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new PlatoListAdapter(new PlatoListAdapter.PlatoDiff());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);

        mPlatoViewModel.getAllPlatos().observe(this, platos -> {
            // Update the cached copy of the notes in the adapter.
            mAdapter.submitList(platos);
        });
        buttonHome = findViewById(R.id.buttonHome);

        buttonHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        });
    }
}