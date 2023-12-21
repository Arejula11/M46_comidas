package es.unizar.eina.M46_comidas.ui;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

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
