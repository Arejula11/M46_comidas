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

    private final TextView mNombreTextView;
    private final TextView mIngredienteTextView;
    private final TextView mPrecioTextView;
    private final TextView mCategoriaTextView;







    private PlatoViewHolder(View itemView) {
        super(itemView);
        mNombreTextView = itemView.findViewById(R.id.textViewNombre);
        mIngredienteTextView = itemView.findViewById(R.id.textViewIngrediente);
        mPrecioTextView = itemView.findViewById(R.id.textViewPrecio);
        mCategoriaTextView = itemView.findViewById(R.id.textViewCategoria);
    }

    public void bind(Plato plato) {
        mNombreTextView.setText("Nombre del plato: " + plato.getNombre());
        mIngredienteTextView.setText("Ingredientes: " + plato.getIngredientes());
        mPrecioTextView.setText("Precio: " + plato.getPrecio() + "€");
        mCategoriaTextView.setText("Categoría: " + plato.getCategoria());
    }

    static PlatoViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_plato_item, parent, false);
        return new PlatoViewHolder(view);
    }







}


