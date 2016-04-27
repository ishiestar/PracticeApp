package com.example.ishitasinha.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ishitasinha on 13/04/16.
 */
public interface OmdbApiService {
    @GET("/")
    Call<List<ListItems>> searchList(@Query("s") String searchTerm);
}
