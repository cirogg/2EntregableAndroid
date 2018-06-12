package com.example.ciro.a2entregableandroid.Model.POJO;

/**
 * Created by ciro_ on 8/6/2018.
 */

public class Obra {
    String name;

    public Obra(String titulo) {
        this.name = titulo;
    }

    public String getTitulo() {
        return name;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + name +
                '}';
    }
}
