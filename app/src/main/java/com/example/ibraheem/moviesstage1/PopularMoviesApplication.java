package com.example.ibraheem.moviesstage1;

import android.app.Application;

import com.squareup.otto.Bus;

public class PopularMoviesApplication extends Application {

    private static Bus mEventBus;

    public static Bus getEventBus() {

        if (mEventBus == null) {
            mEventBus = new com.squareup.otto.Bus();
        }

        return mEventBus;
    }

}

