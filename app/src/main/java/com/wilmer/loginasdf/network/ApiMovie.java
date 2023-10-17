package com.wilmer.loginasdf.network;


import com.wilmer.loginasdf.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiMovie {
    @GET("/v1/eeced007-6b29-4c9d-ab63-c115a990d927")
    Call<List<Movie>> getMovies();
}
