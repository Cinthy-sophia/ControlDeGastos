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
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class AdapterCategoria extends FirestoreRecyclerAdapter<Categoria, AdapterCategoria.CategoriaViewHolder> implements View.OnClickListener{

    private Context context;
    private View.OnClickListener listener;

    public AdapterCategoria(FirestoreRecyclerOptions<Categoria> options, Context context) {
        super(options);
        this.context=context;

    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoria_item,parent,false);
        item.setOnClickListener(this);
        return new CategoriaViewHolder(item);
    }


    @Override
    protected void onBindViewHolder(@NonNull CategoriaViewHolder categoriaViewHolder, int i, @NonNull Categoria categoria) {
        categoriaViewHolder.tvNombreCategoria.setText(categoria.getNombre());
        if (categoria.getFoto() == null){
            categoria.setFoto("drawable/c_"+categoria.getNombre().toLowerCase());
        }
        int iResource= context.getResources().getIdentifier(categoria.getFoto(),null, context.getPackageName());
        categoriaViewHolder.ivImagenCategoria.setImageResource(iResource);
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

    }
}
