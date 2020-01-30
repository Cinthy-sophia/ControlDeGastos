package com.cinthyasophia.controldegastos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cinthyasophia.controldegastos.Gasto;
import com.cinthyasophia.controldegastos.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class AdapterGastos extends FirestoreRecyclerAdapter<Gasto, AdapterGastos.GastosViewHolder> {

    public AdapterGastos(@NonNull FirestoreRecyclerOptions<Gasto> options) {
        super(options);

    }

    @NonNull
    @Override
    public GastosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.gasto_item,parent,false);
        return new GastosViewHolder(item);
    }

    @Override
    protected void onBindViewHolder(@NonNull GastosViewHolder gastosViewHolder, int i, @NonNull Gasto gasto) {
        gastosViewHolder.tvIdGasto.setText(String.valueOf(gasto.getId()));
        gastosViewHolder.tvDescripcionGasto.setText(gasto.getDescripcion());
        gastosViewHolder.tvFechaGasto.setText(gasto.getFecha());
        gastosViewHolder.tvTotalGasto.setText(String.valueOf(gasto.getTotal()));
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
    }


    public class GastosViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIdGasto;
        private TextView tvDescripcionGasto;
        private TextView tvFechaGasto;
        private TextView tvTotalGasto;

        public GastosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdGasto = itemView.findViewById(R.id.tvIdGasto);
            tvDescripcionGasto = itemView.findViewById(R.id.tvDescripcionGasto);
            tvFechaGasto = itemView.findViewById(R.id.tvFechaGasto);
            tvTotalGasto = itemView.findViewById(R.id.tvTotalGasto);
        }

    }
}
