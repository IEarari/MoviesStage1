package com.example.ibraheem.moviesstage1;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


public interface GetMovieService {
    String Top_Rated_Path ="top_rated" , Popular_Path = "popular";


    @GET(Popular_Path)
    public Call<MovieDetails> getPopularMovies(@Query("api_key") String API_KEY);

    @GET(Top_Rated_Path)
    public Call<MovieDetails> getTopRatedMovies(@Query("api_key") String API_KEY);

}