package com.example.vartikajain.moviesearch.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VARTIKA JAIN on 07-06-2018.
 */

public class Rating {
    @SerializedName("value")
    private Double value;

    public Rating(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
