package es.unizar.eina.M46_comidas.test;

import static org.junit.Assert.*;

import org.junit.Test;

import android.app.Application;
import android.util.Log;

import es.unizar.eina.M46_comidas.database.ComidasRepository;
import es.unizar.eina.M46_comidas.database.Pedido;
import es.unizar.eina.M46_comidas.database.Plato;

/**

 Example local unit test, which will execute on the development machine (host).*
 @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    ComidasRepository mRepository;
    //@Test
    public UnitTest(Application aplication) {
        mRepository = new ComidasRepository(aplication);

    }

    //@Test
    public void pruebas_unitarias(){
        prueba_insertar_plato_correcto_PRIMERO();
        prueba_insertar_plato_correcto_SEGUNDO();
        prueba_insertar_plato_correcto_POSTRE();
    }

    //@Test
    public void prueba_insertar_plato_correcto_PRIMERO() {
        Plato plato = new Plato("Pizza", "Masa, tomate y queso ", "PRIMERO", 10.0);
        assertEquals(mRepository.insert(plato),1);
    }
    //@Test
    public void prueba_insertar_plato_correcto_SEGUNDO() {
        Plato plato = new Plato("Pollo con patatas", "Pollo y patatas", "SEGUNDO", 9.0);
        assertEquals(mRepository.insert(plato),1);
    }
    public void prueba_insertar_plato_correcto_POSTRE() {
        Plato plato = new Plato("Arroz con leche", "leche y arroz", "POSTRE", 4.0);
        assertEquals(mRepository.insert(plato),1);
    }



    //@Test
    public void prueba_volumen() {

        for(int i =0; i<100; i++){
            Plato plato = new Plato("PlatoPrueba", "plato, prueba", "PRIMERO", Double.valueOf(i));
            mRepository.insert(plato);
        }
        for(int i =0; i<2000; i++){
            Pedido pedido = new Pedido("PedidoPrueba", 000000001, 202305101435L, "Solicitado", i);
            mRepository.insert(pedido);
        }
    }

    //@Test
    public void prueba_sobrecarga() {
        String ingr = "00000000000000000000000000000";
        for(int i =0; i<200; i++){

            Plato plato = new Plato("PlatoPrueba", ingr, "PRIMERO", Double.valueOf(i));
            mRepository.insert(plato);
            for(int x = 0; x < 1000000; x++){
                ingr += ('a');

            }
            Log.d("VALOR", String.valueOf(i));
        }

    }

    // Elimina todos los datos de la base de datos
    public void eliminar_datos(){

        mRepository.deleteAllRaciones();
        mRepository.deleteAllPedidos();
        mRepository.deleteAllPlatos();

    }
}