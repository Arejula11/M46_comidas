package es.unizar.eina.M46_comidas.database;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "racion", primaryKeys = {"PedidoId", "PlatoId"})
public class Racion implements Serializable {

      @NonNull
      @ColumnInfo(name = "PedidoId")
      private int pedidoId ;

      @NonNull
      @ColumnInfo(name = "PlatoId")
      private int platoId;

      @NonNull
      @ColumnInfo(name = "Cantidad")
      private int cantidad;





        public Racion(@NonNull int platoId, @NonNull int pedidoId, @NonNull int cantidad) {
            this.platoId = platoId;
            this.pedidoId = pedidoId;
            this.cantidad = cantidad;

        }

        /** Devuelve el identificador del pedido */
        public int getPedidoId(){
            return this.pedidoId;
        }

        /** Permite actualizar el identificador del pedido */
        public int getPlatoId() {
            return this.platoId;
        }

        /** Devuelve el identificador del pedido */
        public void setPedidoId(int id){
            this.pedidoId = id;
        }


        /** Devuelve los ingredientes del pedido */
        public void setPlatoId(int id){
            this.platoId = id;
        }

        /** Devuelve la categoria del pedido */
        public int getCantidad(){
            return this.cantidad;
        }


}
