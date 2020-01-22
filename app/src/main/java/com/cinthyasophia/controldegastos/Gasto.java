package com.cinthyasophia.controldegastos;

import com.cinthyasophia.controldegastos.Util.Lib;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Gasto implements Serializable {
    private int id;
    private String descripcion;
    private Categoria categoria;
    private GregorianCalendar fecha;
    private double total;
    private Lib lib;

    public Gasto() {
    }


    public Gasto(int id, String descripcion, Categoria categoria, String fecha, double total) {
        this.lib = new Lib();
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = lib.getFecha(fecha);
        this.categoria = categoria;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public double getTotal() {
        return total;
    }

    public String getFecha() {
        return lib.getFecha(fecha);
    }


    @Override
    public String toString() {
        return "Gasto{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", categoria=" + categoria +
                ", total=" + total +
                '}';
    }
}
