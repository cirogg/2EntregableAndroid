package com.example.ciro.a2entregableandroid.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ciro.a2entregableandroid.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
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

    public void subirFotoFirebase(View view){

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference carpetaImagenesReference = storageRef.child("userUploads").child(LoginActivity.userID);
        StorageReference imageRef = storageRef.child("usersUploads").child(LoginActivity.userID).child("upload");
        //StorageReference imageRef = storageRef.child("uploadTest.jpg");



        imageViewFotoSacada.setDrawingCacheEnabled(true);
        imageViewFotoSacada.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageViewFotoSacada.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        final UploadTask uploadTask = imageRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(CamaraFirebaseActivity.this, "Upload Fail", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Toast.makeText(CamaraFirebaseActivity.this, "Upload piola", Toast.LENGTH_SHORT).show();
                String urlDeLaImagenSubida = taskSnapshot.getDownloadUrl().toString();
            }
        });
    }

   /* public String obtenerUserIdActual(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        return  firebaseAuth.getUid();
    }*/

}
