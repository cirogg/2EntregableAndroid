package com.example.ciro.a2entregableandroid.Controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.ciro.a2entregableandroid.Model.POJO.Artista;
import com.example.ciro.a2entregableandroid.Model.POJO.DataBase;
import com.example.ciro.a2entregableandroid.Model.POJO.DataBaseArtistas;
import com.example.ciro.a2entregableandroid.Model.POJO.Obra;
import com.example.ciro.a2entregableandroid.ResultListener;

import java.util.List;

/**
 * Created by ciro_ on 16/7/2018.
 */

public class ArtistaController {
    private Context context;
    public static boolean isArtistaPersistido = false;





    private boolean hayInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(context, "Hay internet", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(context, "NO hay internet", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public List<Artista> getArtistas(){
        DataBaseArtistas dataBase = new DataBaseArtistas(context);
        //persistirLista(getObras(),this);
        return dataBase.getAllArtistas();


    }



    public void addArtista(Artista artista){
        DataBaseArtistas dataBase = new DataBaseArtistas(context);
        dataBase.insertAll(artista);
    }

    public void persistirLista(List<Artista>listita){
        //listita.clear();
        //DataBase dataBase = new DataBase(context);
        for (Artista artista: listita) {
            addArtista(artista);
        }
        isArtistaPersistido = true;
    }
}
