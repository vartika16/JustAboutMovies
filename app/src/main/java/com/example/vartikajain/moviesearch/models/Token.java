package com.example.vartikajain.moviesearch.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VARTIKA JAIN on 07-06-2018.
 */

public class Token {
    @SerializedName("success")
    private boolean success;
    @SerializedName("expires_at")
    private String expires_at;
    @SerializedName("request_token")
    private String request_token;

    public Token(boolean success, String expires_at, String request_token) {
        this.success = success;
        this.expires_at = expires_at;
        this.request_token = request_token;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public String getRequest_token() {
        return request_token;
    }
}
