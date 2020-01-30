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
    private String usuario;
    private Lib lib;

    public Gasto() {
    }

    public Gasto(int id, String descripcion, Categoria categoria, GregorianCalendar fecha, double total, String usuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.fecha = fecha;
        this.total = total;
        this.usuario = usuario;
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

    public String getUsuario() {
        return usuario;
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
