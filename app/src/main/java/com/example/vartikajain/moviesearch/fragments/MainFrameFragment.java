package com.example.vartikajain.moviesearch.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vartikajain.moviesearch.Api.ApiService;
import com.example.vartikajain.moviesearch.BuildConfig;
import com.example.vartikajain.moviesearch.R;
import com.example.vartikajain.moviesearch.adapters.MovieAdapter;
import com.example.vartikajain.moviesearch.models.Movie;
import com.example.vartikajain.moviesearch.models.MovieResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFrameFragment extends Fragment {


    public MainFrameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_main_frame, container, false);

        final String TAG="movies";
        RecyclerView rvNowPlaying= (RecyclerView) rootview.findViewById(R.id.rvNowPlaying);
        final ProgressBar pbNowPlaying= (ProgressBar) rootview.findViewById(R.id.pbNowPlaying);
        RecyclerView rvPopular= (RecyclerView) rootview.findViewById(R.id.rvPopular);
        final ProgressBar pbPopular= (ProgressBar) rootview.findViewById(R.id.pbPopular);
        RecyclerView rvTopRated= (RecyclerView) rootview.findViewById(R.id.rvTopRated);
        final ProgressBar pbTopRated= (ProgressBar) rootview.findViewById(R.id.pbTopRated);
        RecyclerView rvUpcoming= (RecyclerView) rootview.findViewById(R.id.rvUpcoming);
        final ProgressBar pbUpcoming= (ProgressBar) rootview.findViewById(R.id.pbUpcoming);

        final MovieAdapter nowplayingAdapter=new MovieAdapter(getActivity(),new ArrayList<Movie>());
        rvNowPlaying.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rvNowPlaying.setAdapter(nowplayingAdapter);

        final MovieAdapter popularAdapter=new MovieAdapter(getActivity(),new ArrayList<Movie>());
        rvPopular.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rvPopular.setAdapter(popularAdapter);

        final MovieAdapter topratedAdapter=new MovieAdapter(getActivity(),new ArrayList<Movie>());
        rvTopRated.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rvTopRated.setAdapter(topratedAdapter);

        final MovieAdapter upcomingAdapter=new MovieAdapter(getActivity(),new ArrayList<Movie>());
        rvUpcoming.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rvUpcoming.setAdapter(upcomingAdapter);


        ApiService.getApi().getNowPlayingMovies(BuildConfig.TMDB_API).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                pbNowPlaying.setIndeterminate(false);
                pbNowPlaying.setVisibility(View.GONE);
                nowplayingAdapter.updateMovies(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

                Toast.makeText(getActivity(),"Error in fetching Data",Toast.LENGTH_SHORT).show();
            }
        });

        ApiService.getApi().getPopularMovies(BuildConfig.TMDB_API).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                pbPopular.setIndeterminate(false);
                pbPopular.setVisibility(View.GONE);
                popularAdapter.updateMovies(response.body().getResults());

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

                Toast.makeText(getActivity(),"Error in fetching Data",Toast.LENGTH_SHORT).show();
            }
        });

        ApiService.getApi().getTopRatedMovies(BuildConfig.TMDB_API).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                pbTopRated.setIndeterminate(false);
                pbTopRated.setVisibility(View.GONE);
                topratedAdapter.updateMovies(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

                Toast.makeText(getActivity(),"Error in fetching Data",Toast.LENGTH_SHORT).show();

            }
        });
        ApiService.getApi().getUpcomingMovies(BuildConfig.TMDB_API).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                pbUpcoming.setIndeterminate(false);
                pbUpcoming.setVisibility(View.GONE);
                upcomingAdapter.updateMovies(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

                Toast.makeText(getActivity(),"error in fetching data",Toast.LENGTH_SHORT).show();
            }
        });

        return rootview;
    }

}
