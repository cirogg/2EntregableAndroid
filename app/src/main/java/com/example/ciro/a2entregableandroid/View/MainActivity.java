package com.example.ciro.a2entregableandroid.View;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


import com.example.ciro.a2entregableandroid.Controller.CamaraFirebaseActivity;
import com.example.ciro.a2entregableandroid.R;

public class MainActivity extends AppCompatActivity implements FragmentFeed.ComunicadorFragmentAActivity {

    FragmentManager fragmentManager;

    Button buttonTest;
    Button buttonTestFirebease;
    Button buttonTestCamara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


        buttonTest = findViewById(R.id.cmdTest);
        buttonTestFirebease = findViewById(R.id.cmdTestFirebase);
        buttonTestCamara = findViewById(R.id.cmdTestCamara);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorMainFeed, new FragmentFeed());
        fragmentTransaction.commit();

        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(unIntent);
            }
        });


        buttonTestFirebease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unIntent = new Intent(MainActivity.this, LecturaFirebaseActivity.class);
                startActivity(unIntent);
            }
        });

        buttonTestCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unIntent = new Intent(MainActivity.this, CamaraFirebaseActivity.class);
                startActivity(unIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void clickearonEnLaObra(Integer pos) {
        Intent unIntent = new Intent(this, LecturaFirebaseActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putInt("pos", pos);
        unIntent.putExtras(unBundle);
        startActivity(unIntent);
    }
}
