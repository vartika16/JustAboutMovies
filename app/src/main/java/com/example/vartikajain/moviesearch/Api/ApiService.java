package com.example.vartikajain.moviesearch.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by VARTIKA JAIN on 05-05-2018.
 */

public class ApiService {

    private ApiService(){}
    private static API MovieDBApi=null;

    public static API getApi(){
        if(MovieDBApi==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            MovieDBApi=retrofit.create(API.class);
        }
        return MovieDBApi;
    }
}
