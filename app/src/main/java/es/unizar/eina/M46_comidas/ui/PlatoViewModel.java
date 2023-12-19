package es.unizar.eina.M46_comidas.ui;
import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

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

    public void insert(Plato plato) { mRepository.insert(plato); }

    public void update(Plato plato) { mRepository.update(plato); }
    public void delete(Plato plato) { mRepository.delete(plato); }
}

