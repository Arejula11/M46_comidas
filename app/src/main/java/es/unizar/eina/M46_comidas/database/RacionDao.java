package es.unizar.eina.M46_comidas.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RacionDao {
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Racion racion);

    @Update
    int update(Racion racion);

    @Delete
    int delete(Racion racion);

    @Query("DELETE FROM racion")
    void deleteAll();

    @Query("DELETE FROM racion WHERE racion.PedidoId == :id")
    void deleteAll(int id);
    
    @Query("SELECT * FROM racion WHERE PedidoId = :pedidoId")
    LiveData<List<Racion>> getRaciones(int pedidoId); //cambiar query
    
}
