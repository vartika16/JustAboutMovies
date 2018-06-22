package com.example.vartikajain.moviesearch.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.vartikajain.moviesearch.MainActivity;
import com.example.vartikajain.moviesearch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenreFragment extends Fragment implements View.OnClickListener {


    public GenreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_genre, container, false);
        rootView.findViewById(R.id.tvAction).setOnClickListener(this);
        rootView.findViewById(R.id.tvAdventure).setOnClickListener(this);
        rootView.findViewById(R.id.tvAnimation).setOnClickListener(this);
        rootView.findViewById(R.id.tvComedy).setOnClickListener(this);
        rootView.findViewById(R.id.tvCrime).setOnClickListener(this);
        rootView.findViewById(R.id.tvDocumentary).setOnClickListener(this);
        rootView.findViewById(R.id.tvDrama).setOnClickListener(this);
        rootView.findViewById(R.id.tvFamily).setOnClickListener(this);
        rootView.findViewById(R.id.tvFantasy).setOnClickListener(this);
        rootView.findViewById(R.id.tvHistory).setOnClickListener(this);
        rootView.findViewById(R.id.tvHorror).setOnClickListener(this);
        rootView.findViewById(R.id.tvMusic).setOnClickListener(this);
        rootView.findViewById(R.id.tvMystery).setOnClickListener(this);
        rootView.findViewById(R.id.tvRomance).setOnClickListener(this);
        rootView.findViewById(R.id.tvSciFi).setOnClickListener(this);
        rootView.findViewById(R.id.tvTVMovie).setOnClickListener(this);
        rootView.findViewById(R.id.tvThriller).setOnClickListener(this);
        rootView.findViewById(R.id.tvWar).setOnClickListener(this);
        rootView.findViewById(R.id.tvWestern).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        GenreMovieFragment genreMovieFragment=new GenreMovieFragment();
        Bundle bundle=new Bundle();
        FragmentManager fragmentManager=getFragmentManager();

        switch (v.getId()){

            case R.id.tvAction:
                bundle.putString("Genre","Action");
                bundle.putInt("Id",28);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvAdventure:
                bundle.putString("Genre","Adventure");
                bundle.putInt("Id",12);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvAnimation:
                bundle.putString("Genre","Animation");
                bundle.putInt("Id",16);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvComedy:
                bundle.putString("Genre","Comedy");
                bundle.putInt("Id",35);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvCrime:
                bundle.putString("Genre","Crime");
                bundle.putInt("Id",80);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvDocumentary:
                bundle.putString("Genre","Documentary");
                bundle.putInt("Id",99);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvDrama:
                bundle.putString("Genre","Drama");
                bundle.putInt("Id",18);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvFamily:
                bundle.putString("Genre","Family");
                bundle.putInt("Id",10751);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvFantasy:
                bundle.putString("Genre","Fantasy");
                bundle.putInt("Id",14);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvHistory:
                bundle.putString("Genre","History");
                bundle.putInt("Id",36);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvHorror:
                bundle.putString("Genre","Horror");
                bundle.putInt("Id",27);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvMusic:
                bundle.putString("Genre","Music");
                bundle.putInt("Id",10402);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvMystery:
                bundle.putString("Genre","Mystery");
                bundle.putInt("Id",9648);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvRomance:
                bundle.putString("Genre","Romance");
                bundle.putInt("Id",10749);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvSciFi:
                bundle.putString("Genre","Science Fiction");
                bundle.putInt("Id",878);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvTVMovie:
                bundle.putString("Genre","TV Movie");
                bundle.putInt("Id",10770);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvThriller:
                bundle.putString("Genre","Thriller");
                bundle.putInt("Id",53);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvWar:
                bundle.putString("Genre","War");
                bundle.putInt("Id",10752);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;
            case R.id.tvWestern:
                bundle.putString("Genre","Western");
                bundle.putInt("Id",37);
                genreMovieFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flFragContainer,genreMovieFragment)
                        .commit();
                break;

        }
    }
}
