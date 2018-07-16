package com.example.ciro.a2entregableandroid.View;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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




        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorMainFeed, new FragmentFeed());
        fragmentTransaction.commit();


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
                if (hayInternet()) {
                    Intent intent = new Intent(this, ChatActivity.class);
                    startActivity(intent);
                    break;
                }else{
                    Toast.makeText(this, "No hay internet", Toast.LENGTH_SHORT).show();
                }

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



    private boolean hayInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(this, "Hay internet", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "NO hay internet", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
