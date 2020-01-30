package com.cinthyasophia.controldegastos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cinthyasophia.controldegastos.Lista;
import com.cinthyasophia.controldegastos.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class AdapterListas extends FirestoreRecyclerAdapter<Lista,AdapterListas.ListasViewHolder> implements View.OnClickListener {
    private View.OnClickListener listener;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterListas(@NonNull FirestoreRecyclerOptions<Lista> options) {
        super(options);
    }

    @NonNull
    @Override
    public ListasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item,parent,false);
        item.setOnClickListener(this);
        return new ListasViewHolder(item);
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
    }

    @Override
    protected void onBindViewHolder(@NonNull ListasViewHolder listasViewHolder, int i, @NonNull Lista lista) {
        listasViewHolder.tvNombreLista.setText(lista.getNombre());
        listasViewHolder.tvFechaLista.setText(lista.getFecha());
        listasViewHolder.tvCantidadGastos.setText(String.valueOf(lista.getGastos().size()));
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    public class ListasViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNombreLista;
        private TextView tvFechaLista;
        private TextView tvCantidadGastos;

        public ListasViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreLista = itemView.findViewById(R.id.tvNombreLista);
            tvFechaLista = itemView.findViewById(R.id.tvFechaLista);
            tvCantidadGastos = itemView.findViewById(R.id.tvCantidadGastos);
        }
    }
}
