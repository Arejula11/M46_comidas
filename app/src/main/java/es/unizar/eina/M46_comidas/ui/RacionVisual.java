package es.unizar.eina.M46_comidas.ui;




import es.unizar.eina.M46_comidas.database.Racion;

public class RacionVisual {
    Racion racion;
    String nombre;
    double precio;


    public RacionVisual(String nombreParam,Racion racionParam, double precioParam) {
        this.nombre = nombreParam;   
        this.racion = racionParam;
        this.precio = precioParam;
    }


    /**
     * Devuelve el identificador del pedido
     */
    public Racion getRacionVisual() {
        return this.racion;
    }


    /**
     * Devuelve los ingredientes del pedido
     */
    public String getNombreVisual() {
        return this.nombre;
    }
    public double getPrecioVisual() {
        return this.precio;
    }


    /**
     * Devuelve la categoria del pedido
     */
    public void setRacionVisual(Racion racionParam) {
        this.racion = racionParam;
    }

    /**
     * Devuelve el precio del pedido
     */
    public void setNombreVisual(String nombreParam) {
        this.nombre = nombreParam;
    }
    public void setPrecioVisual(double precioParam) {
        this.precio = precioParam;
    }


}