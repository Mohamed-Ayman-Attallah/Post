package com.example.post;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class fragment_favourite extends AppCompatActivity {

    CardView cardView1 , cardView2 , cardView3 , cardView4 , cardView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_favourite);

        cardView1 = findViewById(R.id.a1);
        cardView2 = findViewById(R.id.a2);
        cardView3 = findViewById(R.id.a3);
        cardView4 = findViewById(R.id.a4);
        cardView5 = findViewById(R.id.a5);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToFacebookPage("https://www.facebook.com/errorteam55/");

            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"email subject");
                intent.putExtra(Intent.EXTRA_TEXT,"body of email");
                startActivity(intent);
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                website("https://ao41866.wixsite.com/errorteam?fbclid=IwAR2YhbFrpuJdncRcg9l6FZ5r9__Cy86Bb7qj1WXIrXheOWBBOt3jedWCbIQ");
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01119816157"));
                startActivity(intent);
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo: 30.470106,31.196216"));
                startActivity(intent);
            }
        });

    }private void goToFacebookPage(String id){
        try {
            Intent intent=new Intent(Intent.ACTION_VIEW , Uri.parse("fb://page/"+id));
            startActivity(intent);

        }catch (ActivityNotFoundException e){
            Intent intent=new Intent(Intent.ACTION_VIEW , Uri.parse("https://.WWW.facebook.com/"+id));
            startActivity(intent);

        }



    }
    public void website(String url){
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
