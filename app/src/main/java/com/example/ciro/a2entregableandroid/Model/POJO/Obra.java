package com.example.ciro.a2entregableandroid.Model.POJO;

/**
 * Created by ciro_ on 8/6/2018.
 */

public class Obra {
    String name;
    String artistId;
    String image;

    public Obra() {
        //CONSTRUCTOR VACIO PARA FIREBASE
    }

    public Obra(String name, String artistId, String image) {
        this.name = name;
        this.artistId = artistId;
        this.image = image;
    }

    public String getTitulo() {
        return name;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Obra{" +
                "name='" + name + '\'' +
                ", artistId='" + artistId + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
