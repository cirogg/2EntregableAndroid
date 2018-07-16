package com.example.ciro.a2entregableandroid.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.ciro.a2entregableandroid.Model.POJO.Artista;
import com.example.ciro.a2entregableandroid.Model.POJO.Obra;

import java.util.List;

/**
 * Created by ciro_ on 16/7/2018.
 */
@Dao
public interface DAORoomArtista {
    @Query("SELECT * FROM Artista")
    List<Artista> getAllArtistas();
    @Insert
    void insertAll(Artista... artista);
}


