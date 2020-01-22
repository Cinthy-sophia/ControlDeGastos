package com.cinthyasophia.controldegastos.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cinthyasophia.controldegastos.Categoria;
import com.cinthyasophia.controldegastos.ControlGastosDB;
import com.cinthyasophia.controldegastos.Gasto;
import com.cinthyasophia.controldegastos.R;
import com.cinthyasophia.controldegastos.adapters.AdapterCategoria;

import java.util.ArrayList;

public class FragmentPrincipal extends Fragment {
    private ArrayList<Categoria> categorias;
    private ArrayList<Gasto> gastos;
    private RecyclerView rvCategorias;
    private AdapterCategoria adapterCategoria;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().setTitle("");
        categorias = (ArrayList<Categoria>) getArguments().getSerializable("categorias");
        gastos = (ArrayList<Gasto>) getArguments().getSerializable("gastos");

        return inflater.inflate(R.layout.fragment_principal,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvCategorias = getView().findViewById(R.id.rvCategorias);

        adapterCategoria = new AdapterCategoria(categorias,getContext());
        rvCategorias.setAdapter(adapterCategoria);
        rvCategorias.setLayoutManager( new GridLayoutManager(getContext(),3));

        adapterCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                String nombre = categorias.get(rvCategorias.getChildAdapterPosition(v)).getNombre().toUpperCase();
                Fragment fGastos = new FragmentGastos();
                b.putSerializable("gastos",gastos);
                b.putSerializable("categorias",categorias);

                switch (nombre){
                    case "HOGAR":
                        b.putString("HOGAR","Hogar");
                        fGastos.setArguments(b);
                        getActivity().setTitle("Hogar");
                        break;
                    case "OCIO":
                        b.putString("OCIO","Ocio");
                        fGastos.setArguments(b);
                        getActivity().setTitle("Ocio");
                        break;
                    case "COMIDA":
                        b.putString("COMIDA","Comida");
                        fGastos.setArguments(b);
                        getActivity().setTitle("Comida");
                        break;
                    case "COCHE":
                        b.putString("COCHE","Coche");
                        fGastos.setArguments(b);
                        getActivity().setTitle("Coche");
                        break;
                    case "IMPREVISTOS":
                        b.putString("IMPREVISTOS","Imprevistos");
                        fGastos.setArguments(b);
                        getActivity().setTitle("Imprevistos");
                        break;
                    case "OTROS":
                        b.putString("OTROS","Otros");
                        fGastos.setArguments(b);
                        getActivity().setTitle("Otros");
                        break;
                    default:
                        break;
                }
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_principal,fGastos).addToBackStack("fragment_principal").commit();

            }
        });


    }
    /*public ArrayList<Categoria> obtenerCategorias(SQLiteDatabase database){
        ArrayList<Categoria> categorias = new ArrayList<>();
        Categoria cat;
        if (database!=null){
            String[] campos = new String[]{"Id","Nombre","Logo"};
            Cursor c = database.query("Categoria",campos,null,null,null,null,null);
            if(c.moveToFirst()) {
                do {
                    int id = c.getInt(0);
                    String nombre = c.getString(1);
                    String foto = c.getString(2);
                    if (foto == null){
                        foto = "drawable/c_"+nombre.toLowerCase();
                    }
                    cat= new Categoria(id,nombre,foto);
                    categorias.add(cat);
                } while(c.moveToNext());
            }
        }
        return categorias;
    }*/

}
