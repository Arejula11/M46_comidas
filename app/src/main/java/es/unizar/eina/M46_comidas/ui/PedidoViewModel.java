package es.unizar.eina.M46_comidas.ui;
import android.app.Application;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.M46_comidas.R;
import es.unizar.eina.M46_comidas.database.Pedido;
import es.unizar.eina.M46_comidas.database.ComidasRepository;

public class PedidoViewModel extends AndroidViewModel{

    private ComidasRepository mRepository;

    private final LiveData<List<Pedido>> mAllPedidos;
    private final LiveData<List<Pedido>> mAllPedidosNumTlfn;
    private final LiveData<List<Pedido>> mAllPedidosFecha;
    private final LiveData<List<Pedido>> mAllPedidosNombreCliente;



    public PedidoViewModel(Application application) {
        super(application);
        
        mRepository = new ComidasRepository(application);
        mAllPedidos = mRepository.getAllPedidos();
        mAllPedidosNumTlfn = mRepository.getAllPedidosNumTlfn();
        mAllPedidosFecha = mRepository.getAllPedidosFecha();
        mAllPedidosNombreCliente = mRepository.getAllPedidosNombreCliente();


    }

    public LiveData<List<Pedido>> getAllPedidos() { return mAllPedidos; }
    public LiveData<List<Pedido>> getAllPedidosNumTlfn() { return mAllPedidosNumTlfn; }
    public LiveData<List<Pedido>> getAllPedidosFecha() { return mAllPedidosFecha; }
    public LiveData<List<Pedido>> getAllPedidosNombreCliente() { return mAllPedidosNombreCliente; }
    public LiveData<List<Pedido>> getAllPedidosAndFilter(String estado) { return mRepository.getAllPedidosFiltradosAndFilter(estado); }
    public LiveData<List<Pedido>> getAllPedidosNumTlfnAndFilter(String estado) { return mRepository.getAllPedidosNumTlfnAndFilter(estado); }
    public LiveData<List<Pedido>> getAllPedidosFechaAndFilter(String estado) { return mRepository.getAllPedidosFechaAndFilter(estado); }
    public LiveData<List<Pedido>> getAllPedidosNombreClienteAndFilter(String estado) { return mRepository.getAllPedidosNombreClienteAndFilter(estado); }

    public long insert(Pedido pedido) { return mRepository.insert(pedido); }
    public void update(Pedido pedido) { mRepository.update(pedido); }
    public void delete(Pedido pedido) { mRepository.delete(pedido); }

    public static class orders_description extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_orders_description);
        }
    }
}

