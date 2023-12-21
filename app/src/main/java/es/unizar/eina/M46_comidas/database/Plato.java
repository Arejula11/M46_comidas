package es.unizar.eina.M46_comidas.database;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "plato")
public class Plato implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID")
    private int id;
    @NonNull
    @ColumnInfo(name = "Nombre")
    private String nombre ;


    @NonNull
    @ColumnInfo(name = "Ingredientes")
    private String ingredientes;
    @NonNull
    @ColumnInfo(name = "Categoria")
    private String categoria;

    @NonNull
    @ColumnInfo(name = "Precio")
    private Double precio;




    public Plato(@NonNull String nombre, @NonNull String ingredientes, @NonNull String categoria, @NonNull Double precio) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.categoria = categoria;
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

    /** Devuelve el identificador del pedido */
    public String getNombre(){
        return this.nombre;
    }


    /** Devuelve los ingredientes del pedido */
    public String getIngredientes(){
        return this.ingredientes;
    }

    /** Devuelve la categoria del pedido */
    public String getCategoria(){
        return this.categoria;
    }

    /** Devuelve el precio del pedido */
    public Double getPrecio(){
        return this.precio;
    }


}
