package com.nathaniel.searchbox;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by USER on 9/14/2016.
 */
public class SearchApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
