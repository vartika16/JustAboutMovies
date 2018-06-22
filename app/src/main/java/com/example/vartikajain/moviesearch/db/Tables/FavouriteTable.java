package com.example.vartikajain.moviesearch.db.Tables;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.vartikajain.moviesearch.models.Movie;

import java.util.ArrayList;

import static com.example.vartikajain.moviesearch.db.Tables.DBConsts.*;

/**
 * Created by VARTIKA JAIN on 09-06-2018.
 */

public class FavouriteTable {
    public static final String TABLE_NAME_1="favourites";
    public interface Columns{
        String ID="id";
        String POSTER_PATH="poster_path";
        String ORIGINAL_TITLE="original_title";
    }
    public static final String CMD_CREATE=
            CMD_CREATE_TABLE_INE+ TABLE_NAME_1 +
                    LBR+
                    Columns.ID + TYPE_INT + TYPE_PK + TYPE_AI+ COMMA +
                    Columns.POSTER_PATH + TYPE_TEXT + COMMA +
                    Columns.ORIGINAL_TITLE + TYPE_TEXT +
                    RBR +
                    SEMI ;
    public static ArrayList<Movie> getAllMovies(SQLiteDatabase db){
        ArrayList<Movie> movies=new ArrayList<>();
        Cursor c=db.query(TABLE_NAME_1,
                new String[]{Columns.ID,Columns.POSTER_PATH,Columns.ORIGINAL_TITLE},
                null,
                null,
                null,
                null,
                null);
        int colForId=c.getColumnIndex(Columns.ID);
        int colForPoster=c.getColumnIndex(Columns.POSTER_PATH);
        int colForTitle=c.getColumnIndex(Columns.ORIGINAL_TITLE);
        while (c.moveToNext()){
            movies.add(new Movie(c.getInt(colForId),c.getString(colForPoster),c.getString(colForTitle)));
        }
        return movies;
    }
    public static long insertMovie(SQLiteDatabase db, Movie movie) {

        ContentValues movieData = new ContentValues();
        movieData.put(Columns.ID, movie.getId());
        movieData.put(Columns.POSTER_PATH, movie.getPosterPath());
        movieData.put(Columns.ORIGINAL_TITLE, movie.getOriginal_title());
        return db.insert(TABLE_NAME_1,
                null,
                movieData
        );
    }
    public static int deleteMovie(SQLiteDatabase db,long movieId){

        String selection=Columns.ID + "=?";
        String arg[]={String.valueOf(movieId)};
        int n=db.delete(TABLE_NAME_1,selection,arg);
        return n;
    }
}
