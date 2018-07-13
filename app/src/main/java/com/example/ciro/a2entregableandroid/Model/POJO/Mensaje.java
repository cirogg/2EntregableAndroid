package com.example.ciro.a2entregableandroid.Model.POJO;

/**
 * Created by ciro_ on 13/7/2018.
 */

public class Mensaje {
    String userID;
    String body;

    public Mensaje(String userID, String body) {
        this.userID = userID;
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

    public void setBody(String body) {
        this.body = body;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
