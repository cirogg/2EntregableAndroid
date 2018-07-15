package com.example.ciro.a2entregableandroid.Model.POJO;

/**
 * Created by ciro_ on 13/7/2018.
 */

public class Mensaje {
    String userID;
    String body;
    String nombre;

    public Mensaje(String userID, String body, String nombre) {
        this.userID = userID;
        this.body = body;
        this.nombre = nombre;
    }

    public Mensaje(String body) {
        this.body = body;
    }

    public Mensaje() {
    }

    public String getUserID() {
        return userID;
    }

    public String getBody() {
        return body;
    }

    public String getNombre() {
        return nombre;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
