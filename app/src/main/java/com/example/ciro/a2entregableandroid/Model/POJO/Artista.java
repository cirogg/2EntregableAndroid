package com.example.ciro.a2entregableandroid.Model.POJO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Objects;

/**
 * Created by ciro_ on 25/6/2018.
 */
@Entity
public class Artista {
    @PrimaryKey
    @NonNull
    private String artistId;
    private String name;
    private String nationality;
    private String Influenced_by;

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

    public void setArtistId(@NonNull String artistId) {
        this.artistId = artistId;
    }

    public void setInfluenced_by(String influenced_by) {
        Influenced_by = influenced_by;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Nombre del Artista " + name + "\n" +
                "Nacionalidad: " + nationality + "\n" +
                "Influencias: " + Influenced_by;
    }


}
