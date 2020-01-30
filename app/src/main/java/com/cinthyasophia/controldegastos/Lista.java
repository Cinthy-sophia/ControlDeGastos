package com.cinthyasophia.controldegastos;

import com.cinthyasophia.controldegastos.Util.Lib;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Lista {
    private String nombre;
    private GregorianCalendar fecha;
    private ArrayList<Gasto> gastos;
    private Lib lib;

    public Lista(String nombre, String fecha, ArrayList<Gasto> gastos) {
        this.nombre = nombre;
        this.fecha = lib.getFecha(fecha);
        this.gastos = gastos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return lib.getFecha(fecha);
    }

    public ArrayList<Gasto> getGastos() {
        return gastos;
    }
}
