package com.example.ciro.a2entregableandroid.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ciro.a2entregableandroid.Model.POJO.Artista;
import com.example.ciro.a2entregableandroid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LecturaFirebaseActivity extends AppCompatActivity {

    TextView textViewTituloObra;
    TextView textViewContenedorTest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectura_firebase);
        textViewContenedorTest = findViewById(R.id.textFireBaseTest);
        textViewTituloObra = findViewById(R.id.textTituloObra);

        Intent unIntent = getIntent();
        Bundle unBundle = unIntent.getExtras();
        Integer pos = unBundle.getInt("pos");

        ///////////////////
        //Cargo el titulo de la obra clickeada
        textViewTituloObra.setText(FragmentFeed.listaDeObrasPublica.get(pos).getTitulo());
        String idDeArtistaDeLaObraSeleccionada = FragmentFeed.listaDeObrasPublica.get(pos).getArtistId();


        leerSimple(idDeArtistaDeLaObraSeleccionada);
    }

    public void escribir(){
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

    public void leerSimple (final String idDeArtista){


        DatabaseReference mDatabase;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();


        DatabaseReference databaseReference = mDatabase.child("artists");

        ValueEventListener valueEventListener = new ValueEventListener() {
            Artista ArtistaLeido;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(LecturaFirebaseActivity.this, "LECTURA PIOLA", Toast.LENGTH_SHORT).show();
               // Artista ArtistaLeido = dataSnapshot.getValue(Artista.class);

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Artista ArtistaLeidoDelIter = dataSnapshot1.getValue(Artista.class);
                    if (ArtistaLeidoDelIter.getArtistId().equals(idDeArtista)){
                        ArtistaLeido = ArtistaLeidoDelIter;
                        break;
                    }
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

}
