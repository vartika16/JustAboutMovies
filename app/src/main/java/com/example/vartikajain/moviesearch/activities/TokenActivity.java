package com.example.vartikajain.moviesearch.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vartikajain.moviesearch.Api.ApiService;
import com.example.vartikajain.moviesearch.BuildConfig;
import com.example.vartikajain.moviesearch.R;
import com.example.vartikajain.moviesearch.models.Session;
import com.example.vartikajain.moviesearch.models.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenActivity extends AppCompatActivity {
    public String token,sessionId;
    Button btnToken,btnSession;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);
        btnToken= (Button) findViewById(R.id.btnToken);
        btnSession= (Button) findViewById(R.id.btnSession);
        btnToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService.getApi().getToken(BuildConfig.TMDB_API).enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        token=response.body().getRequest_token();
                        uri=Uri.parse("https://www.themoviedb.org/authenticate/"+token);
                        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {

                    }
                });
            }
        });
        btnSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService.getApi().getSession(BuildConfig.TMDB_API).enqueue(new Callback<Session>() {
                    @Override
                    public void onResponse(Call<Session> call, Response<Session> response) {
                        sessionId=response.body().getSession_id();
                        Toast.makeText(TokenActivity.this,"Session Id generated!",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Session> call, Throwable t) {
                        Toast.makeText(TokenActivity.this,"Session Id error!",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }
}
