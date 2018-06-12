package com.example.ciro.a2entregableandroid.Model.DAO;

import com.example.ciro.a2entregableandroid.Model.POJO.Obra;
import com.example.ciro.a2entregableandroid.ResultListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ciro_ on 8/6/2018.
 */

public class DAOObraInternet {



        public void obtenerListaDeObrasDeInternet(ResultListener<List<Obra>> escuchadorDelControlador){

            List<Obra>listaDeObras = new ArrayList<>();

            listaDeObras.add(new Obra("Obra 1"));
            listaDeObras.add(new Obra("Obra 2"));
            listaDeObras.add(new Obra("Obra 3"));
            listaDeObras.add(new Obra("Obra 4"));



            escuchadorDelControlador.finish(listaDeObras);
        }



}
