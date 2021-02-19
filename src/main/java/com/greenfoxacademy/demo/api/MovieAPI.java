package com.greenfoxacademy.demo.api;

import com.greenfoxacademy.demo.model.Genre;
import com.greenfoxacademy.demo.model.Genres;
import com.greenfoxacademy.demo.model.TVCollection;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface MovieAPI {

    @GET("genre/movie/list")
    public Call<Genres> getGenres(@Query("api_key") String apiKey);

    @GET("tv/popular")
    public Call<TVCollection> getTVShows(@Query("api_key") String apiKey);
}
