package com.example.ciro.a2entregableandroid.Controller;

import com.example.ciro.a2entregableandroid.Model.DAO.DAOObraRetrofit;
import com.example.ciro.a2entregableandroid.Model.POJO.Obra;
import com.example.ciro.a2entregableandroid.ResultListener;

import java.util.List;

/**
 * Created by ciro_ on 8/6/2018.
 */

public class ObraController {



    public void obtenerProductos(final ResultListener<List<Obra>> escuchadorDeLaVista){
        ResultListener<List<Obra>> escuchadorDelControlador = new ResultListener<List<Obra>>() {
            @Override
            public void finish(List<Obra> resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        };

        List<Obra> obras = null;
        if (hayInternet()){
            DAOObraRetrofit daoObraRetrofit = new DAOObraRetrofit();
            daoObraRetrofit.obtenerObrasDeInternet(escuchadorDelControlador);
        }else{

        }


    }

    public Boolean hayInternet(){
        return true;
    }

}
