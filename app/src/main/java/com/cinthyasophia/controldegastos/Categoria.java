package com.cinthyasophia.controldegastos;


import java.io.Serializable;

public class Categoria implements Serializable {
    private int id;
    private String nombre;
    private String foto;

    public Categoria(int id, String nombre, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
    }

    public Categoria() {
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFoto() {
        return foto;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
