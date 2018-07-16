package com.example.ciro.a2entregableandroid.Model.POJO;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

/**
 * Created by ciro_ on 16/7/2018.
 */

public class DataBaseArtistas {

    private RoomAppDatabase db;

    public DataBaseArtistas(Context context) {
        this.db = Room.databaseBuilder(context, RoomAppDatabase.class, "database-name").allowMainThreadQueries().build();
    }

    //DAO Artist
    public List<Artista> getAllArtistas(){
        return db.artistaDAO().getAllArtistas();
    }

    public void insertAll(Artista... artistas){
        db.artistaDAO().insertAll(artistas);
    }

}
