package io.github.rahmatsyam.mybatik.ui.main;

import android.app.Application;

import io.github.rahmatsyam.mybatik.BuildConfig;
import io.github.rahmatsyam.mybatik.util.ReleaseTree;
import timber.log.Timber;

public class ApplicationController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ReleaseTree());
        }
    }
}
