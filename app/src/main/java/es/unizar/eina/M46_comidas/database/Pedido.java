package es.unizar.eina.M46_comidas.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pedido")
public class Pedido {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID")
    private int id;

    @NonNull
    @ColumnInfo(name = "NombreCliente")
    private String nombrecliente;

    @NonNull
    @ColumnInfo(name = "NumeroTelefono")
    private Integer tel;

    @NonNull
    @ColumnInfo(name = "Fecha")
    private Long fecha;

    @NonNull
    @ColumnInfo(name = "Estado")
    private String estado;

    @NonNull
    @ColumnInfo(name = "Precio")
    private double precio;


    public Pedido(@NonNull String nombrecliente, @NonNull Integer tel, @NonNull Long fecha, @NonNull String estado, @NonNull double precio) {
        this.nombrecliente = nombrecliente;
        this.tel = tel;
        this.fecha = fecha;
        this.estado = estado;
        this.precio = precio;
    }

    /** Devuelve el identificador del pedido */
    public int getId(){
        return this.id;
    }

    /** Permite actualizar el identificador del pedido */
    public void setId(int id) {
        this.id = id;
    }

    /** Devuelve el nombre del cliente del pedido */
    public String getNombrecliente(){
        return this.nombrecliente;
    }

    /** Devuelve el numero de telefono del pedido */
    public Integer getTel(){
        return this.tel;
    }

    /** Devuelve la fecha del pedido */
    public Long getFecha(){
        return this.fecha;
    }

    /** Devuelve el estado del pedido */
    public String getEstado(){
        return this.estado;
    }

    /** Devuelve el estado del pedido */
    public Double getPrecio(){
        return this.precio;
    }


}
