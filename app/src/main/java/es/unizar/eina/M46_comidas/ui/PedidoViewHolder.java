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
    private final TextView mRacionesTextView;
    private final TextView mPrecioTextView;

    private PedidoViewHolder(View itemView) {
        super(itemView);
        mNombreClienteTextView = itemView.findViewById(R.id.textViewNameCliente);
        mTelefonoTextView = itemView.findViewById(R.id.textViewTfn);
        mFechaTextView = itemView.findViewById(R.id.textViewFechaPedido);
        mRacionesTextView = itemView.findViewById(R.id.textViewRacion);
        mPrecioTextView = itemView.findViewById(R.id.textViewPecioPedido);
    }

    public void bind(Pedido pedido) {
        mNombreClienteTextView.setText("Nombre del cliente: " + pedido.getNombrecliente());
        mTelefonoTextView.setText("Teléfono: " + pedido.getTel());
        mFechaTextView.setText("Fecha: " + pedido.getFecha());
       // mRacionesTextView.setText("Raciones: " + pedido.getRaciones());
    }

    static PedidoViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_pedido_item, parent, false);
        return new PedidoViewHolder(view);
    }
}
