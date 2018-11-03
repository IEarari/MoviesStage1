package com.example.ibraheem.moviesstage1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Details extends Fragment {

    private static final String LOG_TAG = Details.class.getSimpleName();

    @Bind(R.id.movieTitle)
    TextView mMovieTileTxt;
    @Bind(R.id.moviePoster)
    ImageView mMoviePoster;
    @Bind(R.id.movieReleaseYear)
    TextView mMovieReleaseYear;
    @Bind(R.id.movieRating)
    TextView mMovieRating;
    @Bind(R.id.movieOverview)
    TextView mMovieOverview;

    private String mMovieTitle;

    public Details() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details, container, false);
        ButterKnife.bind(this, view);
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(DetailsActivity.EXTRA_MOVIE)) {
            Movie selectedMovie = intent.getParcelableExtra(DetailsActivity.EXTRA_MOVIE);
            if (selectedMovie != null) {
                mMovieTitle = selectedMovie.getmTitle();
                fillDetailScreen(selectedMovie);
            }
        }

        return view;
    }

    private void fillDetailScreen(final Movie selectedMovie) {
        mMovieTileTxt.setText(selectedMovie.getmTitle());
        Picasso.with(getContext())
                .load("http://image.tmdb.org/t/p" + "/w185/" + selectedMovie.getmPosterPath())
                .placeholder(R.drawable.movie_img)
                .error(R.drawable.no_image)
                .into(mMoviePoster);
        mMovieRating.setText("" + selectedMovie.getmVoteAverage() + "/10");
        mMovieOverview.setText(selectedMovie.getmOverview());
        Pattern datePattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
        String year = selectedMovie.getmReleaseDate();
        Matcher dateMatcher = datePattern.matcher(year);
        if (dateMatcher.find()) {
            year = dateMatcher.group(1);

        }
        mMovieReleaseYear.setText(year);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.share, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        ShareActionProvider mShareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(createShareMovieIntent());
        } else {
            Log.d(LOG_TAG, "null share action");
        }
    }

    private Intent createShareMovieIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                mMovieTitle);
        return shareIntent;
    }
}
