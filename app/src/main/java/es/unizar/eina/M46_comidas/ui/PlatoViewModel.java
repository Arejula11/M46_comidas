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
    private final LiveData<List<Plato>> mAllPlatosNombre;
    private final LiveData<List<Plato>> mAllPlatosCategoria;
    private final LiveData<List<Plato>> mAllPlatosNombreCategoria;



    public PlatoViewModel(Application application) {
        super(application);
        mRepository = new ComidasRepository(application);
        mAllPlatos = mRepository.getAllPlatos();
        mAllPlatosNombre = mRepository.getAllPlatosNombre();
        mAllPlatosCategoria  = mRepository.getAllPlatosCategoria();
        mAllPlatosNombreCategoria = mRepository.getAllPlatosNombreCategoria();

    }

    LiveData<List<Plato>> getAllPlatos() { return mAllPlatos; }
    LiveData<List<Plato>> getAllPlatosNombre() { return mAllPlatosNombre; }
    LiveData<List<Plato>> getAllPlatosCategoria() { return mAllPlatosCategoria; }
    LiveData<List<Plato>> getAllPlatosNombreCategoria() { return mAllPlatosNombreCategoria; }

    public void insert(Plato plato) { mRepository.insert(plato); }

    public void update(Plato plato) { mRepository.update(plato); }
    public void delete(Plato plato) { mRepository.delete(plato); }
}

