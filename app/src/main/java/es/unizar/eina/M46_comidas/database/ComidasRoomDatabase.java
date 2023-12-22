package es.unizar.eina.M46_comidas.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Plato.class, Pedido.class, Racion.class}, version = 1, exportSchema = false)
public abstract class ComidasRoomDatabase extends RoomDatabase {

    public abstract PlatoDao platoDao();
    public abstract PedidoDao pedidoDao();
    public abstract RacionDao racionDao();


    private static volatile ComidasRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ComidasRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ComidasRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ComidasRoomDatabase.class, "comidas_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more notes, just add them.
                PlatoDao daoPlatos = INSTANCE.platoDao();
                PedidoDao daoPedidos = INSTANCE.pedidoDao();
                RacionDao daoRaciones = INSTANCE.racionDao();
                daoPlatos.deleteAll();
                daoPedidos.deleteAll();
                daoRaciones.deleteAll();


                Plato plato = new Plato("Arroz", "arroz", "SEGUNDO", 1.0);
                daoPlatos.insert(plato);
                plato = new Plato("Pollo", "pollo", "PRIMERO", 2.0);
                daoPlatos.insert(plato);
                plato = new Plato("Helado", "helado", "POSTRE", 3.0);
                daoPlatos.insert(plato);
                plato = new Plato("Hamburguesa", "hamburguesa", "SEGUNDO", 4.0);
                daoPlatos.insert(plato);

            });
        }
    };


}