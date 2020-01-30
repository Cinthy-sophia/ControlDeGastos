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
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity{
    private static final int SIGN_IN_REQUEST_CODE = 1001;
    private FirebaseFirestore db;
    private CollectionReference coleccion;
    private ArrayList<Categoria> categorias;
    private ArrayList<Gasto> gastos;
    private String usuarioN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Iniciamos Activity para Login/Registro
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    SIGN_IN_REQUEST_CODE
            );
        } else {
            // El usuario ya se ha autenticado.
            Toast.makeText(this,
                    "Bienvenido " + FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getDisplayName(),
                    Toast.LENGTH_LONG)
                    .show();

        }

        db = FirebaseFirestore.getInstance();
        usuarioN = FirebaseAuth.getInstance()
                .getCurrentUser()
                .getDisplayName();

        categorias = new ArrayList<>();
        gastos = new ArrayList<>();
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
                fNuevoGasto.setArguments(b);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_principal,fNuevoGasto).addToBackStack(null).commit();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public ArrayList<Lista> obtenerLista(FirebaseFirestore database){
        ArrayList<Lista> listas = new ArrayList<>();

        return listas;
    }

}
