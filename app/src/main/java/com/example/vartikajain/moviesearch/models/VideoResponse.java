package com.example.vartikajain.moviesearch.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by VARTIKA JAIN on 08-06-2018.
 */

public class VideoResponse {
    @SerializedName("results")
    private ArrayList<Video> results;

    public VideoResponse(ArrayList<Video> results) {
        this.results = results;
    }

    public ArrayList<Video> getResults() {
        return results;
    }
}
