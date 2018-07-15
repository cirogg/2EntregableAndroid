package com.example.ciro.a2entregableandroid.View;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ciro.a2entregableandroid.Model.POJO.Mensaje;
import com.example.ciro.a2entregableandroid.Model.POJO.Usuario;
import com.example.ciro.a2entregableandroid.R;
import com.example.ciro.a2entregableandroid.View.Adapters.AdapterRecyclerViewChat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    EditText editTextMensajeEnviar;
    FloatingActionButton fabEnviarMensaje;
    TextView textViewTestLecturaChat;

    List<Mensaje>listaDeMensajes;
    RecyclerView recyclerViewMensajes;
    AdapterRecyclerViewChat adapterRecyclerViewChat;

    //public static String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);



        textViewTestLecturaChat = findViewById(R.id.textViewTestLecturaChats);
        editTextMensajeEnviar = findViewById(R.id.editTextMensajeEnviar);
        fabEnviarMensaje = findViewById(R.id.fabEnviarMensaje);
        listaDeMensajes = new ArrayList<>();

        recyclerViewMensajes = findViewById(R.id.recyclerViewChat);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewMensajes.setLayoutManager(layoutManager);
        adapterRecyclerViewChat = new AdapterRecyclerViewChat(listaDeMensajes);
        recyclerViewMensajes.setAdapter(adapterRecyclerViewChat);

        leerSimple();

        //Obtengo usuario

        //final String nombre = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        //Fin Obtengo usuario

        fabEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String body = editTextMensajeEnviar.getText().toString();
                escribirMensajeEnFirebase(body,LoginActivity.userID,LoginActivity.userNombre);
                editTextMensajeEnviar.setText("");
                adapterRecyclerViewChat.notifyDataSetChanged();

            }
        });
    }

    public void escribirMensajeEnFirebase(String body,String userID,String nombre){
        DatabaseReference mDatabase;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();
        DatabaseReference referenciaMensajes = mDatabase.child("chat").push();

        Mensaje unMensaje = new Mensaje(userID,body,nombre);
        //Mensaje unMensaje = new Mensaje(body);
        referenciaMensajes.setValue(unMensaje);
        listaDeMensajes.add(unMensaje);
        adapterRecyclerViewChat.notifyDataSetChanged();
    }


    public void leerSimple(){
        DatabaseReference mDatabase;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();
        DatabaseReference referenceChat = mDatabase.child("chat");


        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getChildren();

                if (listaDeMensajes.size() != 0)listaDeMensajes = new ArrayList<>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Mensaje mensajeLeido = dataSnapshot1.getValue(Mensaje.class);
                    listaDeMensajes.add(mensajeLeido);
                }
                adapterRecyclerViewChat.setListaDeMensajes(listaDeMensajes);
                adapterRecyclerViewChat.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ChatActivity.this, "ERROR LEYENDO", Toast.LENGTH_SHORT).show();
            }
        };
        //referenceChat.addListenerForSingleValueEvent(valueEventListener);
        referenceChat.addValueEventListener(valueEventListener);




    }

}
