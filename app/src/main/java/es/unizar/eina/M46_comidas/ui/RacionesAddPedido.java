package es.unizar.eina.M46_comidas.ui;
import java.util.ArrayList;
import java.util.List;

import es.unizar.eina.M46_comidas.database.Pedido;
import es.unizar.eina.M46_comidas.database.Plato;
import es.unizar.eina.M46_comidas.database.Racion;

public class RacionesAddPedido {


        private static RacionesAddPedido instance;

        private List<RacionVisual> raciones;

        private String nombre;
        private int telefono;

        private String date;
        private String time;
        private boolean primeraVez;


    private RacionesAddPedido(Pedido pedido2) {
            raciones = new ArrayList<>();
        nombre = "";
        telefono = 0;
        primeraVez = true;
        }

        public static synchronized RacionesAddPedido getInstance(Pedido pedido2) {
            if (instance == null) {
                instance = new RacionesAddPedido(pedido2);
            }
            return instance;
        }

        public List<RacionVisual> getRaciones() {
            return raciones;
        }

        public void agregarRacion(RacionVisual nuevaRacion) {
            raciones.add(nuevaRacion);
        }

        public void eliminarRacion(int i) {
            raciones.remove(i);
        }




        public void reset(){instance = null;}

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre2){
        nombre = nombre2;
    }

    public int getTelefono(){
        return telefono;
    }
    public void setTelefono(int telefono2){
        telefono = telefono2;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date2){
        date = date2;
    }

    public String getTime(){
        return time;
    }
    public void setTime(String time2){
        time = time2;
    }

    public boolean getPrimeraVez(){return primeraVez;}
    public void setPrimeraVez(boolean aux ){
        this.primeraVez = aux;
    }


}
