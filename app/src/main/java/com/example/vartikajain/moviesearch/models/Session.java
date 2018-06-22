package com.example.vartikajain.moviesearch.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VARTIKA JAIN on 07-06-2018.
 */

public class Session {
    @SerializedName("success")
    private Boolean success;
    @SerializedName("session_id")
    private String session_id;

    public Session(Boolean success, String session_id) {
        this.success = success;
        this.session_id = session_id;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getSession_id() {
        return session_id;
    }

}
