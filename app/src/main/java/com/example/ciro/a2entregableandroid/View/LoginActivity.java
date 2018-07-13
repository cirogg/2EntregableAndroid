package com.example.ciro.a2entregableandroid.View;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ciro.a2entregableandroid.Model.POJO.Usuario;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

import com.example.ciro.a2entregableandroid.R;
//import com.facebook.login.LoginClient;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    LoginButton loginButton;
    private static final String EMAIL = "email";
    CallbackManager callbackManager;

    EditText editTextTTS;
    Button buttonTTS;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextTTS = findViewById(R.id.editTextTtsTest);
        buttonTTS = findViewById(R.id.cmdttsTest);
        // FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);

        /////////////////////TEST DE TTS/////////////////


        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                textToSpeech.setLanguage(new Locale("es", "ES"));
                speakText();

                //speakText();
            }
        });

        buttonTTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               speakText();
            }
        });





       /////////////////////////////////////////////////////////////

        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());


            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Logueo cancelado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(LoginActivity.this, "Logueo Erroneo", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {

        ////////Autentificador de Facebook w/ Firebase
        final FirebaseAuth mAuth;
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        ///////Fin Autentificador de Facebook w/ Firebase


        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userNombre = user.getDisplayName();

                            String userID = user.getUid();
                            escribirUserEnFirebase(userID,userNombre);

                        } else {
                            Log.d("firebasito",task.getException().toString());
                        }
                    }
                });
    }

    public void speakText(){
        textToSpeech.speak(editTextTTS.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
    }

    public void escribirUserEnFirebase(String userID, String userNombre){
        DatabaseReference mDatabase;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();
        DatabaseReference referenciaUsuario = mDatabase.child("usuarios").child(userID);

        Usuario unUsuario = new Usuario(userNombre,userID);
        referenciaUsuario.setValue(unUsuario);


        Toast.makeText(LoginActivity.this, "Logueo Exitoso", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }


}
