package com.example.vartikajain.moviesearch.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vartikajain.moviesearch.db.Tables.FavouriteTable;
import com.example.vartikajain.moviesearch.db.Tables.WatchListTable;

/**
 * Created by VARTIKA JAIN on 09-06-2018.
 */

public class MovieHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="Movie.db";
    public static final int DB_VER= 1;

    public MovieHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(FavouriteTable.CMD_CREATE);
        db.execSQL(WatchListTable.CMD_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
