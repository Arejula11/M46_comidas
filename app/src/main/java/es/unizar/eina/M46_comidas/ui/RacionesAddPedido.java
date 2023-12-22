package es.unizar.eina.M46_comidas.ui;
import java.util.ArrayList;
import java.util.List;

import es.unizar.eina.M46_comidas.database.Pedido;
import es.unizar.eina.M46_comidas.database.Plato;
import es.unizar.eina.M46_comidas.database.Racion;

public class RacionesAddPedido {


        private static RacionesAddPedido instance;

        private Pedido pedido;
        private List<Racion> raciones;
        private List<Plato> platos;

        private String nombre;
        private int telefono;

        private String date;
        private String time;


    private RacionesAddPedido(Pedido pedido2) {
            raciones = new ArrayList<>();
        platos = new ArrayList<>();
        pedido = pedido2;
        nombre = "";
        telefono = 0;
        }

        public static synchronized RacionesAddPedido getInstance(Pedido pedido2) {
            if (instance == null) {
                instance = new RacionesAddPedido(pedido2);
            }
            return instance;
        }

        public List<Racion> getRaciones() {
            return raciones;
        }

        public void agregarRacion(Racion nuevaRacion) {
            raciones.add(nuevaRacion);
        }

        public void eliminarRacion(int i) {
            raciones.remove(i);
        }
        public List<Racion> getPlatos() {
        return raciones;
    }

        public void agregarPlato(Plato nuevoPlato) {
            platos.add(nuevoPlato);
        }

        public void eliminarRacion(Plato plato) {
            platos.remove(plato);
    }

        public void modificarPedido(Pedido pedido2) {pedido = pedido2;  }

        public Double getPrecio(int i){
            return platos.get(i).getPrecio();
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



}
