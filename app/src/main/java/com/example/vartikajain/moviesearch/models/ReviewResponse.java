package com.example.vartikajain.moviesearch.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by VARTIKA JAIN on 06-06-2018.
 */

public class ReviewResponse {
    @SerializedName("results")
    private ArrayList<Review> results;
    @SerializedName("total_results")
    private int total_results;

    public ReviewResponse(ArrayList<Review> results, int total_results) {
        this.results = results;
        this.total_results = total_results;
    }

    public ArrayList<Review> getResults() {
        return results;
    }

    public int getTotal_results() {
        return total_results;
    }
}