package com.example.vartikajain.moviesearch.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vartikajain.moviesearch.Api.ApiService;
import com.example.vartikajain.moviesearch.BuildConfig;
import com.example.vartikajain.moviesearch.R;
import com.example.vartikajain.moviesearch.adapters.MovieUserAdapter;
import com.example.vartikajain.moviesearch.db.MovieHelper;
import com.example.vartikajain.moviesearch.db.Tables.FavouriteTable;
import com.example.vartikajain.moviesearch.db.Tables.WatchListTable;
import com.example.vartikajain.moviesearch.fragments.searchFragment;
import com.example.vartikajain.moviesearch.models.Movie;
import com.example.vartikajain.moviesearch.models.Rating;
import com.example.vartikajain.moviesearch.models.RatingResponse;
import com.example.vartikajain.moviesearch.models.Review;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {
    Button btnRate, btnRecommendation, btnVideo;
    ImageView ivBackdrop;
    TextView tvMovietitle, tvOverview, tvReleasedate, tvUserrating, tvMovieId, tvAdult, tvReview, tvFavourite, tvWatch;
    String adultvalue;
    Boolean adult;
    String result;
    SQLiteDatabase movieDb;
    long favmovieId, watchmovieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        ivBackdrop = (ImageView) findViewById(R.id.ivBackdrop);
        tvMovietitle = (TextView) findViewById(R.id.tvMovietitle);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        tvReleasedate = (TextView) findViewById(R.id.tvRelease_date);
        tvUserrating = (TextView) findViewById(R.id.tvUserrating);
        tvMovieId = (TextView) findViewById(R.id.tvMovieId);
        tvAdult = (TextView) findViewById(R.id.tvAdult);
        tvReview = (TextView) findViewById(R.id.tvReview);
//        btnRate = (Button) findViewById(R.id.btnRate);
        btnRecommendation = (Button) findViewById(R.id.btnRecommendation);
        btnVideo = (Button) findViewById(R.id.btnVideo);
        tvFavourite = (TextView) findViewById(R.id.tvFavourite);
        tvWatch = (TextView) findViewById(R.id.tvWatch);
        final MaterialFavoriteButton materialFavoriteButton = (MaterialFavoriteButton) findViewById(R.id.mfbFav);
        MaterialFavoriteButton materialWatchButton = (MaterialFavoriteButton) findViewById(R.id.mfbWatch);
        movieDb = new MovieHelper(this).getWritableDatabase();
        result = " ";
        adultvalue = "U";
        favmovieId = -1;
        watchmovieId = -1;
        adult = getIntent().getBooleanExtra("adult", false);
        if (adult == true)
            adultvalue = "A";
        tvMovietitle.setText(getIntent().getStringExtra("original_title"));
        tvAdult.setText(adultvalue);
        tvReleasedate.setText("| " + getIntent().getStringExtra("release_date"));
        tvUserrating.setText(String.valueOf(getIntent().getDoubleExtra("rating", 0)));
        tvMovieId.setText(String.valueOf(getIntent().getIntExtra("movieId", 0)));
        tvOverview.setText(getIntent().getStringExtra("overview"));
        Picasso.with(this).load(getIntent().getStringExtra("poster_path")).into(ivBackdrop);
        tvReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this, ReviewActivity.class);
                intent.putExtra("movieId", getIntent().getIntExtra("movieId", 0));
                intent.putExtra("original_title", getIntent().getStringExtra("original_title"));
                startActivity(intent);
            }
        });

        btnRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this, RecommendationActivity.class);
                intent.putExtra("movieId", getIntent().getIntExtra("movieId", 0));
                startActivity(intent);
            }

        });
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this, VideoActivity.class);
                intent.putExtra("movieId", getIntent().getIntExtra("movieId", 0));
                startActivity(intent);

            }
        });
        if (Exists(getIntent().getIntExtra("movieId", 0))) {
            materialFavoriteButton.setFavorite(true);
            materialFavoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {

                    if (favorite == true) {
                        Toast.makeText(MovieDetailActivity.this, " Already added to Favourites", Toast.LENGTH_SHORT).show();
                    } else {
                        FavouriteTable.deleteMovie(movieDb, getIntent().getIntExtra("movieId", 0));
                        Toast.makeText(MovieDetailActivity.this, "Deleted from favourites", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            materialFavoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {

                    if (favorite == true) {
                        long favmovieId = FavouriteTable.insertMovie(movieDb,
                                new Movie(getIntent().getIntExtra("movieId", 0),
                                        getIntent().getStringExtra("poster_path"),
                                        getIntent().getStringExtra("original_title")));
                        Toast.makeText(MovieDetailActivity.this, "Added to Favourites", Toast.LENGTH_SHORT).show();
                    } else {
//                        Toast.makeText(MovieDetailActivity.this,"Deleted from favourites",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        if (ExistsW(getIntent().getIntExtra("movieId", 0))) {
            materialWatchButton.setFavorite(true);
            materialWatchButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {

                    if (favorite == true) {
                        Toast.makeText(MovieDetailActivity.this, " Already added to WatchList", Toast.LENGTH_SHORT).show();
                    } else {
                        WatchListTable.deleteMovie(movieDb, getIntent().getIntExtra("movieId", 0));
                        Toast.makeText(MovieDetailActivity.this, "Deleted from Watchlist", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            materialWatchButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if (favorite == true) {
                        long favmovieId = WatchListTable.insertMovie(movieDb,
                                new Movie(getIntent().getIntExtra("movieId", 0),
                                        getIntent().getStringExtra("poster_path"),
                                        getIntent().getStringExtra("original_title")));
                        Toast.makeText(MovieDetailActivity.this, "Added to WatchList", Toast.LENGTH_SHORT).show();
                    } else {
//                        Toast.makeText(MovieDetailActivity.this,"Deleted from favourites",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    public Boolean Exists(int movieId) {
        String[] projection = {FavouriteTable.Columns.ID, FavouriteTable.Columns.POSTER_PATH,
                FavouriteTable.Columns.ORIGINAL_TITLE};
        String selection = FavouriteTable.Columns.ID + "=?";
        String[] arg = {String.valueOf(movieId)};
        String limit = "1";
        Cursor cursor = movieDb.query(FavouriteTable.TABLE_NAME_1, projection, selection, arg, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
    public  Boolean ExistsW(int movieId){

        String[] projection = {WatchListTable.Columns.ID, WatchListTable.Columns.POSTER_PATH,
                WatchListTable.Columns.ORIGINAL_TITLE};
        String selection = WatchListTable.Columns.ID + "=?";
        String[] arg = {String.valueOf(movieId)};
        String limit = "1";
        Cursor cursor = movieDb.query(WatchListTable.TABLE_NAME_2, projection, selection, arg, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}