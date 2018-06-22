package com.example.vartikajain.moviesearch.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VARTIKA JAIN on 08-06-2018.
 */

public class Video {
    @SerializedName("key")
    private String key;
    @SerializedName("name")
    private String name;
    @SerializedName("site")
    private String site;
    @SerializedName("type")
    private String type;

    public Video(String key, String name, String site, String type) {
        this.key = key;
        this.name = name;
        this.site = site;
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public String getType() {
        return type;
    }
}
