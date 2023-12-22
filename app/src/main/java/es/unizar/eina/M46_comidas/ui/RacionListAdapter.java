package es.unizar.eina.M46_comidas.ui;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.function.Function;

import es.unizar.eina.M46_comidas.database.Plato;
import es.unizar.eina.M46_comidas.database.Racion;


public class RacionListAdapter extends ListAdapter<Racion, RacionViewHolder> {
    private int position;
    private Intent intent;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public RacionListAdapter(@NonNull DiffUtil.ItemCallback<Racion> diffCallback, Intent intentAux) {
        super(diffCallback);
        intent = intentAux;
    }


    @Override
    public RacionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RacionViewHolder.create(parent);
    }

    public Racion getCurrent() {
        return getItem(getPosition());
    }

    @Override
    public void onBindViewHolder(RacionViewHolder holder, int position) {

        Racion current = getItem(position);

        holder.bind(current);



        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getAdapterPosition());
                return false;
            }


        });

        Button eliminar = holder.getButton();
        /*eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Eliminar el elemento de la lista de datos
                getCurrentList().remove(holder.getAdapterPosition());

                // Notificar al adaptador que se ha eliminado un elemento en la posición dada
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });*/

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int racionEliminar = holder.getAdapterPosition();
                if (listener != null) {
                    listener.onItemClick(racionEliminar);
                }
            }
        });



        EditText editTextCantidad = holder.getEditText();
        editTextCantidad.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty()) {
                    // Actualiza el modelo con el nuevo texto
                    current.setCantidad(Integer.parseInt(charSequence.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });









    }


    static class RacionDiff extends DiffUtil.ItemCallback<Racion> {

        @Override
        public boolean areItemsTheSame(@NonNull Racion oldItem, @NonNull Racion newItem) {
            //android.util.Log.d ( "NoteDiff" , "areItemsTheSame " + oldItem.getId() + " vs " + newItem.getId() + " " +  (oldItem.getId() == newItem.getId()));
            return oldItem.getPlatoId() == newItem.getPlatoId() && oldItem.getPedidoId() == newItem.getPedidoId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Racion oldItem, @NonNull Racion newItem) {
            //android.util.Log.d ( "NoteDiff" , "areContentsTheSame " + oldItem.getTitle() + " vs " + newItem.getTitle() + " " + oldItem.getTitle().equals(newItem.getTitle()));
            // We are just worried about differences in visual representation, i.e. changes in the title
            return oldItem.getCantidad() == newItem.getCantidad() ;
        }
    }
}
