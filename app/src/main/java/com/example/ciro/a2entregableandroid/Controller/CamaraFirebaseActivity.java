package com.example.ciro.a2entregableandroid.Controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ciro.a2entregableandroid.R;

import java.io.File;
import java.util.List;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class CamaraFirebaseActivity extends AppCompatActivity {
    ImageView imageViewFotoSacada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara_firebase);
        imageViewFotoSacada = findViewById(R.id.imgCamaraHolder);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagesPicked(@NonNull List<File> imageFiles, EasyImage.ImageSource source, int type) {
                File imageFile = imageFiles.get(0);
                Bitmap bitmapDeImagen = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                imageViewFotoSacada.setImageBitmap(bitmapDeImagen);
            }
        });
    }

    public void sacarFoto(View view) {
        EasyImage.openCamera(this, 1);
    }
}
