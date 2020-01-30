package com.cinthyasophia.controldegastos.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cinthyasophia.controldegastos.Categoria;
import com.cinthyasophia.controldegastos.Gasto;
import com.cinthyasophia.controldegastos.Lista;
import com.cinthyasophia.controldegastos.R;
import com.cinthyasophia.controldegastos.adapters.AdapterListas;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FragmentPrincipal extends Fragment {
    private FirebaseFirestore database;
    private ArrayList<Lista> listas;
    private ArrayList<Gasto> gastos;
    private String usuario;
    private RecyclerView rvListas;
    private AdapterListas adapterListas;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().setTitle("");
        usuario = getArguments().getString("usuario");

        return inflater.inflate(R.layout.fragment_principal,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvListas = getView().findViewById(R.id.rvListas);
        CollectionReference coleccion = database.collection("listas");

        final Query query = coleccion.orderBy("id", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Lista> options = new FirestoreRecyclerOptions.Builder<Lista>()
                .setQuery(query,Lista.class)
                .build();

        adapterListas = new AdapterListas(options);
        rvListas.setAdapter(adapterListas);
        rvListas.setLayoutManager( new GridLayoutManager(getContext(),3));

        adapterListas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                String nombre = listas.get(rvListas.getChildAdapterPosition(v)).getNombre().toUpperCase();

                Fragment fGastos = new FragmentGastos();
                b.putString("usuario",usuario);

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
                getActivity().setTitle(nombre);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_principal,fGastos).addToBackStack("fragment_principal").commit();

            }
        });


    }

}
