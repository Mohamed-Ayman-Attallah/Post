package com.example.post;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class picture extends AppCompatActivity {
    ImageView Photo_Activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        Photo_Activity = findViewById(R.id.photo_activity);


        String img  = getIntent().getStringExtra("photo");

        Toast.makeText(this, "" + img , Toast.LENGTH_SHORT).show();

        Photo_Activity.setImageBitmap( StringToBitMap(img) );
    }

    static public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    }
