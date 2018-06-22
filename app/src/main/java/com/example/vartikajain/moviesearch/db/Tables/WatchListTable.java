package com.example.vartikajain.moviesearch.db.Tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vartikajain.moviesearch.models.Movie;

import java.util.ArrayList;

import static com.example.vartikajain.moviesearch.db.Tables.DBConsts.CMD_CREATE_TABLE_INE;
import static com.example.vartikajain.moviesearch.db.Tables.DBConsts.COMMA;
import static com.example.vartikajain.moviesearch.db.Tables.DBConsts.LBR;
import static com.example.vartikajain.moviesearch.db.Tables.DBConsts.RBR;
import static com.example.vartikajain.moviesearch.db.Tables.DBConsts.SEMI;
import static com.example.vartikajain.moviesearch.db.Tables.DBConsts.TYPE_AI;
import static com.example.vartikajain.moviesearch.db.Tables.DBConsts.TYPE_INT;
import static com.example.vartikajain.moviesearch.db.Tables.DBConsts.TYPE_PK;
import static com.example.vartikajain.moviesearch.db.Tables.DBConsts.TYPE_TEXT;

/**
 * Created by VARTIKA JAIN on 09-06-2018.
 */

public class WatchListTable {
    public static final String TABLE_NAME_2="watch_list";
    public interface Columns{
        String ID="id";
        String POSTER_PATH="poster_path";
        String ORIGINAL_TITLE="original_title";
    }
    public static final String CMD_CREATE=
            CMD_CREATE_TABLE_INE+ TABLE_NAME_2 +
                    LBR+
                    Columns.ID + TYPE_INT + TYPE_PK + TYPE_AI+ COMMA +
                    Columns.POSTER_PATH + TYPE_TEXT +COMMA+
                    Columns.ORIGINAL_TITLE + TYPE_TEXT +
                    RBR +
                    SEMI ;
    public static ArrayList<Movie> getAllMovies(SQLiteDatabase db){
        ArrayList<Movie> movies=new ArrayList<>();
        Cursor c=db.query(TABLE_NAME_2,
                new String[]{Columns.ID, Columns.POSTER_PATH, Columns.ORIGINAL_TITLE},
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
        return db.insert(TABLE_NAME_2,
                null,
                movieData
        );
    }
    public static int deleteMovie(SQLiteDatabase db,long movieId){

        String selection= Columns.ID + "=?";
        String arg[]={String.valueOf(movieId)};
        int n=db.delete(TABLE_NAME_2,selection,arg);
        return n;
    }
}
