package es.unizar.eina.M46_comidas.database;


import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PlatoRepository {

    private PlatoDao mPlatoDao;
    private LiveData<List<Plato>> mAllPlatos;
    private LiveData<List<Plato>> mAllPlatosNombre;
    private LiveData<List<Plato>> mAllPlatosCategoria;
    private LiveData<List<Plato>> mAllPlatosNombreCategoria;



    // Note that in order to unit test the NoteRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public PlatoRepository(Application application) {
        PlatoRoomDatabase db = PlatoRoomDatabase.getDatabase(application);
        mPlatoDao = db.platoDao();
        mAllPlatosNombre = mPlatoDao.getOrderedPlatosNombre();
        mAllPlatosCategoria = mPlatoDao.getOrderedPlatosCategoria();
        mAllPlatosNombreCategoria = mPlatoDao.getOrderedPlatosNombreCategoria();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Plato>> getAllPlatosNombre() {
        return mAllPlatosNombre;
    }
    public LiveData<List<Plato>> getAllPlatosCategoria() {
        return mAllPlatosCategoria;
    }
    public LiveData<List<Plato>> getAllPlatosNombreCategoria() {
        return mAllPlatosNombreCategoria;
    }



    /** Inserta una nota
     * @param plato
     * @return un valor entero largo con el identificador de la nota que se ha creado.
     */
    public long insert(Plato plato) {
        final long[] result = {0};
        // You must call this on a non-UI thread or your app will throw an exception. Room ensures
        // that you're not doing any long running operations on the main thread, blocking the UI.
        PlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPlatoDao.insert(plato);
        });
        return result[0];
    }

    /** Modifica una nota
     * @param plato
     * @return un valor entero con el número de filas modificadas.
     */
    public int update(Plato plato) {
        final int[] result = {0};
        PlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPlatoDao.update(plato);
        });
        return result[0];
    }

    /** Elimina una nota
     * @param plato
     * @return un valor entero con el número de filas eliminadas.
     */
    public int delete(Plato plato) {
        final int[] result = {0};
        PlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPlatoDao.delete(plato);
        });
        return result[0];
    }
}
