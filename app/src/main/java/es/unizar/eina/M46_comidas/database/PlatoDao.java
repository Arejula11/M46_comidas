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

    @Query("SELECT * FROM plato ORDER BY Nombre DESC")
    LiveData<List<Plato>> getOrderedPlatosNombre(); //cambiar query
    @Query("SELECT * FROM plato ORDER BY Categoria DESC")
    LiveData<List<Plato>> getOrderedPlatosCategoria(); //cambiar query

    @Query("SELECT * FROM plato ORDER BY Nombre DESC, Categoria DESC")
    LiveData<List<Plato>> getOrderedPlatosNombreCategoria(); //cambiar query
}
