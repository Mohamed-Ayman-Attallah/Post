package com.example.post;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class fragment_home1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_home1);

        Intent intent = new Intent(fragment_home1.this,Home.class);
        startActivity(intent);
    }
}
