package com.example.vartikajain.moviesearch.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class RecommendationActivity extends AppCompatActivity {
    TextView tvRecommend;
    ProgressBar pbRecommend;
    RecyclerView rvRecommend;
    int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        tvRecommend= (TextView) findViewById(R.id.tvRecommend);
        pbRecommend= (ProgressBar) findViewById(R.id.pbRecommend);
        rvRecommend= (RecyclerView) findViewById(R.id.rvRecommend);
        rvRecommend.setLayoutManager(new GridLayoutManager(this,2));
        final MovieAdapter recommendedAdapter=new MovieAdapter(this,new ArrayList<Movie>());
        rvRecommend.setAdapter(recommendedAdapter);
        movieId = getIntent().getIntExtra("movieId", 0);

        ApiService.getApi().getRecommendedMovies(movieId, BuildConfig.TMDB_API).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                pbRecommend.setIndeterminate(false);
                pbRecommend.setVisibility(View.GONE);
                recommendedAdapter.updateMovies(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(RecommendationActivity.this,"Error in fetching Data",Toast.LENGTH_SHORT).show();


            }
        });

    }
}
