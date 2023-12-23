package es.unizar.eina.M46_comidas.ui;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.M46_comidas.R;
import es.unizar.eina.M46_comidas.database.Plato;
import es.unizar.eina.M46_comidas.database.ComidasRepository;

public class PlatoViewModel extends AndroidViewModel{

    private ComidasRepository mRepository;

    private final LiveData<List<Plato>> mAllPlatos;




    public PlatoViewModel(Application application) {
        super(application);
        mRepository = new ComidasRepository(application);
        mAllPlatos = mRepository.getAllPlatos();

    }

    LiveData<List<Plato>> getAllPlatos() { return mAllPlatos; }
    LiveData<List<Plato>> getAllPlatosNombre() { return mRepository.getAllPlatosNombre(); }
    LiveData<List<Plato>> getAllPlatosCategoria() { return mRepository.getAllPlatosCategoria(); }
    LiveData<List<Plato>> getAllPlatosNombreCategoria() { return mRepository.getAllPlatosNombreCategoria(); }

    LiveData<Double> getPrecioPlatoId(int id) { LiveData<Double> aux =  mRepository.getPrecioPlatoId(id);
        Log.d("Observation", String.valueOf(aux));
    return aux;}

    LiveData<Plato> getPlatoId(int id) { return mRepository.getPlatoId(id);}

    public void insert(Plato plato) { mRepository.insert(plato); }

    public void update(Plato plato) { mRepository.update(plato); }
    public void delete(Plato plato) { mRepository.delete(plato); }

    public static class orders_description extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_orders_description);
        }
    }
}

