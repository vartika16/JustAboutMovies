package com.example.vartikajain.moviesearch.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vartikajain.moviesearch.Api.ApiService;
import com.example.vartikajain.moviesearch.BuildConfig;
import com.example.vartikajain.moviesearch.R;
import com.example.vartikajain.moviesearch.adapters.ReviewAdapter;
import com.example.vartikajain.moviesearch.models.ReviewResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {

    ImageButton ibBack;
    RecyclerView rvReviews;
    TextView tvReviewNo,tvMovieName,tvReview;
    int movieId;
    String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ibBack= (ImageButton) findViewById(R.id.ibBack);
        rvReviews= (RecyclerView) findViewById(R.id.rvReviews);
        tvReviewNo= (TextView) findViewById(R.id.tvReviewNo);
        tvMovieName= (TextView) findViewById(R.id.tvMovieName);
        tvReview= (TextView) findViewById(R.id.tvReview);

        tvMovieName.setText(getIntent().getStringExtra("original_title"));
        movieId=getIntent().getIntExtra("movieId",0);

        final ReviewAdapter reviewAdapter=new ReviewAdapter(this);
        rvReviews.setLayoutManager(new LinearLayoutManager(this));
        rvReviews.setAdapter(reviewAdapter);

        ApiService.getApi().getReviews(movieId,BuildConfig.TMDB_API).enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                tvReviewNo.setText(String.valueOf(response.body().getTotal_results()));
                if(response.body().getTotal_results()==1||response.body().getTotal_results()==0)
                    tvReview.setText("Review");
                else tvReview.setText("Reviews");
                reviewAdapter.updateReviews(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {

                Toast.makeText(ReviewActivity.this,"Error in fetching Reviews",Toast.LENGTH_SHORT).show();

            }
        });
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
