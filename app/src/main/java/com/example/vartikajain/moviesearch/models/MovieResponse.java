package com.example.vartikajain.moviesearch.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by VARTIKA JAIN on 03-06-2018.
 */

public class MovieResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private ArrayList<Movie> results;
    @SerializedName("total_pages")
    private int total_pages;
    @SerializedName("total_results")
    private int total_results;

    public MovieResponse(int page, ArrayList<Movie> results, int total_pages, int total_results) {
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }

    public int getPage() {
        return page;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }
}
