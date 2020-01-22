package com.cinthyasophia.controldegastos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cinthyasophia.controldegastos.Gasto;
import com.cinthyasophia.controldegastos.R;

import java.util.ArrayList;

public class AdapterGastos extends RecyclerView.Adapter<AdapterGastos.GastosViewHolder>  {
    private ArrayList<Gasto> datos;
    public AdapterGastos(ArrayList<Gasto> datos) {
        this.datos = datos;
    }

    @NonNull
    @Override
    public GastosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.gasto_item,parent,false);
        return new GastosViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull GastosViewHolder holder, int position) {
        Gasto gasto = datos.get(position);
        holder.bindGasto(gasto);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void swap(ArrayList<Gasto> datos) {
        this.datos = datos;
        notifyDataSetChanged();
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

        public void bindGasto(Gasto gasto) {

            tvIdGasto.setText(String.valueOf(gasto.getId()));
            tvDescripcionGasto.setText(gasto.getDescripcion());
            tvFechaGasto.setText(gasto.getFecha());
            tvTotalGasto.setText(String.valueOf(gasto.getTotal()));
        }
    }
}
