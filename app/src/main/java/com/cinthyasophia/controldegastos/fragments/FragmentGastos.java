package com.cinthyasophia.controldegastos.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cinthyasophia.controldegastos.Categoria;
import com.cinthyasophia.controldegastos.ControlGastosDB;
import com.cinthyasophia.controldegastos.Gasto;
import com.cinthyasophia.controldegastos.R;
import com.cinthyasophia.controldegastos.Util.Lib;
import com.cinthyasophia.controldegastos.adapters.AdapterGastos;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FragmentGastos extends Fragment {
    private RecyclerView rvGastos;
    private TextView tvSumTotal;
    private ArrayList<Gasto> gastos;
    private ArrayList<Gasto> gastosCat;
    private ArrayList<Categoria> categorias;
    private ControlGastosDB databaseG;
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

            gastosCat = obtenerGastosCat(gastos,categorias,categoriaN);

        }
        return inflater.inflate(R.layout.fragment_gastos,container,false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvSumTotal = getView().findViewById(R.id.tvSumTotal);
        rvGastos = getView().findViewById(R.id.rvGastos);

        adapterGastos = new AdapterGastos(gastosCat);
        adapterGastos.swap(gastosCat);

        rvGastos.setAdapter(adapterGastos);
        rvGastos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        double aux = 0;
        for (Gasto g: gastosCat) {
            aux += g.getTotal();
        }
        tvSumTotal.setText(String.valueOf(aux));

    }

    public ArrayList<Gasto> obtenerGastosCat(ArrayList<Gasto> gastos,ArrayList<Categoria> categorias, String cat){
        ArrayList<Gasto> gastosCat = new ArrayList<>();
        int catID = 0;

        /*for (Categoria c: categorias) {
            if (c.getNombre().equalsIgnoreCase(cat)){
                catID = c.getId();
            }
        }*/

        for (Gasto gasto:gastos) {
            if (gasto.getCategoria().getNombre().equalsIgnoreCase(cat)){
                gastosCat.add(gasto);
            }

        }
        return gastosCat;
    }
    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public ArrayList<Gasto> getGastos() {
        return gastos;
    }
}
