package com.example.vartikajain.moviesearch.Api;

import com.example.vartikajain.moviesearch.models.Movie;
import com.example.vartikajain.moviesearch.models.MovieResponse;
import com.example.vartikajain.moviesearch.models.Rating;
import com.example.vartikajain.moviesearch.models.RatingResponse;
import com.example.vartikajain.moviesearch.models.ReviewResponse;
import com.example.vartikajain.moviesearch.models.Session;
import com.example.vartikajain.moviesearch.models.Token;
import com.example.vartikajain.moviesearch.models.VideoResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by VARTIKA JAIN on 05-05-2018.
 */

public interface API {

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String api_key);
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String api_key);
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String api_key);
    @GET("movie/upcoming")
    Call<MovieResponse> getUpcomingMovies(@Query("api_key") String api_key);
    @GET("search/movie")
    Call<MovieResponse>getSearchedMovies(@Query("api_key") String api_key, @Query("query") String Query);
    @GET("movie/{movie_id}/reviews")
    Call<ReviewResponse>getReviews(@Path("movie_id") int movie_id, @Query("api_key") String api_key);
    @GET("genre/{genre_id}/movies")
    Call<MovieResponse>getGenreMovies(@Path("genre_id") int genre_id, @Query("api_key") String api_key);
    @GET("discover/movie")
    Call<MovieResponse>getSortedMovie(@Query("api_key") String api_key, @Query("sort_by") String sort_by);
    @GET("discover/movie")
    Call<MovieResponse>getDiscoveredMovies(@Query("api_key") String api_key,
                                           @Query("with_genres")String with_genres,
                                           @Query("primary_release_year") Integer primary_release_year,
                                           @Query("sort_by") String sort_by,
                                           @Query("include_adult") Boolean include_adult);
    @GET("discover/movie")
    Call<MovieResponse>getNoYearMovies(@Query("api_key") String api_key,
                                           @Query("with_genres")String with_genres,
                                           @Query("sort_by") String sort_by,
                                           @Query("include_adult") Boolean include_adult);
    @GET("discover/movie")
    Call<MovieResponse>getNoGenreMovies(@Query("api_key") String api_key,
                                           @Query("primary_release_year") Integer primary_release_year,
                                           @Query("sort_by") String sort_by,
                                           @Query("include_adult") Boolean include_adult);
    @GET("discover/movie")
    Call<MovieResponse>getNoSortByMovies(@Query("api_key") String api_key,
                                           @Query("with_genres")String with_genres,
                                           @Query("primary_release_year") Integer primary_release_year,
                                           @Query("include_adult") Boolean include_adult);
    @GET("discover/movie")
    Call<MovieResponse>getNoAdultMovies(@Query("api_key") String api_key,
                                           @Query("with_genres")String with_genres,
                                           @Query("primary_release_year") Integer primary_release_year,
                                           @Query("sort_by") String sort_by);


    @GET("movie/{movie_id}/recommendations")
    Call<MovieResponse>getRecommendedMovies(@Path("movie_id") int movie_id, @Query("api_key") String api_key);
    @GET("movie/{movie_id}/videos")
    Call<VideoResponse>getVideos(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

    @GET("authentication/token/new")
    Call<Token>getToken(@Query("api_key")String api_key);
    @GET("authentication/session/new")
    Call<Session>getSession(@Query("api_key") String api_key);

    @Headers("Content-Type:application/json")
    @POST("movie/{movie_id}/rating")
    Call<RatingResponse>postRating(@Body Rating rating,@Path("movie_id") int movie_id, @Query("api_key") String api_key);
}
