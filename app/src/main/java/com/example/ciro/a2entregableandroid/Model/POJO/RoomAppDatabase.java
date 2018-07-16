package com.example.ciro.a2entregableandroid.Model.POJO;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.ciro.a2entregableandroid.Model.DAO.DAORoomArtista;
import com.example.ciro.a2entregableandroid.Model.DAO.DAORoomObra;

/**
 * Created by ciro_ on 16/7/2018.
 */
@Database(entities = {Obra.class,Artista.class}, version = 1)
public abstract class RoomAppDatabase extends RoomDatabase {
    public abstract DAORoomObra obraDAO();
    public abstract DAORoomArtista artistaDAO();
}

