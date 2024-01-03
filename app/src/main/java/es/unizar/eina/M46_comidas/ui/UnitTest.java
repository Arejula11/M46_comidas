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

        //Log.d("TEST INSERTAR", "comienza");
        //clases equivalencias validas para insertar plato
        //long id_primero = prueba_insertar_plato_correcto_PRIMERO();
        //long id_segundo = prueba_insertar_plato_correcto_SEGUNDO();
        //long id_postre = prueba_insertar_plato_correcto_POSTRE();

        //clases equivalencias invalidas para insertar plato
        //prueba_insertar_plato_incorrecto_nombre_null();
        //prueba_insertar_plato_incorrecto_nombre_vacio();
        //prueba_insertar_plato_incorrecto_ingredientes_null();
        //prueba_insertar_plato_incorrecto_ingredientes_vacio();
        //prueba_insertar_plato_incorrecto_OTRO();
        //prueba_insertar_plato_incorrecto_precio_null();
        //prueba_insertar_plato_incorrecto_precio_negativo();

        //Log.d("TEST EDITAR", "comienza");
        //Plato plato = new Plato("Pizza", "Masa, tomate y queso ", "PRIMERO", 10.0);
        //Plato plato2 = new Plato("Pizza2", "Masa, tomate y queso ", "PRIMERO", 10.0);
        //long id = mRepository.insert(plato);
        //long id2 = mRepository.insert(plato2);
        //plato2.setId(-1);

        //clases equivalencias validas para editar plato
        //prueba_editar_plato_correcto_PRIMERO(id_primero);
        //prueba_editar_plato_correcto_SEGUNDO(id_segundo);
        //prueba_editar_plato_correcto_POSTRE(id_postre);


        //clases equivalencias invalidas para editar plato
        //prueba_editar_plato_incorrecto_nombre_null(id_primero);
        //prueba_editar_plato_incorrecto_nombre_vacio(id_primero);
        //prueba_editar_plato_incorrecto_ingredientes_null(id_primero);
        //prueba_editar_plato_incorrecto_ingredientes_vacio(id_primero);
        //prueba_editar_plato_incorrecto_OTRO(id_primero);
        //prueba_editar_plato_incorrecto_precio_null(id_primero);
        //prueba_editar_plato_incorrecto_precio_negativo(id_primero);
        //prueba_editar_plato_incorrecto_id_null(id_primero);
        //prueba_editar_plato_incorrecto_id_negativo(id_primero);

        Log.d("TEST ELIMINAR", "comienza");

        //clases equivalencia validas para eliminar plato
        //prueba_eliminar_plato_correcto(id_primero); //revisar, no lo elimina
        //mRepository.insert(plato);
        //clases equivalencias invalidas para eliminar plato
        //prueba_eliminar_plato_incorrecto_id_negativo(plato2); //revisar el valor devuelto no es -1


        Log.d("TEST INSERTAR", "comienza");

        //clases equivalencias validas para insertar pedido
        long id_solicitado = prueba_insertar_pedido_correcto_SOLICITADO();
        long id_preparado = prueba_insertar_pedido_correcto_PREPARADO();
        long id_recogido = prueba_insertar_pedido_correcto_RECOGIDO();
        //clases equivalencias invalidas para insertar pedido
        prueba_insertar_pedido_incorrecto_nombre_null();
        prueba_insertar_pedido_incorrecto_nombre_vacio();
        prueba_insertar_pedido_incorrecto_telefono_null();
        prueba_insertar_pedido_incorrecto_telefono_mal_formato();
        prueba_insertar_pedido_incorrecto_estado_OTRO();
        prueba_insertar_pedido_incorrecto_precio_null();
        prueba_insertar_pedido_incorrecto_precio_negativo();
        prueba_insertar_pedido_incorrecto_id_negativo();

         //clases equivalencias validas para editar pedido
        prueba_editar_pedido_correcto_SOLICITADO();
        prueba_editar_pedido_correcto_PREPARADO();
        prueba_editar_pedido_correcto_RECOGIDO();
        //clases equivalencias invalidas para editar pedido

        //clases equivalencia validas para eliminar pedido
        //clases equivalencias invalidas para eliminar pedido



    }

    //@Test
    public long prueba_insertar_plato_correcto_PRIMERO() {
        Plato plato = new Plato("Pizza", "Masa, tomate y queso", "PRIMERO", 10.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST CORRECTO PRIMERO", String.valueOf(resultado));
        return resultado;
    }
    //@Test
    public long prueba_insertar_plato_correcto_SEGUNDO() {
        Plato plato = new Plato("Pollo con patatas", "Pollo y patatas", "SEGUNDO", 9.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST CORRECTO SEGUNDO", String.valueOf(resultado));
        return resultado;
    }
    public long prueba_insertar_plato_correcto_POSTRE() {
        Plato plato = new Plato("Arroz con leche", "leche y arroz", "POSTRE", 4.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST CORRECTO POSTRE", String.valueOf(resultado));
        return resultado;
    }

    public void prueba_insertar_plato_incorrecto_nombre_null() {
        Plato plato = new Plato(null, "Masa, tomate y queso", "PRIMERO", 10.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST INCORRECTO NOMBRE NULL", String.valueOf(resultado));
    }
    public void prueba_insertar_plato_incorrecto_nombre_vacio() {
        Plato plato = new Plato("", "Masa, tomate y queso", "PRIMERO", 10.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST INCORRECTO NOMBRE NULL", String.valueOf(resultado));
    }

    public void prueba_insertar_plato_incorrecto_ingredientes_null() {
        Plato plato = new Plato("Pizza", null, "PRIMERO", 10.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST INCORRECTO INGREDIENTES NULL", String.valueOf(resultado));
    }
    public void prueba_insertar_plato_incorrecto_ingredientes_vacio() {
        Plato plato = new Plato("Pizza", "", "PRIMERO", 10.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST INCORRECTO INGREDIENTES NULL", String.valueOf(resultado));
    }


    public void prueba_insertar_plato_incorrecto_OTRO() {
        Plato plato = new Plato("Pizza", "Masa, tomate y queso", "OTRO", 10.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST INCORRECTO OTRO", String.valueOf(resultado));
    }
    public void prueba_insertar_plato_incorrecto_precio_null() {
        Plato plato = new Plato("Pizza", "Masa, tomate y queso", "PRIMERO", null);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST INCORRECTO PRECIO NULL", String.valueOf(resultado));
    }


    public void prueba_insertar_plato_incorrecto_precio_negativo() {
        Plato plato = new Plato("Pizza", "Masa, tomate y queso", "PRIMERO", -1.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST INCORRECTO PRECIO NEGATIVO", String.valueOf(resultado));
    }


  public void prueba_editar_plato_correcto_PRIMERO(long id){
        Plato platoMod = new Plato ("Pizza Margarita", "Masa, tomate y queso ", "PRIMERO", 10.0);
        platoMod.setId((int) id);
        int resultado = mRepository.update(platoMod);
      Log.d("TEST CORRECTO EDITAR PLATO", String.valueOf(resultado));


    }
    public void prueba_editar_plato_correcto_SEGUNDO(long id) {
        Plato platoMod = new Plato ("Pollo con patatas", "Pollo y patatas", "SEGUNDO", 12.0);
        platoMod.setId((int) id);
        int resultado = mRepository.update(platoMod);
        Log.d("TEST CORRECTO EDITAR PLATO", String.valueOf(resultado));



    }
    public void prueba_editar_plato_correcto_POSTRE(long id) {
        Plato platoMod = new Plato ("Arroz con leche", "leche, arroz y canela", "POSTRE", 4.0);
        platoMod.setId((int) id);
        int resultado = mRepository.update(platoMod);
        Log.d("TEST CORRECTO EDITAR PLATO", String.valueOf(resultado));
    }

    public void prueba_editar_plato_incorrecto_nombre_null(long id) {
        Plato platoMod = new Plato (null, "Masa, tomate y queso ", "PRIMERO", 10.0);
        platoMod.setId((int) id);
        int resultado = mRepository.update(platoMod);
        Log.d("TEST CORRECTO EDITAR PLATO", String.valueOf(resultado));
    }
    public void prueba_editar_plato_incorrecto_nombre_vacio(long id) {
        Plato platoMod = new Plato ("", "Masa, tomate y queso ", "PRIMERO", 10.0);
        platoMod.setId((int) id);
        int resultado = mRepository.update(platoMod);
        Log.d("TEST CORRECTO EDITAR PLATO", String.valueOf(resultado));
    }

    public void prueba_editar_plato_incorrecto_ingredientes_null(long id) {
        Plato platoMod = new Plato ("Pizza Margarita", null, "PRIMERO", 10.0);
        platoMod.setId((int) id);
        int resultado = mRepository.update(platoMod);
        Log.d("TEST INCORRECTO EDITAR PLATO", String.valueOf(resultado));
    }
    public void prueba_editar_plato_incorrecto_ingredientes_vacio(long id) {
        Plato platoMod = new Plato ("Pizza Margarita", "", "PRIMERO", 10.0);
        platoMod.setId((int) id);
        int resultado = mRepository.update(platoMod);
        Log.d("TEST INCORRECTO EDITAR PLATO", String.valueOf(resultado));
    }


    public void prueba_editar_plato_incorrecto_OTRO(long id) {
        Plato platoMod = new Plato ("Pizza Margarita", "Masa, tomate y queso", "TERCERO", 10.0);
        platoMod.setId((int) id);
        int resultado = mRepository.update(platoMod);
        Log.d("TEST INCORRECTO EDITAR PLATO", String.valueOf(resultado));
    }
    public void prueba_editar_plato_incorrecto_precio_null(long id) {
        Plato platoMod = new Plato ("Pizza Margarita", "Masa, tomate y queso", "PRIMERO", null);
        platoMod.setId((int) id);
        int resultado = mRepository.update(platoMod);
        Log.d("TEST INCORRECTO EDITAR PLATO", String.valueOf(resultado));
    }


    public void prueba_editar_plato_incorrecto_precio_negativo(long id) {
        Plato platoMod = new Plato ("Pizza Margarita", "Masa, tomate y queso", "PRIMERO", -10.0);
        platoMod.setId((int) id);
        int resultado = mRepository.update(platoMod);
        Log.d("TEST INCORRECTO EDITAR PLATO", String.valueOf(resultado));
    }

    public void prueba_editar_plato_incorrecto_id_null(long id) {
        Plato platoMod = new Plato ("Pizza Margarita", "Masa, tomate y queso ", "PRIMERO", 10.0);
        int resultado = mRepository.update(platoMod);
        Log.d("TEST INCORRECTO EDITAR PLATO", String.valueOf(resultado));
    }
    public void prueba_editar_plato_incorrecto_id_negativo(long id) {
        Plato platoMod = new Plato ("Pizza Margarita", "Masa, tomate y queso ", "PRIMERO", 10.0);
        platoMod.setId(-1);
        int resultado = mRepository.update(platoMod);
        Log.d("TEST INCORRECTO EDITAR PLATO", String.valueOf(resultado));
    }
    public void prueba_eliminar_plato_correcto(long id) {
        Plato platoMod = new Plato ("Margarita", " tomate y queso", "PRIMERO", 0.0);
        platoMod.setId((int) id);
        int resultado = mRepository.delete(platoMod);
        Log.d("TEST CORRECTO ELIMINAR PLATO", String.valueOf(resultado));
    }
    public void prueba_eliminar_plato_incorrecto_id_negativo(Plato plato) {
        int resultado = mRepository.delete(plato);
        Log.d("TEST INCORRECTO ELIMINAR PLATO", String.valueOf(resultado));
    }
    //@Test
    public long prueba_insertar_pedido_correcto_SOLICITADO() {
        Pedido pedido = new Pedido("Juan", 600000000, 202305182036L, "SOLICITADO", 10.0);
        Long resultado = mRepository.insert(pedido);
        Log.d("TEST CORRECTO SOLICITADO", String.valueOf(resultado));
        return resultado;
    }
    //@Test
    public long prueba_insertar_pedido_correcto_PREPARADO() {
        Pedido pedido = new Pedido("Pedro", 611111111, 202306202036L, "PREPARADO", 10.0);
        Long resultado = mRepository.insert(pedido);
        Log.d("TEST CORRECTO PREPARADO", String.valueOf(resultado));
        return resultado;
    }
    public long prueba_insertar_pedido_correcto_RECOGIDO() {
        Pedido pedido = new Pedido("Lucia", 622222222, 202307052036L, "RECOGIDO", 10.0);
        Long resultado = mRepository.insert(pedido);
        Log.d("TEST CORRECTO RECOGIDO", String.valueOf(resultado));
        return resultado;
    }
    //@Test
    public void prueba_insertar_pedido_incorrecto_nombre_null() {
        Pedido pedido = new Pedido(null, 600000000, 202305182036L, "SOLICITADO", 10.0);
        Long resultado = mRepository.insert(pedido);
        Log.d("TEST CORRECTO SOLICITADO", String.valueOf(resultado));
    }
    //@Test
    public void prueba_insertar_pedido_incorrecto_nombre_vacio() {
        Pedido pedido = new Pedido("", 600000000, 202305182036L, "SOLICITADO", 10.0);
        Long resultado = mRepository.insert(pedido);
        Log.d("TEST CORRECTO SOLICITADO", String.valueOf(resultado));
    }
    //@Test
    public void prueba_insertar_pedido_incorrecto_telefono_null() {
        Pedido pedido = new Pedido("Juan", null, 202305182036L, "SOLICITADO", 10.0);
        Long resultado = mRepository.insert(pedido);
        Log.d("TEST CORRECTO SOLICITADO", String.valueOf(resultado));
    }
    //@Test
    public void prueba_insertar_pedido_incorrecto_telefono_mal_formato() {
        Pedido pedido = new Pedido("Juan", 6000, 202305182036L, "SOLICITADO", 10.0);
        Long resultado = mRepository.insert(pedido);
        Log.d("TEST CORRECTO SOLICITADO", String.valueOf(resultado));
    }
    //@Test
    public void prueba_insertar_pedido_incorrecto_estado_OTRO() {
        Pedido pedido = new Pedido("Juan", 600000000, 202305182036L, "TERCERO", 10.0);
        Long resultado = mRepository.insert(pedido);
        Log.d("TEST CORRECTO SOLICITADO", String.valueOf(resultado));
    }
    //@Test
    public void prueba_insertar_pedido_incorrecto_precio_null() {
        Pedido pedido = new Pedido("Juan", 600000000, 202305182036L, "SOLICITADO", null);
        Long resultado = mRepository.insert(pedido);
        Log.d("TEST CORRECTO SOLICITADO", String.valueOf(resultado));
    }
    //@Test
    public void prueba_insertar_pedido_incorrecto_precio_negativo() {
        Pedido pedido = new Pedido("Juan", 600000000, 202305182036L, "SOLICITADO", -1.0);
        Long resultado = mRepository.insert(pedido);
        Log.d("TEST CORRECTO SOLICITADO", String.valueOf(resultado));
    }
    //@Test
    public void prueba_insertar_pedido_incorrecto_id_negativo() {
        Pedido pedido = new Pedido("Juan", 600000123, 202305182036L, "SOLICITADO", 10.0);
        pedido.setId(-1);
        Long resultado = mRepository.insert(pedido);
        Log.d("TEST CORRECTO SOLICITADO", String.valueOf(resultado));
    }
    //@Test
    public void prueba_editar_pedido_correcto_SOLICITADO() {
        Pedido pedido = new Pedido("Juan", 777777777, 202305182036L, "SOLICITADO", 10.0);
        int resultado = mRepository.update(pedido);
        Log.d("TEST CORRECTO SOLICITADO", String.valueOf(resultado));
    }
    public void prueba_editar_pedido_correcto_PREPARADO() {
        Pedido pedido = new Pedido("Juan", 600000000, 202306182036L, "PREPARADO", 10.0);
        int resultado = mRepository.update(pedido);
        Log.d("TEST CORRECTO SOLICITADO", String.valueOf(resultado));
    }
    public void prueba_editar_pedido_correcto_RECOGIDO() {
        Pedido pedido = new Pedido("Juan", 600000000, 202305182036L, "SOLICITADO", 1.0);
        int resultado = mRepository.update(pedido);
        Log.d("TEST CORRECTO SOLICITADO", String.valueOf(resultado));
    }










    //@Test
    public void prueba_volumen() {

        for(int i =0; i<100; i++){
            Plato plato = new Plato("PlatoPrueba", "plato, prueba", "PRIMERO", Double.valueOf(i));
            mRepository.insert(plato);
        }
        for(Double i =0.0; i<2000; i++){
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
