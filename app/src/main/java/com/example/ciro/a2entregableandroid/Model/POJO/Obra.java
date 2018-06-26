package com.example.ciro.a2entregableandroid.Model.POJO;

/**
 * Created by ciro_ on 8/6/2018.
 */

public class Obra {
    String name;
    String artistId;

    public Obra (){
        //CONSTRUCTOR VACIO PARA FIREBASE
    }

    public Obra(String name, String artistId) {
        this.name = name;
        this.artistId = artistId;
    }

    public String getTitulo() {
        return name;
    }

    public String getArtistId() {
        return artistId;
    }

    @Override
    public String toString() {
        return "Obra{" +
                "name='" + name + '\'' +
                ", artistId='" + artistId + '\'' +
                '}';
    }
}
