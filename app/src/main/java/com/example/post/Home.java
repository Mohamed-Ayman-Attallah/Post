package com.example.post;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
     ListView listView;
     public  static UserAdapter adapter;

  public static ArrayList<User> values = new  ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.fragment_container);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigtion_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        values.add(new User("Mohamed",R.drawable.a ,
                R.drawable.a , false , false , false ));
        values.add(new User("Mohamed",R.drawable.b,
                R.drawable.b , false , false , false ));
        values.add(new User("Mohamed", R.drawable.c,
                R.drawable.c , false , false , false ));
        values.add(new User("Mohamed",R.drawable.d,
                R.drawable.d , false , false , false ));
        values.add(new User("Mohamed", R.drawable.e,
                R.drawable.e , false , false , false ));
        values.add(new User("Mohamed", R.drawable.a,
                R.drawable.a , false , false , false ));
      adapter = new UserAdapter(this,R.layout.row,values);
        listView.setAdapter(adapter);
    }

private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        switch (item.getItemId()){
            case R.id.nav_home:
            selectedFragment = new HomeFragment();
            break;
            case R.id.nav_favorites:
                selectedFragment = new favoirtesFragmet();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
        return true;
    }
};
}



