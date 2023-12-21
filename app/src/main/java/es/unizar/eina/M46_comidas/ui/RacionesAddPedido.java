package es.unizar.eina.M46_comidas.ui;
import java.util.ArrayList;
import java.util.List;

import es.unizar.eina.M46_comidas.database.Pedido;
import es.unizar.eina.M46_comidas.database.Racion;

public class RacionesAddPedido {


        private static RacionesAddPedido instance;

        private Pedido pedido;
        private List<Racion> raciones;

        private RacionesAddPedido(Pedido pedido2) {
            raciones = new ArrayList<>();
            pedido = pedido2;
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
        public void eliminarRacion(Racion racion) {
            raciones.remove(racion);
        }

        public void modificarPedido(Pedido pedido2) {pedido = pedido2;  }



}
