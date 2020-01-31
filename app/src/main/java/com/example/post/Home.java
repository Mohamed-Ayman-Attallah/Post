package com.example.post;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Home extends AppCompatActivity {
     ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.listView);

//        Intent i = getIntent();
//        String s = i.getStringExtra("photo");

        ArrayList<User> values = new  ArrayList<>();
        values.add(new User("Ahmed",R.drawable.a ,
                R.drawable.ic_launcher_background , false , false , true ));
        values.add(new User("Ahmed",R.drawable.b,
                R.drawable.ic_launcher_background , false , false , true ));
        values.add(new User("Ahmed", R.drawable.c,
                R.drawable.ic_launcher_background , false , false , true ));
        values.add(new User("Ahmed",R.drawable.d,
                R.drawable.ic_launcher_background , false , false , true ));
        values.add(new User("Ahmed", R.drawable.e,
                R.drawable.ic_launcher_background , false , false , true ));
        values.add(new User("Ahmed", R.drawable.a,
                R.drawable.ic_launcher_background , false , false , true ));
        UserAdapter adapter = new UserAdapter(this,R.layout.row,values);
        listView.setAdapter(adapter);
    }

    /**
     * to convert string to Bitmap
     **/
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

    /**
     * convert Bitmap to String
     **/
    static public String encodeImage(Bitmap imagee) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        imagee.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        byte[] b = bytes.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        String encodedImage1 = encodedImage;
        return encodedImage1;
    }
}



