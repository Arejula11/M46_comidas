package es.unizar.eina.M46_comidas.ui;




import es.unizar.eina.M46_comidas.database.Racion;

public class RacionVisual {
    Racion racion;
    String nombre;


    public RacionVisual(String nombreParam,Racion racionParam ) {
        this.nombre = nombreParam;   
        this.racion = racionParam;
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

}