package es.unizar.eina.M46_comidas.ui;

// import static org.junit.Assert.*;

// import org.junit.Test;

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
        prueba_insertar_plato_incorrecto_nombre_null();
        prueba_insertar_plato_incorrecto_nombre_vacio();
        prueba_insertar_plato_incorrecto_nombre_no_string();
        prueba_insertar_plato_incorrecto_descripcion_null();
        prueba_insertar_plato_incorrecto_descripcion_vacio();
        prueba_insertar_plato_incorrecto_descripcion_no_string();
        prueba_insertar_plato_incorrecto_OTRO();

        

    }

    //@Test
    public void prueba_insertar_plato_correcto_PRIMERO() {
        Plato plato = new Plato("Pizza", "Masa, tomate y queso ", "PRIMERO", 10.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST CORRECTO PRIMERO", String.valueOf(resultado));
    }
    //@Test
    public void prueba_insertar_plato_correcto_SEGUNDO() {
        Plato plato = new Plato("Pollo con patatas", "Pollo y patatas", "SEGUNDO", 9.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST CORRECTO SEGUNDO", String.valueOf(resultado));
    }
    public void prueba_insertar_plato_correcto_POSTRE() {
        Plato plato = new Plato("Arroz con leche", "leche y arroz", "POSTRE", 4.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST CORRECTO POSTRE", String.valueOf(resultado));
    }

    public void prueba_insertar_plato_incorrecto_nombre_null() {
        Plato plato = new Plato(null, "Masa, tomate y queso ", "PRIMERO", 10.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST INCORRECTO NOMBRE NULL", String.valueOf(resultado));
    }
    public void prueba_insertar_plato_incorrecto_nombre_vacio() {
        Plato plato = new Plato("", "Masa, tomate y queso ", "PRIMERO", 10.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST INCORRECTO NOMBRE NULL", String.valueOf(resultado));
    }
    public void prueba_insertar_plato_incorrecto_nombre_no_string() {
        Plato plato = new Plato(33, "Masa, tomate y queso ", "PRIMERO", 10.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST INCORRECTO NOMBRE NULL", String.valueOf(resultado));
    }
    public void prueba_insertar_plato_incorrecto_descripcion_null() {
        Plato plato = new Plato("Pizza", null, "PRIMERO", 10.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST INCORRECTO DESCRIPCION NULL", String.valueOf(resultado));
    }
    public void prueba_insertar_plato_incorrecto_descripcion_vacio() {
        Plato plato = new Plato("Pizza", "", "PRIMERO", 10.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST INCORRECTO DESCRIPCION NULL", String.valueOf(resultado));
    }
     
    public void prueba_insertar_plato_incorrecto_descripcion_no_string() {
        Plato plato = new Plato("Pizza", 33, "PRIMERO", 10.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST INCORRECTO DESCRIPCION NULL", String.valueOf(resultado));
    }
    public void prueba_insertar_plato_incorrecto_OTRO() {
        Plato plato = new Plato("Pizza", "Masa, tomate y queso ", "OTRO", 10.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST CORRECTO POSTRE", String.valueOf(resultado));
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