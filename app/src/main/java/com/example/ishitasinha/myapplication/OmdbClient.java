package com.example.ishitasinha.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ishitasinha on 14/04/16.
 */
public class OmdbClient {
    static OmdbApiService omdbApiService;

    public static OmdbApiService getOmdbApiService() {
        if (omdbApiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.omdbapi.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            omdbApiService = retrofit.create(OmdbApiService.class);
        }
        return omdbApiService;
    }
}
