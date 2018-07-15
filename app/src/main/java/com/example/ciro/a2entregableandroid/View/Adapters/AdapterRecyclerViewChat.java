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
import com.example.ciro.a2entregableandroid.View.ChatActivity;
import com.example.ciro.a2entregableandroid.View.LoginActivity;

import java.util.List;

/**
 * Created by ciro_ on 13/7/2018.
 */

public class AdapterRecyclerViewChat extends RecyclerView.Adapter {

    List<Mensaje> listaDeMensajes;
    Context context;

    Boolean soyYo;

    public AdapterRecyclerViewChat(List<Mensaje> listaDeMensajes) {
        this.listaDeMensajes = listaDeMensajes;
    }

    public void setListaDeMensajes(List<Mensaje> listaDeMensajes) {
        this.listaDeMensajes = listaDeMensajes;
    }

    @NonNull

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        RecyclerView.ViewHolder holderDelMensaje= null;

        switch (viewType){
            case R.layout.celda_mensaje:{
                View celda = layoutInflater.inflate(R.layout.celda_mensaje, parent, false);
                ChatViewHolder chatViewHolder = new ChatViewHolder(celda);
                holderDelMensaje = (ChatViewHolder)chatViewHolder;
                break;
            }
            case R.layout.celda_mensaje_user:{
                View celda = layoutInflater.inflate(R.layout.celda_mensaje_user, parent, false);
                ChatViewHolder2 chatViewHolder2 = new ChatViewHolder2(celda);
                holderDelMensaje = (ChatViewHolder2)chatViewHolder2;
                break;
            }
        }

        return holderDelMensaje;
        //TODO REVISAR SI HAY QUE CASTEAR O NO.

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Mensaje mensaje = listaDeMensajes.get(position);

        if (holder instanceof ChatViewHolder){
            ChatViewHolder chatViewHolder = (ChatViewHolder) holder;
            chatViewHolder.asignarDatosALaCelda(mensaje);
        }

        if (holder instanceof ChatViewHolder2){
            ChatViewHolder2 chatViewHolder2 = (ChatViewHolder2) holder;
            chatViewHolder2.asignarDatosALaCelda2(mensaje);
        }


    }

    @Override
    public int getItemCount() {
        return listaDeMensajes.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(listaDeMensajes.get(position).getUserID().equals(LoginActivity.userID)){
            return R.layout.celda_mensaje_user;
        }else{
            return R.layout.celda_mensaje;
        }
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
            String autorDelMensaje = mensaje.getNombre().toString();
            String bodyDelMensaje = mensaje.getBody().toString();
            textViewAutor.setText(autorDelMensaje);
            textViewMensaje.setText(bodyDelMensaje);
        }
    }

    private class ChatViewHolder2 extends  RecyclerView.ViewHolder{
        TextView textViewAutor;
        TextView textViewMensaje;
        public ChatViewHolder2(View itemView) {
            super(itemView);
            textViewAutor = itemView.findViewById(R.id.textViewAutorDelMensaje);
            textViewMensaje = itemView.findViewById(R.id.textViewMensajeEnviado);
        }

        private void asignarDatosALaCelda2(Mensaje mensaje) {
            String autorDelMensaje = mensaje.getNombre().toString();
            String bodyDelMensaje = mensaje.getBody().toString();
            textViewAutor.setText(autorDelMensaje);
            textViewMensaje.setText(bodyDelMensaje);
        }
    }



}


