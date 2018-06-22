package com.example.vartikajain.moviesearch.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VARTIKA JAIN on 06-06-2018.
 */

public class Review {
    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String content;

    public Review(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}

