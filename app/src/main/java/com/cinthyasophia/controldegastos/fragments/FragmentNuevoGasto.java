package com.cinthyasophia.controldegastos.fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
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

public class FragmentNuevoGasto extends Fragment {
    private ControlGastosDB databaseG;
    private ArrayList<Categoria> categorias;
    private ArrayList<Gasto> gastos;
    private SQLiteDatabase database;
    private Lib lib;
    private RecyclerView rvGastosCat;
    private EditText etCategoria;
    private EditText etFecha;
    private EditText etCantidad;
    private EditText etDescripcion;
    private TextView tvMessage;
    private Button bGuardar;
    private Button bBuscar;
    private String fecha;
    private String descripcion;
    private double total;
    private String categoria;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        lib= new Lib();
        Bundle b = getArguments();

        if(b!=null){
            databaseG = (ControlGastosDB) b.getSerializable("DATABASE");
            gastos = (ArrayList<Gasto>) b.getSerializable("gastos");
            categorias = (ArrayList<Categoria>) b.getSerializable("categorias");
            database = databaseG.getOpenDataBase();
        }
        return inflater.inflate(R.layout.fragment_nuevo_gasto,container,false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<Gasto> gas;
        AdapterGastos adapterGastos;
        etDescripcion = getView().findViewById(R.id.etDescripcion);
        etCategoria = getView().findViewById(R.id.etCategoria);
        etFecha = getView().findViewById(R.id.eTFecha);
        etCantidad = getView().findViewById(R.id.etCantidad);
        rvGastosCat = getView().findViewById(R.id.rvGastosCat);
        bGuardar = getView().findViewById(R.id.bGuardar);
        bBuscar = getView().findViewById(R.id.bBuscar);

        bBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                categoria = categorias.get(categorias.size()-1).getNombre();
                for (Categoria cat : categorias) {
                    if (cat.getNombre().equalsIgnoreCase(String.valueOf(etCategoria.getText()))) {
                        categoria = cat.getNombre();
                    }
                }

            }
        });

        gas = ordenarGastos(gastos,categoria);
        adapterGastos = new AdapterGastos(gas);

        rvGastosCat.setAdapter(adapterGastos);
        rvGastosCat.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idCat = 0;
                fecha = String.valueOf(etFecha.getText());
                descripcion = String.valueOf(etDescripcion.getText());
                total = Double.parseDouble(String.valueOf(etCantidad.getText()));


                if (fecha == null || !lib.validarFecha(fecha)) {
                    fecha = lib.getFecha(new GregorianCalendar());
                }

                for (Categoria cat : categorias) {
                    if (cat.getNombre().equalsIgnoreCase(categoria)){
                        idCat = cat.getId();
                    }
                }

                if (database == null) {
                    Toast.makeText(getContext(), "HA FALLADO LA CONEXION ALAVERGA", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "HA SIDO CORRECTA LA CONEXION OLOVERGA", Toast.LENGTH_LONG).show();
                    ContentValues nuevoGasto = new ContentValues();
                    nuevoGasto.put("Descripcion",descripcion);
                    nuevoGasto.put("Categoria_Id",idCat);
                    nuevoGasto.put("Fecha",fecha);
                    nuevoGasto.put("Total",total);
                    database.insert("Gasto",null,nuevoGasto);
                    database.close();
                }

            }
        });


    }
    public ArrayList<Gasto> ordenarGastos(ArrayList<Gasto> gastos,String categoria){
        ArrayList<Gasto> g = new ArrayList<>();

        for (Gasto gasto:gastos){
            if(gasto.getCategoria().getNombre().equalsIgnoreCase(categoria)){
                g.add(gasto);
            }
        }
        return g;

    }

}
