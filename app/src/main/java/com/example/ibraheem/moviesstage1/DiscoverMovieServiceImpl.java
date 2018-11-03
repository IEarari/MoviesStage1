package com.example.ibraheem.moviesstage1;

import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class DiscoverMovieServiceImpl {

    private Bus mEventBus;
    private List<Movie> mMovieList;

    private static final String LOG_TAG = DiscoverMovieServiceImpl.class.getSimpleName();

    public DiscoverMovieServiceImpl(Bus eventBus) {
        mEventBus = eventBus;
        eventBus.register(this);
    }

    public DiscoverMovieServiceImpl() {
        PopularMoviesApplication.getEventBus().register(this);
    }

    @Subscribe
    public void onDiscoverMovieEvent(DiscoverMovieEvent event) {

        Retrofit client = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetMovieService api = client.create(GetMovieService.class);

        Call<MovieDetails> restCall = api.getPopularMovies(event.getmSortBy(), "7e454ef2680130203ed1755740e22d88");

        restCall.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Response<MovieDetails> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MovieDetails movieDetails = response.body();
                    mMovieList = movieDetails.getmMovieList();
                    PopularMoviesApplication.getEventBus().post(produceMovieEvent());
                } else {
                    Log.d(LOG_TAG, "Web call error");
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public MovieEvent produceMovieEvent() {
        return new MovieEvent(mMovieList);
    }
}