package com.example.ciro.a2entregableandroid.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ciro.a2entregableandroid.Model.DAO.DAOObraRetrofit;
import com.example.ciro.a2entregableandroid.Model.POJO.Obra;
import com.example.ciro.a2entregableandroid.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ciro_ on 12/6/2018.
 */

public class AdapterRecyclerViewObras extends RecyclerView.Adapter {

    List<Obra> listaDeObrasDelRecyclerView;
    ComunicadoraAdapterRWalFragment comunicadoraAdapterRWalFragment;

    public AdapterRecyclerViewObras(List<Obra> listaDeObrasDelRecyclerView, ComunicadoraAdapterRWalFragment comunicadoraAdapterRWalFragment) {
        this.listaDeObrasDelRecyclerView = listaDeObrasDelRecyclerView;
        this.comunicadoraAdapterRWalFragment = comunicadoraAdapterRWalFragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View celda = layoutInflater.inflate(R.layout.celda_obra, parent, false);
        ViewHolderObras viewHolderObras = new ViewHolderObras(celda);

        return viewHolderObras;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Obra obra = listaDeObrasDelRecyclerView.get(position);
        ViewHolderObras viewHolderObras = (ViewHolderObras) holder;
        viewHolderObras.asignarDatosALaCelda(obra);

    }

    @Override
    public int getItemCount() {
        return listaDeObrasDelRecyclerView.size();
    }

    private class ViewHolderObras extends RecyclerView.ViewHolder{

        TextView textViewTitulo;

        public ViewHolderObras(View itemView) {
            super(itemView);
            textViewTitulo = itemView.findViewById(R.id.textViewTitulo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer posicionSeleccionada = getAdapterPosition();

                    comunicadoraAdapterRWalFragment.informarObraSeleccionadaDelRW(posicionSeleccionada);
                }
            });
        }

        private void asignarDatosALaCelda(Obra obra){
            String tituloDeLaObra = obra.getTitulo().toString();
            textViewTitulo.setText(tituloDeLaObra);
        }
    }

    public interface ComunicadoraAdapterRWalFragment{
        public void informarObraSeleccionadaDelRW(Integer pos); //VER COMO MANDAR
    }
}
