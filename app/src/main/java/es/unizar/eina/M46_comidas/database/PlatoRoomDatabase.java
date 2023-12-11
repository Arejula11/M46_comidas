package es.unizar.eina.M46_comidas.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Plato.class}, version = 1, exportSchema = false)
public abstract class PlatoRoomDatabase extends RoomDatabase {

    public abstract PlatoDao platoDao();

    private static volatile PlatoRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PlatoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PlatoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PlatoRoomDatabase.class, "plato_database")
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
                PlatoDao dao = INSTANCE.platoDao();
                dao.deleteAll();

                Plato plato = new Plato("Plato 1's nombre", "Plato 1's ingredientes", "Plato 1's categoria", 1);
                dao.insert(plato);
                plato = new Plato("Plato 2's nombre", "Plato 2's ingredientes", "Plato 2's categoria", 2);
                dao.insert(plato);
            });
        }
    };

}
