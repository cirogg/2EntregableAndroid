package com.example.ciro.a2entregableandroid.Model.POJO;

/**
 * Created by ciro_ on 10/7/2018.
 */

public class Usuario {
    String nombre;
    String userID;

    public Usuario(String nombre, String userID) {
        this.nombre = nombre;
        this.userID = userID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUserID() {
        return userID;
    }
}


