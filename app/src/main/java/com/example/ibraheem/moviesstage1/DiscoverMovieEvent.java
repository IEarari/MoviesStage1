package com.example.ibraheem.moviesstage1;

public class DiscoverMovieEvent {
    private String mSortBy;

    public DiscoverMovieEvent(String sortBy) {
        mSortBy = sortBy;
    }

    public DiscoverMovieEvent() {
    }

    public String getmSortBy() {
        return mSortBy;
    }

    public void setmSortBy(String mSortBy) {
        this.mSortBy = mSortBy;
    }
}