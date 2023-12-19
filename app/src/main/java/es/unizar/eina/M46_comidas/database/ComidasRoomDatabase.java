package es.unizar.eina.M46_comidas.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Plato.class, Pedido.class}, version = 1, exportSchema = false)
public abstract class ComidasRoomDatabase extends RoomDatabase {

    public abstract PlatoDao platoDao();
    public abstract PedidoDao pedidoDao();


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
                daoPlatos.deleteAll();

                Plato plato = new Plato("Arroz", "arroz", "SEGUNDO", 1);
                daoPlatos.insert(plato);
                plato = new Plato("Pollo", "pollo", "PRIMERO", 2);
                daoPlatos.insert(plato);
                plato = new Plato("Helado", "helado", "POSTRE", 3);
                daoPlatos.insert(plato);
                plato = new Plato("Hamburguesa", "hamburguesa", "SEGUNDO", 4);
                daoPlatos.insert(plato);

                Pedido pedido = new Pedido("Pedido 1's nombrecliente", 000000001, (long) 0, "Pedido 1's estado");
                daoPedidos.insert(pedido);
                pedido = new Pedido("Pedido 2's title", 000000002, (long) 0, "Pedido 2's estado");
                daoPedidos.insert(pedido);
            });
        }
    };


}