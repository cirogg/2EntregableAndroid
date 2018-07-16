package com.example.ciro.a2entregableandroid.View;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ciro.a2entregableandroid.R;

public class Moma extends AppCompatActivity {

    Button fabEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moma);

        fabEntrar = findViewById(R.id.fabENTRAR);
        fabEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Moma.this,LoginActivity.class));
            }
        });



    }
}
