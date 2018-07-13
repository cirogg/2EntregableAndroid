package com.example.ciro.a2entregableandroid.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ciro.a2entregableandroid.Model.POJO.Mensaje;
import com.example.ciro.a2entregableandroid.Model.POJO.Obra;
import com.example.ciro.a2entregableandroid.R;

import java.util.List;

/**
 * Created by ciro_ on 13/7/2018.
 */

public class AdapterRecyclerViewChat extends RecyclerView.Adapter {

    List<Mensaje> listaDeMensajes;
    Context context;

    public AdapterRecyclerViewChat(List<Mensaje> listaDeMensajes) {
        this.listaDeMensajes = listaDeMensajes;
    }

    @NonNull

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View celda = layoutInflater.inflate(R.layout.celda_mensaje, parent, false);
        ChatViewHolder chatViewHolder = new ChatViewHolder(celda);

        return chatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Mensaje mensaje = listaDeMensajes.get(position);
        ChatViewHolder chatViewHolder = (ChatViewHolder) holder;
        chatViewHolder.asignarDatosALaCelda(mensaje);
    }

    @Override
    public int getItemCount() {
        return listaDeMensajes.size();
    }

    private class ChatViewHolder extends RecyclerView.ViewHolder{

        TextView textViewAutor;
        TextView textViewMensaje;

        public ChatViewHolder(View itemView) {
            super(itemView);
            textViewAutor = itemView.findViewById(R.id.textViewAutorDelMensaje);
            textViewMensaje = itemView.findViewById(R.id.textViewMensajeEnviado);
        }
        private void asignarDatosALaCelda(Mensaje mensaje) {
            String autorDelMensaje = mensaje.getUserID().toString();
            String bodyDelMensaje = mensaje.getBody().toString();
            textViewAutor.setText(autorDelMensaje);
            textViewMensaje.setText(bodyDelMensaje);
        }
    }


}


