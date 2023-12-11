package es.unizar.eina.M46_comidas.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.time.LocalDateTime;

@Database(entities = {Pedido.class}, version = 1, exportSchema = false)
public abstract class PedidoRoomDatabase extends RoomDatabase {

    public abstract PedidoDao pedidoDao();

    private static volatile PedidoRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PedidoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PedidoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PedidoRoomDatabase.class, "pedido_database")
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
                PedidoDao dao = INSTANCE.pedidoDao();
                dao.deleteAll();
                LocalDateTime fecha = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    fecha = LocalDateTime.now();
                }
                Pedido pedido = new Pedido("Pedido 1's nombrecliente", 000000001, (long) 0, "Pedido 1's estado");
                dao.insert(pedido);
                pedido = new Pedido("Pedido 2's title", 000000002, (long) 0, "Pedido 2's estado");
                dao.insert(pedido);
            });
        }
    };

}
