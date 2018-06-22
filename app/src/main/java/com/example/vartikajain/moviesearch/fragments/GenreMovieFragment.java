package com.example.vartikajain.moviesearch.fragments;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vartikajain.moviesearch.Api.ApiService;
import com.example.vartikajain.moviesearch.BuildConfig;
import com.example.vartikajain.moviesearch.R;
import com.example.vartikajain.moviesearch.adapters.MovieAdapter;
import com.example.vartikajain.moviesearch.db.MovieHelper;
import com.example.vartikajain.moviesearch.db.Tables.FavouriteTable;
import com.example.vartikajain.moviesearch.models.Movie;
import com.example.vartikajain.moviesearch.models.MovieResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenreMovieFragment extends Fragment {


    public GenreMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_genre_movie, container, false);
        final TextView tvMovieGenre= (TextView) rootView.findViewById(R.id.tvMovieGenre);
        RecyclerView rvMovieGenre= (RecyclerView) rootView.findViewById(R.id.rvMovieGenre);
        final ProgressBar pbMovieGenre= (ProgressBar) rootView.findViewById(R.id.pbMovieGenre);

        final Bundle bundle=this.getArguments();
        final MovieAdapter MovieGenreAdapter= new MovieAdapter(getActivity(),new ArrayList<Movie>());
        rvMovieGenre.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rvMovieGenre.setAdapter(MovieGenreAdapter);
        tvMovieGenre.setText(bundle.getString("Genre"));
        ApiService.getApi().getGenreMovies(bundle.getInt("Id"), BuildConfig.TMDB_API).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                pbMovieGenre.setIndeterminate(false);
                pbMovieGenre.setVisibility(View.GONE);
                MovieGenreAdapter.updateMovies(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(getActivity(),"error in fetching data",Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }

}
