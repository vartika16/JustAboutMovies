package com.example.vartikajain.moviesearch.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vartikajain.moviesearch.R;
import com.example.vartikajain.moviesearch.models.Review;

import java.util.ArrayList;

/**
 * Created by VARTIKA JAIN on 06-06-2018.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.Viewholder> {
    Context context;
    ArrayList<Review> reviews=new ArrayList<>();
    public ReviewAdapter(Context context){this.context=context;}

    public void updateReviews(ArrayList<Review> reviews){
        this.reviews=reviews;
        notifyDataSetChanged();
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=li.inflate(R.layout.list_item_review,parent,false);
        return new ReviewAdapter.Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        holder.tvAuthor.setText(reviews.get(position).getAuthor());
        holder.tvContent.setText(reviews.get(position).getContent());
    }

    @Override
    public int getItemCount() {return reviews.size();}

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView tvAuthor,tvContent;
        public Viewholder(View itemView) {
            super(itemView);
            tvAuthor= (TextView) itemView.findViewById(R.id.tvAuthor);
            tvContent= (TextView) itemView.findViewById(R.id.tvContent);
        }
    }
}
