package es.unizar.eina.M46_comidas.database;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plato")
public class Plato {
    @PrimaryKey(autoGenerate = false)
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
    private int precio;




    public Plato(@NonNull String nombre, @NonNull String ingredientes, @NonNull String categoria, @NonNull int precio) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.categoria = categoria;
        this.precio = precio;
    }

    /** Devuelve el identificador del pedido */
    public String getNombre(){
        return this.nombre;
    }

    /** Permite actualizar el identificador del pedido */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public int getPrecio(){
        return this.precio;
    }


}
