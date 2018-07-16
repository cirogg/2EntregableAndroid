package com.example.ciro.a2entregableandroid.Controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.ciro.a2entregableandroid.Model.DAO.DAOObraRetrofit;
import com.example.ciro.a2entregableandroid.Model.POJO.DataBase;
import com.example.ciro.a2entregableandroid.Model.POJO.Obra;
import com.example.ciro.a2entregableandroid.ResultListener;
import com.example.ciro.a2entregableandroid.View.FragmentFeed;

import java.util.List;

/**
 * Created by ciro_ on 8/6/2018.
 */

public class ObraController {

    private Context context;
    public static boolean isPersistida = false;

    public ObraController(Context context) {
        this.context = context;
    }

    public void obtenerProductos(final ResultListener<List<Obra>> escuchadorDeLaVista) {
        ResultListener<List<Obra>> escuchadorDelControlador = new ResultListener<List<Obra>>() {
            @Override
            public void finish(List<Obra> resultado) {
                escuchadorDeLaVista.finish(resultado);
                if (isPersistida == false) {
                    persistirLista(resultado);
                }
            }
        };

        List<Obra> obras = null;
        if (hayInternet()) {
            DAOObraRetrofit daoObraRetrofit = new DAOObraRetrofit();
            daoObraRetrofit.obtenerObrasDeInternet(escuchadorDelControlador);


        }
        if (hayInternet() == false){

            escuchadorDeLaVista.finish(getObras());

        }


    }

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

    public List<Obra>getObras(){
        DataBase dataBase = new DataBase(context);
        //persistirLista(getObras(),this);
        return dataBase.getAllObras();


    }



    public void addObra(Obra obra){
        DataBase dataBase = new DataBase(context);
        dataBase.insertAll(obra);
    }

    public void deleteAll(){
        DataBase dataBase = new DataBase(context);
        dataBase.deleteAllObras();
    }

    public void persistirLista(List<Obra>listita){
        //listita.clear();
        //DataBase dataBase = new DataBase(context);
        for (Obra obra : listita) {
            addObra(obra);
        }
        isPersistida = true;
    }
}
