package com.example.ciro.a2entregableandroid.View;


import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ciro.a2entregableandroid.Controller.ObraController;
import com.example.ciro.a2entregableandroid.Model.POJO.Obra;
import com.example.ciro.a2entregableandroid.R;
import com.example.ciro.a2entregableandroid.ResultListener;
import com.example.ciro.a2entregableandroid.View.Adapters.AdapterRecyclerViewObras;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFeed extends Fragment implements AdapterRecyclerViewObras.ComunicadoraAdapterRWalFragment {

    public static List<Obra> listaDeObras;
    RecyclerView recyclerViewDeObras;
    ComunicadorFragmentAActivity comunicadorFragmentAActivity;
    AdapterRecyclerViewObras adapterRecyclerViewObras;


    public static List<Obra> listaDeObrasPublica;

    public FragmentFeed() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.comunicadorFragmentAActivity = (ComunicadorFragmentAActivity) context;
    }

    @Override
    public void onStart() {
        super.onStart();

        ObraController obraController = new ObraController(getContext());
        //obraController.deleteAll();
     /*   listaDeObras = obraController.getObras();
        adapterRecyclerViewObras.setListaDeObrasDelRecyclerView(listaDeObras);
        adapterRecyclerViewObras.notifyDataSetChanged();*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_feed, container, false);

        //CREADO NOCHE
        listaDeObras = new ArrayList<>();
        adapterRecyclerViewObras =  new AdapterRecyclerViewObras(listaDeObras, FragmentFeed.this);

        recyclerViewDeObras = view.findViewById(R.id.recyclerViewFeedObras);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewDeObras.setLayoutManager(layoutManager);

        cargarProductos();

        return view;
    }

    public void cargarProductos() {
        final ObraController obraController = new ObraController(getContext());
        obraController.obtenerProductos(new ResultListener<List<Obra>>() {
            @Override
            public void finish(List<Obra> resultado) {
                listaDeObras = resultado;
                adapterRecyclerViewObras = new AdapterRecyclerViewObras(listaDeObras, FragmentFeed.this);
                recyclerViewDeObras.setAdapter(adapterRecyclerViewObras);
                //persistirLista(resultado,obraController);
              /* for (Obra obra : resultado) {
                    obraController.addObra(obra);
                }*/
                adapterRecyclerViewObras.setListaDeObrasDelRecyclerView(listaDeObras);
                //obraController.getObras();
            }
        });


    }


    @Override
    public void informarObraSeleccionadaDelRW(Integer pos) {
        comunicadorFragmentAActivity.clickearonEnLaObra(pos);
    }

    public interface ComunicadorFragmentAActivity {
        public void clickearonEnLaObra(Integer pos);
    }




}
