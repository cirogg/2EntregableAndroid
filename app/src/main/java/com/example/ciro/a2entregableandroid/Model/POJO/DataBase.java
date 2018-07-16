package com.example.ciro.a2entregableandroid.Model.POJO;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

/**
 * Created by ciro_ on 16/7/2018.
 */

public class DataBase {
    private RoomAppDatabase db;


    public DataBase(Context context) {
        db = Room.databaseBuilder(context, RoomAppDatabase.class, "database-name").allowMainThreadQueries().build();
    }

    public List<Obra> getAllObras(){
        return db.obraDAO().getAllObras();
    }
    public void insertAll(Obra... obra){
        db.obraDAO().insertAll(obra);
    }

    public void deleteAllObras(){
        db.obraDAO().delete();
    }





}
