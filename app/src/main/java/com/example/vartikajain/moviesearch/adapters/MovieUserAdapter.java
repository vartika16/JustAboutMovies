package com.example.vartikajain.moviesearch.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vartikajain.moviesearch.R;
import com.example.vartikajain.moviesearch.db.Tables.FavouriteTable;
import com.example.vartikajain.moviesearch.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by VARTIKA JAIN on 09-06-2018.
 */

public class MovieUserAdapter extends RecyclerView.Adapter<MovieUserAdapter.Viewholder> {
    Context context;
    ArrayList<Movie> movies = new ArrayList<>();
    SQLiteDatabase db;

    public MovieUserAdapter(Context context, ArrayList<Movie> movies, SQLiteDatabase db) {
        this.context = context;
        this.movies = movies;
        this.db = db;
    }

    public void updateUserMovies(ArrayList<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_movie, parent, false);
        return new MovieUserAdapter.Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {
        holder.tvuserTitle.setText(movies.get(position).getOriginal_title());
        Log.d("TAG1",movies.get(position).getPosterPath());
        Picasso.with(context)
                .load(movies.get(position).getPosterPath())
                .into(holder.ivuserPoster);
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView ivuserPoster;
        TextView tvuserTitle;

        public Viewholder(View itemView) {
            super(itemView);
            ivuserPoster = (ImageView) itemView.findViewById(R.id.ivPoster);
            tvuserTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}
