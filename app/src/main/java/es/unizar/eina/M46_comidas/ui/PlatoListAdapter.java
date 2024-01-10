package es.unizar.eina.M46_comidas.ui;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import es.unizar.eina.M46_comidas.database.Pedido;
import es.unizar.eina.M46_comidas.database.Plato;

public class PlatoListAdapter extends ListAdapter<Plato, PlatoViewHolder> {
    private int position;
    private Intent intent;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public PlatoListAdapter(@NonNull DiffUtil.ItemCallback<Plato> diffCallback, Intent intentAux) {
        super(diffCallback);
        intent = intentAux;
    }

    @Override
    public PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PlatoViewHolder.create(parent);
    }

    public Plato getCurrent() {
        return getItem(getPosition());
    }

    @Override
    public void onBindViewHolder(PlatoViewHolder holder, int position) {

        Plato current = getItem(position);
        holder.bind(current);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getAdapterPosition());
                return false;
            }

        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origen = intent.getStringExtra("origen");
                if(origen != null && origen.equals("plates_for_orderAdd")){
                    Intent intent2 = new Intent(v.getContext(), add_order.class);
                    if (intent != null && intent.hasExtra("operacion")) {

                        intent2.putExtra("operacion", intent.getStringExtra("operacion"));
                    }
                    intent2.putExtra("Objeto", current);
                    v.getContext().startActivity(intent2);
                }else if(origen != null && origen.equals("plates_for_orderEdit")){
                    Intent intent2 = new Intent(v.getContext(), edit_order.class);
                    if (intent != null && intent.hasExtra("operacion")) {

                        intent2.putExtra("operacion", intent.getStringExtra("operacion"));
                    }
                    intent2.putExtra("Objeto", current);
                    intent2.putExtra("Pedido", intent.getSerializableExtra("Pedido"));
                    v.getContext().startActivity(intent2);
                }else{
                        Intent intent2 = new Intent(v.getContext(), plato_description.class);
                        if (intent != null && intent.hasExtra("operacion")) {

                            intent2.putExtra("operacion", intent.getStringExtra("operacion"));
                        }
                        intent2.putExtra("Objeto", current);
                        v.getContext().startActivity(intent2);
                }

            }
        });

    }


    static class PlatoDiff extends DiffUtil.ItemCallback<Plato> {

        @Override
        public boolean areItemsTheSame(@NonNull Plato oldItem, @NonNull Plato newItem) {
            //android.util.Log.d ( "NoteDiff" , "areItemsTheSame " + oldItem.getId() + " vs " + newItem.getId() + " " +  (oldItem.getId() == newItem.getId()));
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Plato oldItem, @NonNull Plato newItem) {
            //android.util.Log.d ( "NoteDiff" , "areContentsTheSame " + oldItem.getTitle() + " vs " + newItem.getTitle() + " " + oldItem.getTitle().equals(newItem.getTitle()));
            // We are just worried about differences in visual representation, i.e. changes in the title
            return oldItem.getNombre().equals(newItem.getNombre());
        }
    }
}


