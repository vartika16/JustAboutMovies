package com.example.vartikajain.moviesearch.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VARTIKA JAIN on 07-06-2018.
 */

public class RatingResponse {
    @SerializedName("status_code")
    private int status_code;
    @SerializedName("status_message")
    private String status_message;

    public RatingResponse(int status_code, String status_message) {
        this.status_code = status_code;
        this.status_message = status_message;
    }

    public int getStatus_code() {
        return status_code;
    }

    public String getStatus_message() {
        return status_message;
    }
}
