package es.unizar.eina.M46_comidas.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/** Definición de un Data Access Object para los pedidos */
@Dao
public interface PedidoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Pedido pedido);

    @Update
    int update(Pedido pedido);

    @Delete
    int delete(Pedido pedido);

    @Query("DELETE FROM pedido")
    void deleteAll();

    @Query("SELECT * FROM pedido ORDER BY NombreCliente DESC")
    LiveData<List<Pedido>> getOrderedPedidosNombreCliente();
    @Query("SELECT * FROM pedido ORDER BY NumeroTelefono DESC")
    LiveData<List<Pedido>> getOrderedPedidosNumTlfn();
    @Query("SELECT * FROM pedido ORDER BY Fecha ASC")
    LiveData<List<Pedido>> getOrderedPedidosFecha();

    @Query("SELECT * FROM pedido WHERE Estado = :estado")
    LiveData<List<Pedido>> getPedidosBy(String estado);


}

