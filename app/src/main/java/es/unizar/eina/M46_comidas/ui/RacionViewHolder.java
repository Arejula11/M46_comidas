package es.unizar.eina.M46_comidas.ui;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;


import androidx.recyclerview.widget.RecyclerView;

import es.unizar.eina.M46_comidas.R;
import es.unizar.eina.M46_comidas.database.ComidasRepository;
import es.unizar.eina.M46_comidas.database.Plato;
import es.unizar.eina.M46_comidas.database.Racion;


public class RacionViewHolder extends RecyclerView.ViewHolder {

    private final TextView mRacionTextView;
    private final EditText mCantidadEditText;
    private final Button mEliminarButton;






    private RacionViewHolder(View itemView) {
        super(itemView);
        mRacionTextView = itemView.findViewById(R.id.textViewRacion);
        mCantidadEditText = itemView.findViewById(R.id.editTextCantidad);
        mEliminarButton = itemView.findViewById(R.id.borrarRacion);

    }

    public void bind(RacionVisual racionVisual) {

        mRacionTextView.setText(String.valueOf(racionVisual.racion.getPlatoId()));
        mCantidadEditText.setText(String.valueOf(racionVisual.racion.getCantidad()));

    }

    public EditText getEditText(){
        return mCantidadEditText;
    }

    public Button getButton(){
        return mEliminarButton;
    }

    static RacionViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_edit_raciones, parent, false);
        return new RacionViewHolder(view);
    }







}


