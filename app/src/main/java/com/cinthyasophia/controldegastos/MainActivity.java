package com.cinthyasophia.controldegastos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cinthyasophia.controldegastos.Util.Lib;
import com.cinthyasophia.controldegastos.fragments.FragmentNuevoGasto;
import com.cinthyasophia.controldegastos.fragments.FragmentPrincipal;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity{
    private static final String DB_NAME = "controlgastos";
    private static final int DB_VERSION = 1;
    private ControlGastosDB databaseGestor;
    private SQLiteDatabase database;
    private ArrayList<Categoria> categorias;
    private ArrayList<Gasto> gastos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseGestor = new ControlGastosDB(this,DB_NAME,null,DB_VERSION);
        database = databaseGestor.getOpenDataBase();

        if (database == null){
            Toast.makeText(this,"HA FALLADO LA CONEXION ALAVERGA",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"HA SIDO CORRECTA LA CONEXION OLOVERGA",Toast.LENGTH_LONG).show();
            categorias = obtenerCategorias(database);
            gastos = obtenerGastos(database);
            database.close();
        }
        Bundle b = new Bundle();
        b.putSerializable("categorias",categorias);
        b.putSerializable("gastos", gastos);

        Fragment fPrincipal = new FragmentPrincipal();
        fPrincipal.setArguments(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_principal,fPrincipal).commit();


    }


    @Override
    public void onBackPressed() {

        if(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStackImmediate();
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nuevo_gasto,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iNuevoGasto:
                Fragment fNuevoGasto = new FragmentNuevoGasto();
                Bundle b = new Bundle();
                b.putSerializable("categorias",categorias);
                b.putSerializable("gastos",gastos);
                b.putSerializable("DATABASE",databaseGestor);
                fNuevoGasto.setArguments(b);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_principal,fNuevoGasto).addToBackStack(null).commit();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public ArrayList<Gasto> obtenerGastos(SQLiteDatabase database){
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
    public ArrayList<Categoria> obtenerCategorias(SQLiteDatabase database){
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
    }

}
