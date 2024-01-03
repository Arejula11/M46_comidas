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
        //clases equivalencias validas para insertar plato
        prueba_insertar_plato_correcto_PRIMERO();
        prueba_insertar_plato_correcto_SEGUNDO();
        prueba_insertar_plato_correcto_POSTRE();

        //clases equivalencias invalidas para insertar plato
        //prueba_insertar_plato_incorrecto_nombre_null();
        prueba_insertar_plato_incorrecto_nombre_vacio();
        //prueba_insertar_plato_incorrecto_ingredientes_null();
        prueba_insertar_plato_incorrecto_ingredientes_vacio();
        prueba_insertar_plato_incorrecto_OTRO();
        //prueba_insertar_plato_incorrecto_precio_null();
        prueba_insertar_plato_incorrecto_precio_negativo();

        Plato plato = new Plato("Pizza", "Masa, tomate y queso ", "PRIMERO", 10.0);
        Plato plato2 = new Plato("Pizza2", "Masa, tomate y queso ", "PRIMERO", 10.0);
        mRepository.insert(plato);
        mRepository.insert(plato2);
        plato2.setId(-1);

        //clases equivalencias validas para editar plato
        prueba_editar_plato_correcto_PRIMERO(plato);
        prueba_editar_plato_correcto_SEGUNDO(plato);
        prueba_editar_plato_correcto_POSTRE(plato);


        //clases equivalencias invalidas para editar plato
        //prueba_editar_plato_incorrecto_nombre_null(plato);
        prueba_editar_plato_incorrecto_nombre_vacio(plato);
        //prueba_editar_plato_incorrecto_ingredientes_null(plato);
        prueba_editar_plato_incorrecto_ingredientes_vacio(plato);
        prueba_editar_plato_incorrecto_OTRO(plato);
        //prueba_editar_plato_incorrecto_precio_null(plato);
        prueba_editar_plato_incorrecto_precio_negativo(plato);

        //clases equivalencia validas para eliminar plato
        prueba_eliminar_plato_correcto(plato); //revisar, no lo elimina
        mRepository.insert(plato);
        //clases equivalencias invalidas para eliminar plato
        prueba_eliminar_plato_incorrecto_id_negativo(plato2); //revisar el valor devuelto no es -1

        //clases equivalencias validas para insertar pedido
        //clases equivalencias invalidas para insertar pedido

         //clases equivalencias validas para editar pedido
        //clases equivalencias invalidas para editar pedido

        //clases equivalencia validas para eliminar pedido
        //clases equivalencias invalidas para eliminar pedido



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
        Plato plato = new Plato("Pizza", "Masa, tomate y queso ", "OTRO", 10.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST INCORRECTO OTRO", String.valueOf(resultado));
    }
    public void prueba_insertar_plato_incorrecto_precio_null() {
        Plato plato = new Plato("Pizza", "Masa, tomate y queso ", "PRIMERO", null);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST INCORRECTO PRECIO NULL", String.valueOf(resultado));
    }


    public void prueba_insertar_plato_incorrecto_precio_negativo() {
        Plato plato = new Plato("Pizza", "Masa, tomate y queso ", "PRIMERO", -1.0);
        Long resultado = mRepository.insert(plato);
        Log.d("TEST INCORRECTO PRECIO NEGATIVO", String.valueOf(resultado));
    }


  public void prueba_editar_plato_correcto_PRIMERO(Plato plato){
        Plato platoMod = new Plato ("Pizza margarita", "Masa, tomate y mozzarella","PRIMERO",11.0);
        platoMod.setId(plato.getId());
        Long resultado = mRepository.update(platoMod);
      Log.d("TEST CORRECTO EDITAR PLATO", String.valueOf(resultado));


    }
    public void prueba_editar_plato_correcto_SEGUNDO(Plato plato) {



    }
    public void prueba_editar_plato_correcto_POSTRE(Plato plato) {

    }

    public void prueba_editar_plato_incorrecto_nombre_null(Plato plato) {

    }
    public void prueba_editar_plato_incorrecto_nombre_vacio(Plato plato) {

    }

    public void prueba_editar_plato_incorrecto_ingredientes_null(Plato plato) {

    }
    public void prueba_editar_plato_incorrecto_ingredientes_vacio(Plato plato) {

    }


    public void prueba_editar_plato_incorrecto_OTRO(Plato plato) {

    }
    public void prueba_editar_plato_incorrecto_precio_null(Plato plato) {

    }


    public void prueba_editar_plato_incorrecto_precio_negativo(Plato plato) {

    }

    public void prueba_editar_plato_incorrecto_id_null(Plato plato) {

    }
    public void prueba_editar_plato_incorrecto_id_negativo(Plato plato) {

    }
    public void prueba_eliminar_plato_correcto(Plato plato) {

        Long resultado = mRepository.delete(plato);
        Log.d("TEST CORRECTO ELIMINAR PLATO", String.valueOf(resultado));
    }
    public void prueba_eliminar_plato_incorrecto_id_negativo(Plato plato) {
        Long resultado = mRepository.delete(plato);
        Log.d("TEST INCORRECTO ELIMINAR PLATO", String.valueOf(resultado));
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
