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

    LiveData<List<Pedido>> getAllPedidos() { return mAllPedidos; }
    LiveData<List<Pedido>> getAllPedidosNumTlfn() { return mAllPedidosNumTlfn; }
    LiveData<List<Pedido>> getAllPedidosFecha() { return mAllPedidosFecha; }
    LiveData<List<Pedido>> getAllPedidosNombreCliente() { return mAllPedidosNombreCliente; }

    public LiveData<Long> insert(Pedido pedido) { return mRepository.insert(pedido); }

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

