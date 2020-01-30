package com.cinthyasophia.controldegastos.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cinthyasophia.controldegastos.Categoria;
import com.cinthyasophia.controldegastos.Gasto;
import com.cinthyasophia.controldegastos.Lista;
import com.cinthyasophia.controldegastos.R;
import com.cinthyasophia.controldegastos.Util.Lib;
import com.cinthyasophia.controldegastos.adapters.AdapterGastos;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FragmentGastos extends Fragment {
    private RecyclerView rvGastos;
    private TextView tvSumTotal;
    private ArrayList<Gasto> gastos;
    private ArrayList<Categoria> categorias;
    private FirebaseFirestore database;
    private AdapterGastos adapterGastos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle b = getArguments();
        String categoriaN;
        if(b!=null){
            gastos = (ArrayList<Gasto>) b.getSerializable("gastos");
            categorias = (ArrayList<Categoria>) b.getSerializable("categorias");

            if (b.containsKey("HOGAR")){
                categoriaN = b.getString("HOGAR");
            }else if(b.containsKey("OCIO")){
                categoriaN = b.getString("OCIO");

            }else if(b.containsKey("COMIDA")){
                categoriaN = b.getString("COMIDA");

            }else if(b.containsKey("COCHE")){
                categoriaN = b.getString("COCHE");

            }else if(b.containsKey("IMPREVISTOS")){
                categoriaN = b.getString("IMPREVISTOS");

            }else if(b.containsKey("OTROS")){
                categoriaN = b.getString("OTROS");

            }else{
                categoriaN = "Hogar";
            }

            //gastosCat = obtenerGastosCat(gastos,categorias,categoriaN);

        }
        return inflater.inflate(R.layout.fragment_gastos,container,false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvSumTotal = getView().findViewById(R.id.tvSumTotal);
        rvGastos = getView().findViewById(R.id.rvGastos);
        CollectionReference coleccion = database.collection("gastos");

        final Query query = coleccion.orderBy("id", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Gasto> options = new FirestoreRecyclerOptions.Builder<Gasto>()
                .setQuery(query,Gasto.class)
                .build();

        adapterGastos = new AdapterGastos(options);

        rvGastos.setAdapter(adapterGastos);
        rvGastos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

            }
        });

        //todo tengo que cargar los gastos en un arraylist para obtener la cantidad total

        //tvSumTotal.setText(String.valueOf(aux));

    }

    /*public ArrayList<Gasto> obtenerGastosCat(ArrayList<Gasto> gastos,ArrayList<Categoria> categorias, String cat){
        ArrayList<Gasto> gastosCat = new ArrayList<>();
        int catID = 0;

        *//*for (Categoria c: categorias) {
            if (c.getNombre().equalsIgnoreCase(cat)){
                catID = c.getId();
            }
        }*//*

        for (Gasto gasto:gastos) {
            if (gasto.getCategoria().getNombre().equalsIgnoreCase(cat)){
                gastosCat.add(gasto);
            }

        }
        return gastosCat;
    }*/
    /*public ArrayList<Gasto> obtenerGastos(SQLiteDatabase database){
        Lib lib = new Lib();
        ArrayList<Gasto> gastos = new ArrayList<>();
        Gasto gas;
        if (database!=null){
            String[] campos = new String[]{"Id","Descripcion","Categoria_Id","Fecha","Total"};
            int id;
            String descripcion;
            Categoria categoria;
            GregorianCalendar fecha;
            double total;
            Cursor c = database.query("Gasto",campos,null,null,null,null,null);
            if (c.moveToFirst()){
                do {
                    id = c.getInt(0);
                    descripcion = c.getString(1);
                    categoria = categorias.get(c.getInt(2)-1);
                    if (c.getString(3)==null){
                        fecha = new GregorianCalendar();
                    }else{
                        fecha = lib.getFecha(c.getString(3));
                    }
                    total = c.getDouble(4);
                    gas = new Gasto(id,descripcion,categoria,lib.getFecha(fecha),total);
                    gastos.add(gas);
                }while (c.moveToNext());
            }
        }
        return gastos;
    }
*/
}
