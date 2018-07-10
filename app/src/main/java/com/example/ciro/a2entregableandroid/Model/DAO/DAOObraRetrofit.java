package com.example.ciro.a2entregableandroid.Model.DAO;

import com.example.ciro.a2entregableandroid.Model.POJO.ContenedorObras;
import com.example.ciro.a2entregableandroid.Model.POJO.Obra;
import com.example.ciro.a2entregableandroid.ResultListener;
import com.example.ciro.a2entregableandroid.ServiceObra;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ciro_ on 8/6/2018.
 */

public class DAOObraRetrofit {

    private String baseURL;
    private Retrofit retrofit;
    private ServiceObra serviceObra;

    public DAOObraRetrofit() {
        baseURL = "https://api.myjson.com/bins/x858r/";
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceObra = retrofit.create(ServiceObra.class);
    }

    public void obtenerObrasDeInternet(final ResultListener<List<Obra>> escuchadorDelControlador) {

        Call<ContenedorObras> retrofitListener = serviceObra.getObras("ABCD");
        retrofitListener.enqueue(new Callback<ContenedorObras>() {
            @Override
            public void onResponse(Call<ContenedorObras> call, Response<ContenedorObras> response) {
                ContenedorObras contenedorObras = response.body();
                escuchadorDelControlador.finish(contenedorObras.getListaDeObras());
            }

            @Override
            public void onFailure(Call<ContenedorObras> call, Throwable t) {
                escuchadorDelControlador.finish(new ArrayList<Obra>());
            }
        });

    }

}
