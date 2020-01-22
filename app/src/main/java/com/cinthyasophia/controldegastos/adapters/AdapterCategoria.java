package com.cinthyasophia.controldegastos.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cinthyasophia.controldegastos.Categoria;
import com.cinthyasophia.controldegastos.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class AdapterCategoria extends RecyclerView.Adapter<AdapterCategoria.CategoriaViewHolder> implements View.OnClickListener{
    private ArrayList<Categoria> datos;
    private Context context;
    private View.OnClickListener listener;

    public AdapterCategoria(ArrayList<Categoria> datos,Context context) {
        this.datos = datos;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoria_item,parent,false);
        item.setOnClickListener(this);
        return new CategoriaViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        Categoria categoria = datos.get(position);
        holder.bindCategoria(categoria,context);

    }

    @Override
    public int getItemCount() {
        return datos.size();
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


    public class CategoriaViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNombreCategoria;
        private ImageView ivImagenCategoria;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreCategoria = itemView.findViewById(R.id.tvNombreCategoria);
            ivImagenCategoria = itemView.findViewById(R.id.ivImagenCategoria);

        }

        public void bindCategoria(Categoria categoria, Context context) {
            tvNombreCategoria.setText(categoria.getNombre());
            int iResource= context.getResources().getIdentifier(categoria.getFoto(),null, context.getPackageName());
            ivImagenCategoria.setImageResource(iResource);

        }
    }
}
