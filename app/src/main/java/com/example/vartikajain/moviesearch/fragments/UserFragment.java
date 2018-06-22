package com.example.vartikajain.moviesearch.fragments;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vartikajain.moviesearch.R;
import com.example.vartikajain.moviesearch.adapters.MovieAdapter;
import com.example.vartikajain.moviesearch.adapters.MovieUserAdapter;
import com.example.vartikajain.moviesearch.db.MovieHelper;
import com.example.vartikajain.moviesearch.db.Tables.FavouriteTable;
import com.example.vartikajain.moviesearch.db.Tables.WatchListTable;
import com.example.vartikajain.moviesearch.models.Movie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_user, container, false);
        TextView tvMovies= (TextView) rootView.findViewById(R.id.tvMovies);
        RecyclerView rvMovies= (RecyclerView) rootView.findViewById(R.id.rvMovies);
        ProgressBar pbMovies= (ProgressBar) rootView.findViewById(R.id.pbMovies);
        ArrayList<Movie> movies=new ArrayList<>();
        SQLiteDatabase movieDb=new MovieHelper(getActivity()).getReadableDatabase();
        MovieUserAdapter movieUserAdapter=new MovieUserAdapter(getActivity(),movies,movieDb);
        rvMovies.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rvMovies.setAdapter(movieUserAdapter);


        Bundle bundle=this.getArguments();
        String btnNumber=bundle.getString("ButtonNumber");
        switch (btnNumber){
            case "1":
                tvMovies.setText("Favourite Movies");
                pbMovies.setIndeterminate(false);
                pbMovies.setVisibility(View.GONE);
                movies= FavouriteTable.getAllMovies(movieDb);
                movieUserAdapter.updateUserMovies(movies);
                break;

            case "2":
                tvMovies.setText("Watch Listed Movies");
                pbMovies.setIndeterminate(false);
                pbMovies.setVisibility(View.GONE);
                movies= WatchListTable.getAllMovies(movieDb);
                movieUserAdapter.updateUserMovies(movies);
                break;
        }
        return rootView;
    }

}
