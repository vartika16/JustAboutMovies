package com.example.vartikajain.moviesearch;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.vartikajain.moviesearch.activities.UserActivity;
import com.example.vartikajain.moviesearch.fragments.DiscoverFragment;
import com.example.vartikajain.moviesearch.fragments.GenreFragment;
import com.example.vartikajain.moviesearch.fragments.MainFrameFragment;
import com.example.vartikajain.moviesearch.fragments.SortFragment;
import com.example.vartikajain.moviesearch.fragments.searchFragment;
import com.example.vartikajain.moviesearch.interfaces.ActivityConsts;

public class MainActivity extends AppCompatActivity {
    ImageButton imgbtnSearch,imgbtnList,imgbtnBack,imgbtnSort,imgbtnUser;
    EditText etMovie;
    FragmentManager fragmentManager;
    searchFragment searchFragment;
    MainFrameFragment mainFrameFragment;
    GenreFragment genreFragment;
    SortFragment sortFragment;
    DiscoverFragment discoverFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgbtnSearch= (ImageButton) findViewById(R.id.imgbtnSearch);
        imgbtnList= (ImageButton) findViewById(R.id.imgbtnList);
        imgbtnBack= (ImageButton) findViewById(R.id.imgbtnBack);
        imgbtnSort= (ImageButton) findViewById(R.id.imgbtnSort);
        imgbtnUser= (ImageButton) findViewById(R.id.imgbtnUser);
        etMovie= (EditText) findViewById(R.id.etMovie);
        fragmentManager=getSupportFragmentManager();
        mainFrameFragment=new MainFrameFragment();
        Intent intent=getIntent();
        beginFrame();


        imgbtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle= new Bundle();
                String searchText=etMovie.getText().toString();
                bundle.putString("search",searchText);
                searchFragment=new searchFragment();
                searchFragment.setArguments(bundle);
                bundle.putString("Calling_Activity", String.valueOf(ActivityConsts.MainActivity));
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,searchFragment)
                        .commit();
            }
        });
        imgbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginFrame();
            }
        });
        imgbtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genreFragment= new GenreFragment();
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreFragment).commit();
            }
        });
        imgbtnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sortFragment=new SortFragment();
//                fragmentManager.beginTransaction().replace(R.id.flFragContainer,sortFragment).commit();
                discoverFragment=new DiscoverFragment();
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,discoverFragment).commit();
            }
        });
        imgbtnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

    }
    public void beginFrame(){
        fragmentManager.beginTransaction().replace(R.id.flFragContainer,mainFrameFragment).commit();
    }
}
