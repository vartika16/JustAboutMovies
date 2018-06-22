package com.example.vartikajain.moviesearch.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vartikajain.moviesearch.R;
import com.example.vartikajain.moviesearch.fragments.UserFragment;
import com.example.vartikajain.moviesearch.fragments.searchFragment;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnFavourite,btnWatch;
    FragmentManager fragmentManager;
    UserFragment userFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        btnFavourite= (Button) findViewById(R.id.btnFavourite);
        btnWatch= (Button) findViewById(R.id.btnWatch);
        btnFavourite.setOnClickListener(this);
        btnWatch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle=new Bundle();
        fragmentManager=getSupportFragmentManager();
        userFragment=new UserFragment();
        switch (v.getId()){
            case R.id.btnFavourite:
                bundle.putString("ButtonNumber", String.valueOf(1));
                userFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flMovieContainer,userFragment).commit();
                break;
            case R.id.btnWatch:
                bundle.putString("ButtonNumber", String.valueOf(2));
                userFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flMovieContainer,userFragment).commit();
                break;
        }

    }
}
