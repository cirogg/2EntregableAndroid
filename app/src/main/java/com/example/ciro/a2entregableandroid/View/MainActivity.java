package com.example.ciro.a2entregableandroid.View;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.ciro.a2entregableandroid.R;
import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity implements FragmentFeed.ComunicadorFragmentAActivity {

    FragmentManager fragmentManager;

    Button buttonTest;
    Button buttonTestFirebease;
    Button buttonTestCamara;
    Button buttonTestChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


        buttonTest = findViewById(R.id.cmdTest);
        buttonTestFirebease = findViewById(R.id.cmdTestFirebase);
        buttonTestCamara = findViewById(R.id.cmdTestCamara);
        buttonTestChat = findViewById(R.id.cmdTestChat);

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

        buttonTestChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unIntent = new Intent(MainActivity.this,ChatActivity.class);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        switch (item.getItemId()) {

            case R.id.itemChat:
                //Toast.makeText(this,"yendo a chat",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ChatActivity.class);
                startActivity(intent);
                break;

            case R.id.itemLogOut:

                //Toast.makeText(this, "loggout", Toast.LENGTH_SHORT).show();
                LoginManager.getInstance().logOut();
                Intent ointent = new Intent(this, LoginActivity.class);
                startActivity(ointent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
