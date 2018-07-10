package com.example.ciro.a2entregableandroid.Model.POJO;

import java.util.List;

/**
 * Created by ciro_ on 8/6/2018.
 */

public class ContenedorObras {
    private List<Obra> paints;

    public ContenedorObras(List<Obra> listaDeObras) {
        this.paints = listaDeObras;
    }

    public List<Obra> getListaDeObras() {
        return paints;
    }
}
