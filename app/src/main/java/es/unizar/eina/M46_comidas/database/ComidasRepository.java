package es.unizar.eina.M46_comidas.database;


import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ComidasRepository {

    private PedidoDao mPedidoDao;
    private PlatoDao mPlatoDao;
    private RacionDao mRacionDao;


    private LiveData<List<Pedido>> mAllPedidos;


    private LiveData<List<Plato>> mAllPlatos;

    private final long TIMEOUT = 15000;




    // Note that in order to unit test the NoteRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public ComidasRepository(Application application) {
        ComidasRoomDatabase db = ComidasRoomDatabase.getDatabase(application);
        mPedidoDao = db.pedidoDao();
        mAllPedidos = mPedidoDao.getPedidos();
        mPlatoDao = db.platoDao();
        mAllPlatos = mPlatoDao.getPlatos();
        mRacionDao = db.racionDao();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Pedido>> getAllPedidos() {
        return mAllPedidos;
    }
    
    public LiveData<List<Pedido>> getAllPedidosNumTlfn() {
        return mPedidoDao.getOrderedPedidosNumTlfn();

    }

    public LiveData<List<Pedido>> getAllPedidosFecha() {
        return mPedidoDao.getOrderedPedidosFecha();
    }
    public LiveData<List<Pedido>> getAllPedidosNombreCliente() {
        return mPedidoDao.getOrderedPedidosNombreCliente();
    }
    public LiveData<List<Pedido>> getAllPedidosFiltrados(String estado) {
        return mPedidoDao.getPedidosBy(estado);
    }
    public LiveData<List<Pedido>> getAllPedidosNumTlfnAndFilter(String estado) {
        return mPedidoDao.getOrderedPedidosNumTlfnAndFilter(estado);
    }

    public LiveData<List<Pedido>> getAllPedidosFechaAndFilter(String estado) {
        return mPedidoDao.getOrderedPedidosFechaAndFilter(estado);
    }
    public LiveData<List<Pedido>> getAllPedidosNombreClienteAndFilter(String estado) {
        return mPedidoDao.getOrderedPedidosNombreClienteAndFilter(estado);
    }
    public LiveData<List<Pedido>> getAllPedidosFiltradosAndFilter(String estado) {
        return mPedidoDao.getPedidosBy(estado);
    }

    public LiveData<List<Plato>> getAllPlatos() {
        return mAllPlatos;
    }
    public LiveData<List<Plato>> getAllPlatosNombre() {
        return mPlatoDao.getOrderedPlatosNombre();
    }
    public LiveData<List<Plato>> getAllPlatosCategoria() {
        return mPlatoDao.getOrderedPlatosCategoria();
    }
    public LiveData<List<Plato>> getAllPlatosNombreCategoria() {
        return mPlatoDao.getOrderedPlatosNombreCategoria();
    }

    public LiveData<String> getNombrePlatoId(int id){
        return mPlatoDao.getNombrePlatoId(id);
    }

    public LiveData<List<Racion>> getAllRaciones(int id){
        return mRacionDao.getRaciones(id);
    }

    public void deleteAll(int id) {mRacionDao.deleteAll(id);    }

    public LiveData<Double> getPrecioPlatoId(int id) {return mPlatoDao.getPrecioPlatoId(id);}
    public LiveData<Plato> getPlatoId(int id) {return mPlatoDao.getPlatoId(id);}


    /** Inserta una nota
     * @param pedido
     * @return un valor entero largo con el identificador de la nota que se ha creado.
     */
    public LiveData<Long> insert(Pedido pedido) {
        final long[] result = {0};
        // You must call this on a non-UI thread or your app will throw an exception. Room ensures
        // that you're not doing any long running operations on the main thread, blocking the UI.
        MutableLiveData<Long> insertedId = new MutableLiveData<>();
        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPedidoDao.insert(pedido);
            insertedId.postValue(result[0]);

    });
        return insertedId;
    }

    /** Inserta una nota
     * @param plato
     * @return un valor entero largo con el identificador de la nota que se ha creado.
     */
    public long insert(Plato plato) {
        AtomicLong result = new AtomicLong();
        Semaphore resource = new Semaphore(0);
        // You must call this on a non-UI thread or your app will throw an exception. Room ensures
        // that you're not doing any long running operations on the main thread, blocking the UI.
        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            long value = mPlatoDao.insert(plato);
            result.set(value);
            resource.release();
        });
        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            Log.d("ComidaRepository", "Exception: " + e.getMessage());
        }
        return result.get();
    }
    public long insert(Racion racion) {
        final long[] result = {0};
        // You must call this on a non-UI thread or your app will throw an exception. Room ensures
        // that you're not doing any long running operations on the main thread, blocking the UI.
        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mRacionDao.insert(racion);
        });
        return result[0];
    }


    /** Modifica una nota
     * @param pedido
     * @return un valor entero con el número de filas modificadas.
     */
    public int update(Pedido pedido) {
        final int[] result = {0};
        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPedidoDao.update(pedido);
        });
        return result[0];
    }

    /** Modifica una nota
     * @param plato
     * @return un valor entero con el número de filas modificadas.
     */
    public int update(Plato plato) {
        final int[] result = {0};
        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPlatoDao.update(plato);
        });
        return result[0];
    }
    public int update(Racion racion) {
        final int[] result = {0};
        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mRacionDao.update(racion);
        });
        return result[0];
    }


    /** Elimina una nota
     * @param pedido
     * @return un valor entero con el número de filas eliminadas.
     */
    public int delete(Pedido pedido) {
        final int[] result = {0};
        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPedidoDao.delete(pedido);
        });
        return result[0];
    }

    /** Elimina una nota
     * @param plato
     * @return un valor entero con el número de filas eliminadas.
     */
    public int delete(Plato plato) {
        final int[] result = {0};
        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mPlatoDao.delete(plato);
        });
        return result[0];
    }
    public int delete(Racion racion) {
        final int[] result = {0};
        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            result[0] = mRacionDao.delete(racion);
        });
        return result[0];
    }
    public void deleteAllRaciones() {
        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            mRacionDao.deleteAll();
        });
    }
    public void deleteAllPedidos() {
        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPedidoDao.deleteAll();
        });
    }
    public void deleteAllPlatos() {
        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPlatoDao.deleteAll();
        });
    }

}
