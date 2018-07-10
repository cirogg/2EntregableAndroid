package com.example.ciro.a2entregableandroid;

/**
 * Created by ciro_ on 8/6/2018.
 */

import com.example.ciro.a2entregableandroid.Model.POJO.ContenedorObras;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceObra {
    @GET("/bins/x858r/")
    Call<ContenedorObras> getObras(@Query("q") String obraABuscar);
}
