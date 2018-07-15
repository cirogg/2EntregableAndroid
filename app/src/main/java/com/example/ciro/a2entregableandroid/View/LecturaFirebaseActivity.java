package com.example.ciro.a2entregableandroid.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ciro.a2entregableandroid.Model.POJO.Artista;
import com.example.ciro.a2entregableandroid.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class LecturaFirebaseActivity extends AppCompatActivity {

    TextView textViewTituloObra;
    TextView textViewContenedorTest;
    ImageView imageViewObraDetalle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectura_firebase);
        textViewContenedorTest = findViewById(R.id.textFireBaseTest);
        textViewTituloObra = findViewById(R.id.textTituloObra);
        imageViewObraDetalle = findViewById(R.id.imgViewObraDetalle);

        Intent unIntent = getIntent();
        Bundle unBundle = unIntent.getExtras();
        Integer pos = unBundle.getInt("pos");

        ///////////////////

        ////////////
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        StorageReference imageRef = storageReference.child(FragmentFeed.listaDeObrasPublica.get(pos).getImage());
        ///////////

        //Cargo el titulo de la obra clickeada
        textViewTituloObra.setText(FragmentFeed.listaDeObrasPublica.get(pos).getTitulo());
        String idDeArtistaDeLaObraSeleccionada = FragmentFeed.listaDeObrasPublica.get(pos).getArtistId();
        //Glide.with(this).load(FragmentFeed.listaDeObrasPublica.get(pos).getImage()).into(imageViewObraDetalle);
        Glide.with(this).using(new FirebaseImageLoader()).load(imageRef).into(imageViewObraDetalle);


        leerSimple(idDeArtistaDeLaObraSeleccionada);
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

}
