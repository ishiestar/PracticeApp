package com.example.ishitasinha.myapplication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ishitasinha on 12/04/16.
 */
public class ListItems {

    @SerializedName("Poster")
    String poster;
    @SerializedName("Year")
    String released;
    @SerializedName("Title")
    String title;

    ListItems(String poster, String title, String released) {
        this.poster = poster;
//        this.rating = rating;
        this.released = released;
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public String getTitle() {
        return title;
    }

    public String getReleased() {
        return released;
    }
}
