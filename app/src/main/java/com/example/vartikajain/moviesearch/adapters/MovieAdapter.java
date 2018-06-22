package com.example.vartikajain.moviesearch.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vartikajain.moviesearch.R;
import com.example.vartikajain.moviesearch.activities.MovieDetailActivity;
import com.example.vartikajain.moviesearch.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by VARTIKA JAIN on 27-05-2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Viewholder> {

    Context context;
    ArrayList<Movie> movies=new ArrayList<>();
    public MovieAdapter(Context context, ArrayList<Movie> movies){
        this.context=context;
        this.movies=movies;
    }

    public void updateMovies(ArrayList<Movie> movies){
        this.movies=movies;
        notifyDataSetChanged();
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=li.inflate(R.layout.list_item_movie,parent,false);
        return new MovieAdapter.Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {

        holder.tvTitle.setText(movies.get(position).getOriginal_title());
        Picasso.with(context)
                .load(movies.get(position).getPoster_path())
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }



    class Viewholder extends RecyclerView.ViewHolder{

        ImageView ivPoster;
        TextView tvTitle;
        String TAG="position";
        public Viewholder(View itemView) {
            super(itemView);
            ivPoster= (ImageView) itemView.findViewById(R.id.ivPoster);
            tvTitle= (TextView) itemView.findViewById(R.id.tvTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent= new Intent(context, MovieDetailActivity.class);
                    int pos=getAdapterPosition();
                    Log.d(TAG,"Adapter position is:"+pos);
                    Movie clickedMovie=movies.get(pos);

                    intent.putExtra("poster_path",clickedMovie.getPoster_path());
                    intent.putExtra("original_title",clickedMovie.getOriginal_title());
                    intent.putExtra("overview",clickedMovie.getOverview());
                    intent.putExtra("release_date",clickedMovie.getRelease_date());
                    intent.putExtra("rating",clickedMovie.getVote_average());
                    intent.putExtra("movieId",clickedMovie.getId());
                    intent.putExtra("adult",clickedMovie.isAdult());
                    intent.putExtra("imdb_id",clickedMovie.getImdb_id());
                    context.startActivity(intent);
                }
            });

        }
    }
}
