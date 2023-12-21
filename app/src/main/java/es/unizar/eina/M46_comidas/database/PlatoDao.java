package es.unizar.eina.M46_comidas.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/** Definición de un Data Access Object para los platos */
@Dao
public interface PlatoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Plato plato);

    @Update
    int update(Plato plato);

    @Delete
    int delete(Plato plato);

    @Query("DELETE FROM plato")
    void deleteAll();
    @Query("SELECT * FROM plato")
    LiveData<List<Plato>> getPlatos(); //cambiar query

    @Query("SELECT * FROM plato ORDER BY Nombre ASC")
    LiveData<List<Plato>> getOrderedPlatosNombre(); //cambiar query
    @Query("SELECT * FROM plato ORDER BY\n " +
            "CASE\n" +
            "    WHEN categoria = 'PRIMERO' THEN 1\n" +
            "    WHEN categoria = 'SEGUNDO' THEN 2\n" +
            "    WHEN categoria = 'POSTRE' THEN 3\n" +
            "  END")
    LiveData<List<Plato>> getOrderedPlatosCategoria(); //cambiar query

    @Query("SELECT * FROM plato ORDER BY\n " +
            "CASE\n" +
            "    WHEN categoria = 'PRIMERO' THEN 1\n" +
            "    WHEN categoria = 'SEGUNDO' THEN 2\n" +
            "    WHEN categoria = 'POSTRE' THEN 3\n" +
            "  END, Nombre ASC")
    LiveData<List<Plato>> getOrderedPlatosNombreCategoria(); //cambiar query

    @Query("SELECT plato.Nombre FROM plato WHERE plato.ID == :id")
    String getNombrePlatoId(int id); //cambiar query
}
