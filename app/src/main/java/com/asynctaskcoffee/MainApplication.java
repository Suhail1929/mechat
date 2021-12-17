package com.asynctaskcoffee;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {

    private static MainApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getInstance() {
        return instance.getApplicationContext();
    }
}
