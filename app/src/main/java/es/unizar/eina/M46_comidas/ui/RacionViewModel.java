package es.unizar.eina.M46_comidas.ui;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.M46_comidas.R;
import es.unizar.eina.M46_comidas.database.ComidasRepository;
import es.unizar.eina.M46_comidas.database.Plato;
import es.unizar.eina.M46_comidas.database.Racion;


public class RacionViewModel extends AndroidViewModel {
    private ComidasRepository mRepository;





    public RacionViewModel(Application application) {
        super(application);
        mRepository = new ComidasRepository(application);

    }

    LiveData<List<Racion>> getAllRaciones(int id) { return mRepository.getAllRaciones(id); }

    public LiveData<String> getNombrePlatoId(int id){
        return mRepository.getNombrePlatoId(id);
    }


    public void insert(Racion racion) { mRepository.insert(racion); }

    public void update(Racion racion) { mRepository.update(racion); }
    public void delete(Racion racion) { mRepository.delete(racion); }

    public void deleteAll(int id) {        AsyncTask.execute(() -> mRepository.deleteAll(id));}
    public static class orders_description extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_orders_description);
        }
    }
}



