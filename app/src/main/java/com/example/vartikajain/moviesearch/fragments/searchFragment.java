package com.example.vartikajain.moviesearch.fragments;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vartikajain.moviesearch.Api.API;
import com.example.vartikajain.moviesearch.Api.ApiService;
import com.example.vartikajain.moviesearch.BuildConfig;
import com.example.vartikajain.moviesearch.R;
import com.example.vartikajain.moviesearch.adapters.MovieAdapter;
import com.example.vartikajain.moviesearch.db.MovieHelper;
import com.example.vartikajain.moviesearch.db.Tables.FavouriteTable;
import com.example.vartikajain.moviesearch.interfaces.ActivityConsts;
import com.example.vartikajain.moviesearch.models.Movie;
import com.example.vartikajain.moviesearch.models.MovieResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class searchFragment extends Fragment {


    public searchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        RecyclerView rvSearchMovie = (RecyclerView) rootView.findViewById(R.id.rvSearchMovie);
        final ProgressBar pbResult = (ProgressBar) rootView.findViewById(R.id.pbResult);
        final TextView tvResultMovies= (TextView) rootView.findViewById(R.id.tvResultMovies);
        final MovieAdapter searchAdapter = new MovieAdapter(getActivity(), new ArrayList<Movie>());
        rvSearchMovie.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvSearchMovie.setAdapter(searchAdapter);
        final Bundle bundle = this.getArguments();
        final String calling_activity=bundle.getString("Calling_Activity");
        String sort_query=bundle.getString("sort_by");
        String query = bundle.getString("search");
        String genreId="0";
        Integer primary_release_year=bundle.getInt("primary_release_year");
        String genre=bundle.getString("genre");
        String sort_by=bundle.getString("sort_by");
        Boolean adult=bundle.getBoolean("include_adult");
        Log.d("TAG","discover:"+primary_release_year+","+sort_by+","+adult);

        switch (calling_activity) {
            case "1001":
            ApiService.getApi().getSearchedMovies(BuildConfig.TMDB_API, query).enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    pbResult.setIndeterminate(false);
                    pbResult.setVisibility(View.GONE);
                    searchAdapter.updateMovies(response.body().getResults());
                    if (response.body().getResults().size()==0)
                        tvResultMovies.setText("No Results Found");
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "error in fetching data", Toast.LENGTH_SHORT).show();
                }
            });
                break;
            case "1002":
            ApiService.getApi().getSortedMovie(BuildConfig.TMDB_API, sort_query).enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    tvResultMovies.setText(bundle.getString("sort_by"));
                    pbResult.setIndeterminate(false);
                    pbResult.setVisibility(View.GONE);
                    searchAdapter.updateMovies(response.body().getResults());
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "error in fetching data", Toast.LENGTH_SHORT).show();

                }
            });
                break;
            case "1004":
                switch (genre){
                    case "Action":
                        genreId="28";
                        break;
                    case "Adventure":
                        genreId="12";
                        break;
                    case "Animation":
                        genreId="16";
                        break;
                    case "Comedy":
                        genreId="35";
                        break;
                    case "Crime":
                        genreId="80";
                        break;
                    case "Documentary":
                        genreId="99";
                        break;
                    case "Drama":
                        genreId="18";
                        break;
                    case "Family":
                        genreId="10751";
                        break;
                    case "Fantasy":
                        genreId="14";
                        break;
                    case "History":
                        genreId="36";
                        break;
                    case "Horror":
                        genreId="27";
                        break;
                    case "Music":
                        genreId="10402";
                        break;
                    case "Mystery":
                        genreId="9648";
                        break;
                    case "Romance":
                        genreId="10749";
                        break;

                    case "SciFi":
                        genreId="878";
                        break;
                    case "TVMovie":
                        genreId="10770";
                        break;
                    case "Thriller":
                        genreId="53";
                        break;
                    case "War":
                        genreId="10752";
                        break;
                    case "Western":
                        genreId="37";
                        break;
                }
                if (primary_release_year==0&&genreId!="0"){
                    ApiService.getApi().getNoYearMovies(BuildConfig.TMDB_API,genreId,sort_by,adult).enqueue(new Callback<MovieResponse>() {
                        @Override
                        public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                            pbResult.setIndeterminate(false);
                            pbResult.setVisibility(View.GONE);
                            searchAdapter.updateMovies(response.body().getResults());
                            if (response.body().getResults().size()==0)
                                tvResultMovies.setText("No Results Found");
                        }

                        @Override
                        public void onFailure(Call<MovieResponse> call, Throwable t) {
                            Toast.makeText(getActivity(), "error in fetching data", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                else if (genreId=="0"){
                    ApiService.getApi().getNoGenreMovies(BuildConfig.TMDB_API,primary_release_year,sort_by,adult)
                            .enqueue(new Callback<MovieResponse>() {
                                @Override
                                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                                    pbResult.setIndeterminate(false);
                                    pbResult.setVisibility(View.GONE);
                                    searchAdapter.updateMovies(response.body().getResults());
                                    if (response.body().getResults().size()==0)
                                        tvResultMovies.setText("No Results Found");

                                }

                                @Override
                                public void onFailure(Call<MovieResponse> call, Throwable t) {
                                    Toast.makeText(getActivity(), "error in fetching data", Toast.LENGTH_SHORT).show();

                                }
                            });

                }
                else{
                ApiService.getApi().getDiscoveredMovies(BuildConfig.TMDB_API,genreId,primary_release_year,sort_by,adult)
                        .enqueue(new Callback<MovieResponse>() {
                            @Override
                            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                                pbResult.setIndeterminate(false);
                                pbResult.setVisibility(View.GONE);
                                searchAdapter.updateMovies(response.body().getResults());
                                if (response.body().getResults().size()==0)
                                    tvResultMovies.setText("No Results Found");
                            }

                            @Override
                            public void onFailure(Call<MovieResponse> call, Throwable t) {
                                Toast.makeText(getActivity(), "error in fetching data", Toast.LENGTH_SHORT).show();
                            }
                        });

                }
            }
            return rootView;
        }
}
