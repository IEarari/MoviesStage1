package com.example.ibraheem.moviesstage1;

import java.util.List;

public class MovieEvent {

    List<Movie> mMovieList;

    public MovieEvent() {
    }

    public MovieEvent(List<Movie> mMovieList) {
        this.mMovieList = mMovieList;
    }

    public List<Movie> getmMovieList() {
        return mMovieList;
    }

    public void setmMovieList(List<Movie> mMovieList) {
        this.mMovieList = mMovieList;
    }
}