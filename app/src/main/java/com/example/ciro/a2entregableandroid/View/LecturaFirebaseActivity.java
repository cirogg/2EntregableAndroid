package com.example.ciro.a2entregableandroid.View;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ciro.a2entregableandroid.Controller.ArtistaController;
import com.example.ciro.a2entregableandroid.Controller.ObraController;
import com.example.ciro.a2entregableandroid.Model.POJO.Artista;
import com.example.ciro.a2entregableandroid.Model.POJO.DataBaseArtistas;
import com.example.ciro.a2entregableandroid.Model.POJO.Obra;
import com.example.ciro.a2entregableandroid.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class LecturaFirebaseActivity extends AppCompatActivity {

    TextView textViewTituloObra;
    TextView textViewContenedorTest;
    ImageView imageViewObraDetalle;

    FloatingActionButton floatingActionButtonToUpload;

    List<Artista> listaDeArtistasPersistida;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectura_firebase);
        listaDeArtistasPersistida = new ArrayList<>();
        context = getApplicationContext();

        textViewContenedorTest = findViewById(R.id.textFireBaseTest);
        textViewTituloObra = findViewById(R.id.textTituloObra);
        imageViewObraDetalle = findViewById(R.id.imgViewObraDetalle);
        floatingActionButtonToUpload = findViewById(R.id.cmdToSubirFoto);

        Intent unIntent = getIntent();
        Bundle unBundle = unIntent.getExtras();
        Integer pos = unBundle.getInt("pos");

        ///////////////////

        floatingActionButtonToUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unIntent = new Intent(LecturaFirebaseActivity.this, CamaraFirebaseActivity.class);
                startActivity(unIntent);
            }
        });

        ////////////
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        StorageReference imageRef = storageReference.child(FragmentFeed.listaDeObras.get(pos).getImage());
        ///////////

        //Cargo el titulo de la obra clickeada
        textViewTituloObra.setText(FragmentFeed.listaDeObras.get(pos).getTitulo());
        String idDeArtistaDeLaObraSeleccionada = FragmentFeed.listaDeObras.get(pos).getArtistId();
        //Glide.with(this).load(FragmentFeed.listaDeObrasPublica.get(pos).getImage()).into(imageViewObraDetalle);
        Glide.with(this).using(new FirebaseImageLoader()).load(imageRef).into(imageViewObraDetalle);

        if (hayInternet()){
            leerSimple(idDeArtistaDeLaObraSeleccionada);
        }else{
            listaDeArtistasPersistida = new ArrayList<>();
            listaDeArtistasPersistida = getArtistas();
            iterarLocalArtista(listaDeArtistasPersistida,idDeArtistaDeLaObraSeleccionada);

        }

    }

    public void escribir() {
        DatabaseReference mDatabase;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();
        DatabaseReference referencePrimerMensaje = mDatabase.child("mensajes").child("0");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(LecturaFirebaseActivity.this, "HUBO UN CAMBIO", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(LecturaFirebaseActivity.this, "HUBO UN ERROR", Toast.LENGTH_SHORT).show();
            }
        };


    }

    public void leerSimple(final String idDeArtista) {


        DatabaseReference mDatabase;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();
        DatabaseReference databaseReference = mDatabase.child("artists");
        ValueEventListener valueEventListener = new ValueEventListener() {
            Artista ArtistaLeido;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(LecturaFirebaseActivity.this, "LECTURA PIOLA", Toast.LENGTH_SHORT).show();
                ArtistaLeido = iterarJSonFirebaseArtista(dataSnapshot, idDeArtista);

                if (getArtistas().size() == 0){
                    listaDeArtistasPersistida = iterarJSonFirebaseArtistaParaGuardar(dataSnapshot);
                }
                textViewContenedorTest.setText(ArtistaLeido.toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(LecturaFirebaseActivity.this, "ERROR EN LECTURA", Toast.LENGTH_SHORT).show();
            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }

    public Artista iterarJSonFirebaseArtista(DataSnapshot dataSnapshot, String idDeArtista) {
        Artista ArtistaARetornar = null;
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
            Artista ArtistaLeidoDelIter = dataSnapshot1.getValue(Artista.class);
            if (ArtistaLeidoDelIter.getArtistId().equals(idDeArtista)) {
                ArtistaARetornar = ArtistaLeidoDelIter;
                break;
            }
        }
        return ArtistaARetornar;
    }

    //TODO Deberían estar en un controller. Si tengo tiempo lo aplico.

    public List<Artista> iterarJSonFirebaseArtistaParaGuardar(DataSnapshot dataSnapshot) {
        List<Artista>listitaARetornar = new ArrayList<>();
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
            Artista ArtistaLeidoDelIter = dataSnapshot1.getValue(Artista.class);
            listitaARetornar.add(ArtistaLeidoDelIter);
        }
        persistirLista(listitaARetornar);

        return listitaARetornar;
    }
    public Artista iterarLocalArtista(List<Artista>listita,String idDeArtista){
        Artista artistaARetornar = null;
        for (Artista artista : listita) {
            if (artista.getArtistId().equals(idDeArtista)){
                artistaARetornar = artista;
                }
                break;
        }
        textViewContenedorTest.setText(artistaARetornar.toString());
        //isArtistaPersistido = true;
        return artistaARetornar;
    }
    private boolean hayInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(context, "Hay internet", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(context, "NO hay internet", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public List<Artista> getArtistas(){
        DataBaseArtistas dataBase = new DataBaseArtistas(context);
        //persistirLista(getObras(),this);
        return dataBase.getAllArtistas();


    }
    public void addArtista(Artista artista){
        DataBaseArtistas dataBase = new DataBaseArtistas(context);
        dataBase.insertAll(artista);
    }
    public void persistirLista(List<Artista>listita){
        //listita.clear();
        //DataBase dataBase = new DataBase(context);
        for (Artista artista: listita) {
            addArtista(artista);
        }
        ArtistaController.isArtistaPersistido = true;
    }



}
