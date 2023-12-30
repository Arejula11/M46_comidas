package es.unizar.eina.M46_comidas.test;

import org.junit.Test;

import static org.junit.Assert.*;

import es.unizar.eina.M46_comidas.database.ComidasRepository;
import es.unizar.eina.M46_comidas.database.Pedido;
import es.unizar.eina.M46_comidas.database.Plato;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    ComidasRepository comidasRepository;
    @Test
    public void prueba_volumen() {

        for(int i =0; i<100; i++){
            Plato plato = new Plato("PlatoPrueba", "plato, prueba", "PRIMERO", Double.valueOf(i));
            comidasRepository.insert(plato);
        }
        for(int i =0; i<2000; i++){
            Pedido pedido = new Pedido("PedidoPrueba", 000000001, 202305101435L, "Solicitado", i);
            comidasRepository.insert(pedido);
        }
    }
    @Test
    public void prueba_sobrecarga() {
        String ingr = "00000000000000000000000000000";
        for(int i =0; i<100; i++){

            Plato plato = new Plato("PlatoPrueba", ingr, "PRIMERO", Double.valueOf(i));
            comidasRepository.insert(plato);
            for(int x = 0; x < 3; x++){
                ingr += ('a' + i);
            }
        }

    }
}