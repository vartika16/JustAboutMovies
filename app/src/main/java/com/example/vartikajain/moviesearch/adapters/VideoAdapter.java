package com.example.vartikajain.moviesearch.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.vartikajain.moviesearch.R;
import com.example.vartikajain.moviesearch.models.Video;

import java.util.ArrayList;

/**
 * Created by VARTIKA JAIN on 08-06-2018.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.Viewholder> {
    Context context;
    ArrayList<Video> videos=new ArrayList<>();
    public VideoAdapter(Context context){
        this.context=context;
    }
    public void updateVideos(ArrayList<Video> videos){
        this.videos=videos;
        notifyDataSetChanged();
    }
    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=li.inflate(R.layout.list_item_video,parent,false);
        return new VideoAdapter.Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {
        holder.tvVideoName.setText(videos.get(position).getName());
        holder.imgbtnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse
                        ("vnd.youtube://"+videos.get(position).getKey()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        ImageButton imgbtnYoutube;
        TextView tvVideoName;
        public Viewholder(View itemView) {
            super(itemView);
            imgbtnYoutube= (ImageButton) itemView.findViewById(R.id.imgbtnYoutube);
            tvVideoName= (TextView) itemView.findViewById(R.id.tvVideoName);
        }
    }
}
