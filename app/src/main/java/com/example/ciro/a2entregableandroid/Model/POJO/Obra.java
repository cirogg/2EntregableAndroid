package com.example.ciro.a2entregableandroid.Model.POJO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Objects;

/**
 * Created by ciro_ on 8/6/2018.
 */
@Entity
public class Obra {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    int id;
    public String name;
    public String artistId;
    public String image;

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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Obra{" +
                "name='" + name + '\'' +
                ", artistId='" + artistId + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, artistId,image);
    }
}
