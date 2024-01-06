package es.unizar.eina.M46_comidas.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import es.unizar.eina.M46_comidas.R;
import es.unizar.eina.M46_comidas.database.Pedido;

public class PedidoViewHolder extends RecyclerView.ViewHolder {

    private final TextView mNombreClienteTextView;
    private final TextView mTelefonoTextView;
    private final TextView mFechaTextView;
    private final TextView mPrecioTextView;
    private final TextView mEstadoTextView;


    private PedidoViewHolder(View itemView) {
        super(itemView);
        mNombreClienteTextView = itemView.findViewById(R.id.textViewNameCliente);
        mTelefonoTextView = itemView.findViewById(R.id.textViewTfn);
        mFechaTextView = itemView.findViewById(R.id.textViewFechaPedido);
        mPrecioTextView = itemView.findViewById(R.id.textViewPecioPedido);
        mEstadoTextView = itemView.findViewById(R.id.textViewEstadoPedido);
    }

    public void bind(Pedido pedido) {
        mNombreClienteTextView.setText("Nombre del cliente: " + pedido.getNombrecliente());
        mTelefonoTextView.setText("Teléfono: " + pedido.getTel());
        mFechaTextView.setText("Fecha: " + pedido.getFecha().toString().substring(0,4) + '-'
                + pedido.getFecha().toString().substring(4,6) + '-' + pedido.getFecha().toString().substring(6,8)
                + ' ' + pedido.getFecha().toString().substring(8,10) + ':' + pedido.getFecha().toString().substring(10));
        mPrecioTextView.setText("Precio: " + pedido.getPrecio());
        mEstadoTextView.setText("Estado: " + pedido.getEstado());
    }

    static PedidoViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_pedido_item, parent, false);
        return new PedidoViewHolder(view);
    }
}
