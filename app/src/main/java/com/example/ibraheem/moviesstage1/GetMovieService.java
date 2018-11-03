package com.example.ibraheem.moviesstage1;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


public interface GetMovieService {

    @GET("/3/discover/movie")
    public Call<MovieDetails> getPopularMovies(@Query("sort_by") String sortBy, @Query("api_key") String apiKey);

}