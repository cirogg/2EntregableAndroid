package com.example.ciro.a2entregableandroid.Model.POJO;

/**
 * Created by ciro_ on 25/6/2018.
 */

public class Artista {

    String artistId;
    String name;
    String nationality;
    String Influenced_by;

    public Artista() {
        //CONSTRUCTOR VACIO PARA Firebase
    }

    public Artista(String artistId, String name, String nationality, String Influenced_by) {
        this.artistId = artistId;
        this.name = name;
        this.nationality = nationality;
        this.Influenced_by = Influenced_by;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getInfluenced_by() {
        return Influenced_by;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "artistId=" + artistId +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", influenced_by='" + Influenced_by + '\'' +
                '}';
    }
}
