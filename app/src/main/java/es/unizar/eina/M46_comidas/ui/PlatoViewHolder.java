package es.unizar.eina.M46_comidas.ui;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import es.unizar.eina.M46_comidas.R;
import es.unizar.eina.M46_comidas.database.Plato;

public class PlatoViewHolder extends RecyclerView.ViewHolder {

    private final TextView mPlatoItemView;



    private PlatoViewHolder(View itemView) {
        super(itemView);
        mPlatoItemView = itemView.findViewById(R.id.textView2);

    }

    public void bind(String text) {
        mPlatoItemView.setText(text);
    }

    static PlatoViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_plato_item, parent, false);
        return new PlatoViewHolder(view);
    }





}


