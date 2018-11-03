package com.example.ibraheem.moviesstage1;

import android.os.Bundle;

public class DetailsActivity extends MainViewSettings {

    public static final String EXTRA_MOVIE = "com.example.ibraheem.moviesstage1.EXTRA_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detailsContainer, new Details())
                    .commit();
        }
    }

}