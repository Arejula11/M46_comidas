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


public class RacionListAdapter extends ListAdapter<RacionVisual, RacionViewHolder> {
    private int position;
    private Intent intent;

    private OnItemClickListener listener;

    private TextChangedListener textChangedListener;

    public interface TextChangedListener {
        void onTextChanged(int position, String newText);
    }
    public void setTextChangedListener(TextChangedListener listener) {
        this.textChangedListener = listener;
    }

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

    public RacionListAdapter(@NonNull DiffUtil.ItemCallback<RacionVisual> diffCallback, Intent intentAux) {
        super(diffCallback);
        intent = intentAux;
    }


    @Override
    public RacionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RacionViewHolder.create(parent);
    }

    public RacionVisual getCurrent() {
        return getItem(getPosition());
    }

    @Override
    public void onBindViewHolder(RacionViewHolder holder, int position) {

        RacionVisual current = getItem(position);

        holder.bind(current);
        Button eliminar = holder.getButton();
        EditText editTextCantidad = holder.getEditText();


        if(intent.hasExtra("invisible")){
            Boolean invisible = false;
            invisible = intent.getBooleanExtra("invisible", false);
            if(invisible){
                eliminar.setVisibility(View.GONE);
                editTextCantidad.setFocusable(false);
                editTextCantidad.setFocusableInTouchMode(false);
            }
        }


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getAdapterPosition());
                return false;
            }


        });


        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int racionEliminar = holder.getAdapterPosition();
                if (listener != null) {
                    listener.onItemClick(racionEliminar);
                }
            }
        });



        editTextCantidad.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty()) {
                    // Actualiza el modelo con el nuevo texto
                    current.racion.setCantidad(Integer.parseInt(charSequence.toString()));
                }
                if (textChangedListener != null) {
                    textChangedListener.onTextChanged(position, charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });









    }


    static class RacionDiff extends DiffUtil.ItemCallback<RacionVisual> {

        @Override
        public boolean areItemsTheSame(@NonNull RacionVisual oldItem, @NonNull RacionVisual newItem) {
            //android.util.Log.d ( "NoteDiff" , "areItemsTheSame " + oldItem.getId() + " vs " + newItem.getId() + " " +  (oldItem.getId() == newItem.getId()));
            return oldItem.racion.getPlatoId() == newItem.racion.getPlatoId() && oldItem.racion.getPedidoId() == newItem.racion.getPedidoId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull RacionVisual oldItem, @NonNull RacionVisual newItem) {
            //android.util.Log.d ( "NoteDiff" , "areContentsTheSame " + oldItem.getTitle() + " vs " + newItem.getTitle() + " " + oldItem.getTitle().equals(newItem.getTitle()));
            // We are just worried about differences in visual representation, i.e. changes in the title
            return oldItem.racion.getCantidad() == newItem.racion.getCantidad() ;
        }
    }
}
