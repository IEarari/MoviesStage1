package com.example.ibraheem.moviesstage1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class GalleryItemAdapter extends ArrayAdapter<Movie> {

    private Context mContext;
    private List<Movie> mMovieList;

    public GalleryItemAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
        mContext = context;
        mMovieList = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.gallery_item, parent, false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.gallery_item_image);
        Picasso.with(mContext)
                .load("http://image.tmdb.org/t/p" + "/w185/" + mMovieList.get(position).getmPosterPath())
                .placeholder(R.drawable.movie_img)
                .error(R.drawable.no_image)
                .into(imageView);
        return convertView;
    }
}