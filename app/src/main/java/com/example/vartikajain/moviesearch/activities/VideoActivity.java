package com.example.vartikajain.moviesearch.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vartikajain.moviesearch.Api.ApiService;
import com.example.vartikajain.moviesearch.BuildConfig;
import com.example.vartikajain.moviesearch.R;
import com.example.vartikajain.moviesearch.adapters.MovieAdapter;
import com.example.vartikajain.moviesearch.adapters.VideoAdapter;
import com.example.vartikajain.moviesearch.models.Movie;
import com.example.vartikajain.moviesearch.models.VideoResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends AppCompatActivity {
    TextView tvVideo;
    RecyclerView rvVideo;
    ProgressBar pbVideo;
    int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        tvVideo= (TextView) findViewById(R.id.tvVideo);
        pbVideo= (ProgressBar) findViewById(R.id.pbVideo);
        rvVideo= (RecyclerView) findViewById(R.id.rvVideo);
        final VideoAdapter videoAdapter=new VideoAdapter(this);
        rvVideo.setLayoutManager(new LinearLayoutManager(this));
        rvVideo.setAdapter(videoAdapter);
        movieId = getIntent().getIntExtra("movieId", 0);

        ApiService.getApi().getVideos(movieId, BuildConfig.TMDB_API).enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                pbVideo.setIndeterminate(false);
                pbVideo.setVisibility(View.GONE);
                videoAdapter.updateVideos(response.body().getResults());
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {

                Toast.makeText(VideoActivity.this,"Error in playing video",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
